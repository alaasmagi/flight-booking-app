import axios from "axios";


export async function getFlights() {
    const response = await axios.get(`${import.meta.env.VITE_API_URL}/flights`);
    return response.data;
  }