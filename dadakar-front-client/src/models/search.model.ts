import { LocalDate } from 'js-joda';

export interface Search {
    startTown?: string,
    startDistrict?: string,
    endTown?: string,
    endDistrict?: string,
    startDate?: LocalDate
}
