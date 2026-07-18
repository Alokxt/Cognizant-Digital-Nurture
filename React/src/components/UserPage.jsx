import FlightCard from "./FlightCard";
import flights from "../data/flights";

function UserPage({ userName }) {
  return (
    <section className="page">
      <div className="page-intro">
        <span className="eyebrow">Welcome back, {userName}</span>
        <h1>Today's departures from Indore</h1>
        <p>You're logged in, so you can reserve a seat on any flight.</p>
      </div>

      <div className="ticket-list">
        {flights.map((flight) => (
          <FlightCard key={flight.id} flight={flight} canBook={true} />
        ))}
      </div>
    </section>
  );
}

export default UserPage;
