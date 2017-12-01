import { User } from "./user.model";

export interface Rating {
    ratingId?: string,
    value?: number,
    rater?: User,
    comment?: string
}
