import FlightCard from "./FlightCard";
import flights from "../data/flights";

function GuestPage() {
  return (
    <section className="page">
      <div className="page-intro">
        <span className="eyebrow">Browsing as guest</span>
        <h1>Today's departures from Indore</h1>
        <p>
          You can browse every scheduled flight below. Log in to reserve a
          seat.
        </p>
      </div>

      <div className="ticket-list">
        {flights.map((flight) => (
          <FlightCard key={flight.id} flight={flight} canBook={false} />
        ))}
      </div>
    </section>
  );
}

export default GuestPage;
