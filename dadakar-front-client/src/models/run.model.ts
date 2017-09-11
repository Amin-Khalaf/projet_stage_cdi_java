import { SubRun } from "./subrun.model";
import { User } from "./user.model";

import { Luggage } from "./enums/luggage.model";

export interface Run {
    runId?: string,
    driver?: User,
    vehicleId?: string,
    subRuns?:SubRun[],
    luggageType?: Luggage,
    cancelled?: boolean
}
