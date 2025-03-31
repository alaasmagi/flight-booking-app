<script lang="ts">
  import { getFlights } from "../businesslogic/FlightRequests";
  import type { FlightFilterModel } from "../models/FlightFilterModel";
  import type { FlightModel } from "../models/FlightModel";
  import { navigate } from "svelte-routing";
  import { onMount } from "svelte";

  let availableFlights: FlightModel[] = [];
  let viewState = 0;

  async function loadFlights() {
    const filters: FlightFilterModel = {};
    availableFlights = await getFlights(filters);
  }

  async function handleSelectFlight() {
    if (selectedFlightId != 0) {
      navigate("/seats");
    }
  }

  onMount(() => {
    loadFlights();
  });

  let selectedFlightId = 0;
</script>

<div class="main-container">
  <div class="container-card">
    <h2 class="text-4xl font-semibold">Saadaolevad lennud:</h2>
    {#each availableFlights as flight}
      <div class="seat-card">
        <p><strong>Lennuliin:</strong> {`${flight.originCity} - ${flight.destinationCity}`}</p>
        <p><strong>Väljumine:</strong> {flight.departureTime}</p>
        <p><strong>Saabumine:</strong> {flight.arrivalTime}</p>
        <p><strong>Hind:</strong> {`${flight.price}€/pilet`}</p>
        <button
          on:click={() => {
            selectedFlightId = flight.id;
            handleSelectFlight();
          }}>Vali</button
        >
      </div>
    {/each}
  </div>
</div>

<style>
  .main-container {
    display: flex;
    flex-direction: column;
    gap: 15px;
  }

  .container-card {
    display: flex;
    flex-direction: column;
    gap: 20px;
    align-self: center;
    min-width: 600px;
    border-radius: 30px;
    background-color: #2b2b2b;
    padding: 20px 15px;
  }
</style>
