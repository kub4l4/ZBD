import { catchError, mergeMap, Observable } from 'rxjs';
import { fromFetch } from 'rxjs/internal/observable/dom/fetch';
import { HttpError } from './HttpError';

export abstract class ApiConnector {

	protected url = 'http://localhost:8080/api/measure';

	protected get<T>(url: string, headers?: Record<string, string>): Observable<T> {
		console.log(`--> GET [${url}]`);
		return fromFetch(url, { method: 'GET', headers: { Accept: 'application/json', ...headers } }).pipe(
			catchError(this.handleError),
			mergeMap((response) => {
				this.ensureSuccessCode(response);
				return this.parseResponse<T>(response);
			})
		);
	}

	protected post<T>(url: string, headers?: Record<string, string>): Observable<T> {
		console.log(`--> POST [${url}]`);
		return fromFetch(url, { method: 'POST', headers: { Accept: 'application/json', ...headers } }).pipe(
			catchError(this.handleError),
			mergeMap((response) => {
				this.ensureSuccessCode(response);
				return this.parseResponse<T>(response);
			})
		);
	}

	protected put<T>(url: string, headers?: Record<string, string>): Observable<T> {
		console.log(`--> PUT [${url}] ${JSON.stringify(headers)}`);
		return fromFetch(url, { method: 'PUT', headers: { Accept: 'application/json', ...headers } }).pipe(
			catchError(this.handleError),
			mergeMap((response) => {
				this.ensureSuccessCode(response);
				return this.parseResponse<T>(response);
			})
		);
	}

	protected delete<T>(url: string, headers?: Record<string, string>): Observable<T> {
		console.log(`--> DELETE [${url}] ${JSON.stringify(headers)}`);
		return fromFetch(url, { method: 'DELETE', headers: { Accept: 'application/json', ...headers } }).pipe(
			catchError(this.handleError),
			mergeMap((response) => {
				this.ensureSuccessCode(response);
				return this.parseResponse<T>(response);
			})
		);
	}

	private readonly handleError = (error: any) => {
		throw error;
	};

	private readonly ensureSuccessCode = (response: Response) => {
		if (response.status < 200 || response.status >= 300) {
			throw new HttpError(response.status, `ERROR: ${response.status}`
			);
		}
	};

	private readonly parseResponse = async <T>(response: Response): Promise<T> => {
		try {
			return await response.json();
		} catch {
			return {} as T;
		}
	};

}
