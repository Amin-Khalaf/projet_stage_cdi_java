import { LocalDateTime } from "js-joda";

export interface Message {
    msgId?: string,
    senderId?: string,
    receiverId?: string,
    horo?: LocalDateTime,
    object?: string,
    message?: string,
    seen?: boolean,
    replied?: boolean
}
