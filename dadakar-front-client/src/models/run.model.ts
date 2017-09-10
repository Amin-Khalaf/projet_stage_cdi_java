import { SubRun } from "./subrun.model";

import { Luggage } from "./enums/luggage.model";

export interface Run {
    runId?: string,
    driverId?: string,
    vehicleId?: string,
    subRuns?:SubRun[],
    luggageType?: Luggage,
    cancelled?: boolean
}
