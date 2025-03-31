import axios from "axios";
import type { SeatModel } from "../models/SeatModel";
import type { SeatFilterModel } from "../models/SeatFilterModel";

export async function RecommendSeats(filters: SeatFilterModel): Promise<SeatModel[] | null> {
  const params = new URLSearchParams();

  if (filters.numberOfSeats != null) params.append("numberOfSeats", filters.numberOfSeats.toString());
  if (filters.seatClass != null) params.append("seatClass", filters.seatClass);
  if (filters.window) params.append("window", filters.window.toString());
  if (filters.extraLegroom) params.append("extraLegroom", filters.extraLegroom.toString());
  if (filters.nearExit) params.append("nearExit", filters.nearExit.toString());

  const response = await axios.get(`${import.meta.env.VITE_API_URL}/seats/recommend?${params.toString()}`);

  return response.data;
}

export async function BookSeat(seatIds: number[]): Promise<string> {
  const response = await axios.post(`${import.meta.env.VITE_API_URL}/seats/book`, seatIds, {
    headers: {
      "Content-Type": "application/json",
    },
  });

  const result: string = response.data;
  return result;
}
