import { Role } from "./enums/role.model";

export interface Account {
    accountId?: string,
    username?: string,
    password?: string,
    role?: Role,
    banned?: boolean,
    deleted?: boolean
}
