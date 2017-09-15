import { Rating } from "./rating.model";
import { Vehicle } from "./vehicle.model";

export interface User {
    userId?: string,
    accountId?: string,
    firstName?: string,
    lastName?: string,
    mail?: string,
    idCard?: string,
    photo?: string,
    drivingLicence?: string,
    vehicles?: Vehicle[],
    ratings?: Rating[]
}
