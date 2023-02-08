import { ApiConnector } from './ApiConnector';
import { Observable } from 'rxjs';
import { ExecutionTimeDto } from './model/ExecutionTimeDto';

export class Endpoints extends ApiConnector {


	public readonly getSimple = (quantity: number): Observable<ExecutionTimeDto> => {
		const uri = `${this.url}?uploadLines=${quantity}`;
		return this.get<ExecutionTimeDto>(uri);
	};
	public readonly getCondition = (quantity: number): Observable<ExecutionTimeDto> => {
		const uri = `${this.url}/city?uploadLines=${quantity}&city=Toro`;
		return this.get<ExecutionTimeDto>(uri);
	};
	public readonly getAvgConditioned = (quantity: number): Observable<ExecutionTimeDto> => {
		const uri = `${this.url}/avg-price-by-city?uploadLines=${quantity}`;
		return this.get<ExecutionTimeDto>(uri);
	};
	public readonly getConditionedOrdered = (quantity: number): Observable<ExecutionTimeDto> => {
		const uri = `${this.url}/cityAndSort?uploadLines=${quantity}&city=Toro`;
		return this.get<ExecutionTimeDto>(uri);
	};
	public readonly updateSimple = (quantity: number): Observable<ExecutionTimeDto> => {
		const uri = `${this.url}/updateAdDescription?uploadLines=${quantity}`;
		return this.put<ExecutionTimeDto>(uri);
	};
	public readonly updateCondition = (quantity: number): Observable<ExecutionTimeDto> => {
		const uri = `${this.url}/updateAdDescriptionCity?uploadLines=${quantity}&city=Toro`;
		return this.put<ExecutionTimeDto>(uri);
	};
	public readonly addSimple = (quantity: number): Observable<ExecutionTimeDto> => {
		const uri = `${this.url}/upload?uploadLines=${quantity}`;
		return this.post<ExecutionTimeDto>(uri);
	};
	public readonly deleteSimple = (quantity: number): Observable<ExecutionTimeDto> => {
		const uri = `${this.url}lines?uploadLines=${quantity}&deleteLines=${quantity}`;
		return this.delete<ExecutionTimeDto>(uri);
	};
	public readonly deleteCondition = (quantity: number): Observable<ExecutionTimeDto> => {
		const uri = `${this.url}/city?uploadLines=${quantity}&city=Toro`;
		return this.delete<ExecutionTimeDto>(uri);
	};
}

export function useEndpoints() {
	return new Endpoints();
}
