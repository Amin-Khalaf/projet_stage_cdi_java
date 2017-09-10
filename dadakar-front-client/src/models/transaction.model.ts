import { LocalDateTime } from "js-joda";

import { TxState } from "./enums/txstate.model";

export interface Transaction {
    transactionId?: number,
    transactionNumber?: string,
    transactionDate?: LocalDateTime,
    senderId?: string,
    receiverId?: string,
    state?: TxState
}
