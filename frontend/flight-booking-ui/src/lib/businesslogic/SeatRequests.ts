import axios from "axios";
import type { SeatModel } from "../models/SeatModel";
import type { SeatFilterModel } from "../models/SeatFilterModel";


export async function RecommendSeats(filters: SeatFilterModel): Promise<SeatModel[] | null> {
    const response = await axios.get(`${import.meta.env.VITE_API_URL}/seats/recommend?numberOfSeats=${filters.numberOfSeats}
                                                &seatClass=${filters.seatClass}&window=${filters.window}
                                                &extraLegroom=${filters.extraLegroom}&nearExit=${filters.nearExit}`);
    
    const result: SeatModel[] = response.data;
    return result;
}

export async function BookSeat(seatIds: number[]): Promise<string> {
    const response = await axios.post(`${import.meta.env.VITE_API_URL}/seats/book`, 
        {
            seatIds: seatIds
        },
        {
            headers: {
              "Content-Type": "application/json",
            }
        }
    );
    
    const result: string = response.data;
    return result;
}