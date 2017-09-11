import { Duration } from "js-joda";
import { LocalDate } from "js-joda";
import { LocalTime } from "js-joda";

import { Passenger } from "./passenger.model";
import { Toll } from "./toll.model";
import { WayPoint } from "./waypoint.model";

export interface SubRun {
    subRunId?: string,
    flexibility?: Duration,
    startPlace?: WayPoint,
    endPlace?: WayPoint,
    startDate?: LocalDate,
    startTime?: LocalTime,
    estimatedEndDate?: LocalDate,
    estimatedEndTime?: LocalTime,
    availableSeats?: number,
    passengers?: Passenger[],
    startingPoints?: WayPoint[],
    tolls?: Toll[],
    price?: number
}
