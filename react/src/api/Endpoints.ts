import {ApiConnector} from "./ApiConnector";
import {Observable} from "rxjs";
import {ExecutionTimeDto} from "./model/ExecutionTimeDto";
import {OperationQuantity} from "../objects/enums/OperationQuantity";
import {OperationType} from "../objects/enums/OperationType";

export class Endpoints extends ApiConnector {
    public readonly getAddingTime = (quantity: number): Observable<ExecutionTimeDto> => {
        const uri = `${this.url}/add&quantity=${quantity}`
        return this.get<ExecutionTimeDto>(uri);
    }
    public readonly getDeletingTime = (type: OperationType, quantity: OperationQuantity): Observable<ExecutionTimeDto> => {
        const uri = `${this.url}/delete&type=${type}&quantity=${quantity}`
        return this.get<ExecutionTimeDto>(uri);
    }
    public readonly getUpdateTime = (type: OperationType, quantity: OperationQuantity): Observable<ExecutionTimeDto> => {
        const uri = `${this.url}/update&type=${type}&quantity=${quantity}`
        return this.get<ExecutionTimeDto>(uri);
    }
    public readonly getReceiveTime = (type: OperationType, quantity: OperationQuantity): Observable<ExecutionTimeDto> => {
        const uri = `${this.url}/get&type=${type}&quantity=${quantity}`
        return this.get<ExecutionTimeDto>(uri);
    }
}

export function useEndpoints(){
    return new Endpoints();
}