import { useState } from "react";

/**
 * Renders nothing when a flight still has plenty of seats.
 * This is the "preventing a component from rendering" pattern:
 * a component can return null instead of JSX.
 */
function LowSeatsFlag({ seatsLeft }) {
  if (seatsLeft > 3) {
    return null;
  }
  return <span className="pill pill-warn">Only {seatsLeft} seats left</span>;
}

/**
 * BookingAction conditionally renders either a "Book" button,
 * a confirmed state, or nothing at all — depending on whether
 * the viewer is logged in and whether the flight was booked.
 */
function BookingAction({ canBook, booked, onBook }) {
  if (!canBook) {
    return <p className="hint-text">Log in to book this flight</p>;
  }

  if (booked) {
    return <span className="pill pill-confirmed">Booked ✓</span>;
  }

  return (
    <button className="btn btn-accent btn-small" onClick={onBook}>
      Book seat
    </button>
  );
}

function FlightCard({ flight, canBook }) {
  const [booked, setBooked] = useState(false);

  return (
    <article className="ticket">
      <div className="ticket-main">
        <div className="ticket-row">
          <span className="carrier">{flight.carrier}</span>
          <span className="flight-id">{flight.id}</span>
        </div>

        <div className="ticket-route">
          <div className="route-point">
            <span className="code">{flight.fromCode}</span>
            <span className="city">{flight.from}</span>
            <span className="time">{flight.depart}</span>
          </div>

          <div className="route-line" aria-hidden="true">
            <span className="plane">✈</span>
          </div>

          <div className="route-point route-point-end">
            <span className="code">{flight.toCode}</span>
            <span className="city">{flight.to}</span>
            <span className="time">{flight.arrive}</span>
          </div>
        </div>

        <div className="ticket-meta">
          <span>Duration {flight.duration}</span>
          <span>Gate {flight.gate}</span>
          <LowSeatsFlag seatsLeft={flight.seatsLeft} />
        </div>
      </div>

      <div className="ticket-stub">
        <span className="price">₹{flight.price.toLocaleString("en-IN")}</span>
        <BookingAction
          canBook={canBook}
          booked={booked}
          onBook={() => setBooked(true)}
        />
      </div>
    </article>
  );
}

export default FlightCard;
