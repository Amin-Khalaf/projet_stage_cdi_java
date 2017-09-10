import { Address } from "./address.model";

export interface WayPoint {
    wayPointId?:string,
    meetingPoint?: string,
    address?: Address
}
