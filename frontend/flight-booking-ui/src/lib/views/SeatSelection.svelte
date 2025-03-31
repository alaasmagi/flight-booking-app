<script lang="ts">
  import { navigate } from "svelte-routing";
  import { BookSeat, RecommendSeats } from "../businesslogic/SeatRequests.js";
  import { TranslateSeatClass } from "../helpers/TranslateSeatClasses.js";
  import type { SeatFilterModel } from "../models/SeatFilterModel.js";
  import type { SeatModel } from "../models/SeatModel.js";

  let availableSeats: SeatModel[] = [];
  let viewState = 0;

  let success: boolean = false;
  let isWindowFilterChecked = false;
  let isExtraLegroomChecked = false;
  let isNearExitChecked = false;
  let seatClass = "ECONOMY";
  let numberOfSeats: number = 1;

  async function loadSeats() {
    const filters: SeatFilterModel = {
      window: isWindowFilterChecked,
      extraLegroom: isExtraLegroomChecked,
      nearExit: isNearExitChecked,
      seatClass: seatClass,
      numberOfSeats: numberOfSeats,
    };
    availableSeats = await RecommendSeats(filters);
  }

  async function handleFilter() {
    await loadSeats();
    if (availableSeats != null) {
      viewState = 1;
    }
  }

  async function handleConfirmation() {
    const seatIds = availableSeats.map((seat) => seat.id);
    await BookSeat(seatIds);
    success = true;
  }
</script>

<div class="main-container">
  {#if viewState == 0}
    <div class="container-card">
      <h2 class="text-lg font-semibold">Rakenda filtrid:</h2>
      <div class="container-horizontal">
        <label>
          <input type="checkbox" bind:checked={isWindowFilterChecked} />
          Aknakoht
        </label>
        <label>
          <input type="checkbox" bind:checked={isExtraLegroomChecked} />
          Rohkem ruumi jalgadele
        </label>
        <label>
          <input type="checkbox" bind:checked={isNearExitChecked} />
          Väljapääsu lähedal
        </label>
      </div>
      <div class="container-horizontal">
        <label>
          Kohtade arv:
          <input class="input-field" type="number" bind:value={numberOfSeats} />
        </label>
        <label>
          Istme klass:
          <select class="input-field" bind:value={seatClass}>
            <option value="ECONOMY">{TranslateSeatClass["ECONOMY"]}</option>
            <option value="BUSINESS">{TranslateSeatClass["BUSINESS"]}</option>
            <option value="FIRSTCLASS">{TranslateSeatClass["FIRSTCLASS"]}</option>
          </select>
        </label>
      </div>
      <button class="confirmButton" on:click={() => handleFilter()}>Filtreeri</button>
    </div>
  {:else}
    <div class="container-card">
      <h2 class="text-4xl font-semibold">Soovitatud kohad:</h2>
      {#each availableSeats as seat}
        <div class="seat-card">
          <p><strong>Kohatähis:</strong> {seat.seatLetter + seat.rowNumber}</p>
          <p><strong>Istme klass:</strong> {TranslateSeatClass[seat.seatClass as keyof typeof TranslateSeatClass]}</p>
        </div>
      {/each}
      <a class="link" on:click={() => (viewState = 0)}>Tagasi</a>

      {#if success}
        <p class="successText">Kohtade broneerimine õnnestus!</p>
        <button class="confirmButton" on:click={() => navigate("/")}>Tagasi avalehele</button>
      {:else}
        <button class="confirmButton" on:click={handleConfirmation}>Broneeri kohad</button>
      {/if}
    </div>
  {/if}
</div>

<style>
  .main-container {
    display: flex;
    flex-direction: column;
    gap: 15px;
  }

  .link {
    color: inherit;
    font-size: inherit;
    text-decoration: underline;
    cursor: pointer;
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

  .input-field {
    padding: 2px;
    max-width: 150px;
    font-size: inherit;
    color: inherit;
  }

  .container-horizontal {
    display: flex;
    gap: 20px;
    align-self: center;
  }

  .successText {
    color: green;
    font-weight: 700;
    font-size: 20px;
  }

  .confirmButton {
    padding: 15px 50px;
    align-self: center;
    max-width: 300px;
    border: 1px solid #4492ea;
    border-radius: 20px;
    background-color: #1e1e1e;
    color: #bcbcbd;
    cursor: pointer;
    font-size: 22px;
    font-weight: 700;
    outline: none;
    cursor: pointer;
  }

  .confirmButton:hover {
    background-color: #2b2b2b;
  }
</style>
