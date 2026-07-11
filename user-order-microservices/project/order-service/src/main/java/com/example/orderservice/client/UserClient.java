package com.example.orderservice.client;

import com.example.orderservice.dto.UserDTO;
import com.example.orderservice.exception.InvalidUserException;
import com.example.orderservice.exception.UserServiceUnavailableException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

@Component
@Slf4j
public class UserClient {

    private final WebClient webClient;

    public UserClient(@Qualifier("userServiceWebClient") WebClient webClient) {
        this.webClient = webClient;
    }
    public UserDTO getUserById(Long userId) {
        return webClient.get()
                .uri("/api/users/{id}", userId)
                .retrieve()
                .onStatus(status -> status.value() == 404, response ->
                        Mono.error(new InvalidUserException("User not found with id: " + userId)))
                .onStatus(org.springframework.http.HttpStatusCode::is5xxServerError, response ->
                        Mono.error(new UserServiceUnavailableException("user-service returned a server error")))
                .bodyToMono(UserDTO.class)
                .retryWhen(Retry.backoff(2, Duration.ofMillis(300))
                        .filter(this::isRetryable))
                .onErrorMap(WebClientRequestException.class, ex ->
                        new UserServiceUnavailableException("Could not reach user-service: " + ex.getMessage(), ex))
                .block();
    }

    public boolean userExists(Long userId) {
        try {
            getUserById(userId);
            return true;
        } catch (InvalidUserException ex) {
            return false;
        }
    }

    private boolean isRetryable(Throwable throwable) {

        if (throwable instanceof InvalidUserException) {
            return false;
        }
        return throwable instanceof WebClientRequestException
                || throwable instanceof UserServiceUnavailableException
                || (throwable instanceof WebClientResponseException wcre && wcre.getStatusCode().is5xxServerError());
    }
}
