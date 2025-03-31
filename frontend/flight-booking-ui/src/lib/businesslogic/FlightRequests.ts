import axios from "axios";
import type { FlightFilterModel } from "../models/FlightFilterModel";
import type { FlightModel } from "../models/FlightModel";


export async function getFlights(filters: FlightFilterModel): Promise<FlightModel[] | null> {
    const response = await axios.get(`${import.meta.env.VITE_API_URL}/flights?originCity=${filters.originCity}
                                                &destinationCity=${filters.destinationCity}&date=${filters.date}
                                                &arrivalTime=${filters.arrivalTime}&departureTime=${filters.departureTime}
                                                &price=${filters.price}`);
    
    const result: FlightModel[] = response.data;
    return result;
}