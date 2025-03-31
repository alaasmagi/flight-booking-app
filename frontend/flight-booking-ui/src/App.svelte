<script lang="ts">
  import { Router, Route, Link } from "svelte-routing";
  import FlightList from "./lib/views/FlightList.svelte";
  import SeatSelection from "./lib/views/SeatSelection.svelte";
</script>

<Router>
  <Route path="/">
    <FlightList />
  </Route>
  <Route path="/seats">
    <SeatSelection />
  </Route>
</Router>
<main></main>

<style>
</style>
