import { Luggage } from "./enums/luggage.model";
import { ResState } from "./enums/resstate.model";

export interface Passenger {
    passengerId?: string,
    userId?: string,
    luggageType?: Luggage,
    price?: number,
    reservationState?: ResState
}
