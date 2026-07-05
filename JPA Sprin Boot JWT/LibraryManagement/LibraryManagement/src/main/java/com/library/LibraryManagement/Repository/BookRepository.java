package com.library.LibraryManagement.Repository;

import lombok.NonNull;
import com.library.LibraryManagement.Entities.BookStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookRepository extends JpaRepository<BookStore, Long> {
    List<BookStore> findByAuthorName(String authorName);
}
