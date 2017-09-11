import { LocalDateTime } from "js-joda";

export interface Message {
    msgId?: string,
    senderId?: string,
    reveiverId?: string,
    horo?: LocalDateTime,
    object?: string,
    message?: string
}
