export interface SeatFilterModel {
    numberOfSeats: number;
    seatClass?: string;
    window?: boolean;
    extraLegroom?: boolean;
    nearExit?: boolean;
}