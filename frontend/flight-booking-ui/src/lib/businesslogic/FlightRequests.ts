import axios from "axios";
import type { FlightFilterModel } from "../models/FlightFilterModel";
import type { FlightModel } from "../models/FlightModel";

export async function getFlights(filters: FlightFilterModel): Promise<FlightModel[] | null> {
  const params = new URLSearchParams();

  if (filters.originCity != null) params.append("originCity", filters.originCity);
  if (filters.destinationCity != null) params.append("destinationCity", filters.destinationCity);
  if (filters.arrivalTime) params.append("arrivalTime", filters.arrivalTime);
  if (filters.departureTime) params.append("departureTime", filters.departureTime);
  if (filters.price) params.append("price", filters.price.toString());

  const response = await axios.get(`${import.meta.env.VITE_API_URL}/flights?${params.toString()}`);

  const result: FlightModel[] = response.data;
  return result;
}
