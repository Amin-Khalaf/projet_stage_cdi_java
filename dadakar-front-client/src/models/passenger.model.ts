import { User } from "./user.model";

import { Luggage } from "./enums/luggage.model";
import { ResState } from "./enums/resstate.model";

export interface Passenger {
    passengerId?: string,
    user?: User,
    luggageType?: Luggage,
    price?: number,
    reservationState?: ResState
}
