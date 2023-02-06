import {OperationName} from "./enums/OperationName";
import {OperationType} from "./enums/OperationType";
import {OperationQuantity} from "./enums/OperationQuantity";

export const mapNumberToOperationName = (value: number) => OperationName[value]
export const mapNumberToOperationType = (value: number) => OperationType[value]
export const mapNumberToOperationQuantity = (value: number) => OperationQuantity[value]
