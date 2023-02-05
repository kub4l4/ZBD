import {catchError, mergeMap, Observable} from "rxjs";
import {fromFetch} from "rxjs/internal/observable/dom/fetch";
import {HttpError} from "./HttpError";

export abstract class ApiConnector {

    protected url = 'localhost:8080';

    protected get<T>(url: string, headers?: Record<string, string>): Observable<T> {
        console.log(`--> GET [${url}] ${JSON.stringify(headers)}`)
        return fromFetch(url, {method: 'GET', headers: {Accept: 'aplication/json', ...headers},}).pipe(
            catchError(this.handleError),
            mergeMap((response) => {
                this.ensureSuccessCode(response);
                return this.parseResponse<T>(response);
            })
        )
    }

    private readonly handleError = (error: any) => {
        throw error;
    }

    private readonly ensureSuccessCode = (response: Response) => {
        if (response.status < 200 || response.status >= 300) {
            throw new HttpError(response.status, `ERROR: ${response.status}`
            )
        }
    }

    private readonly parseResponse = async <T>(response: Response): Promise<T> => {
        try {
            return await response.json()
        } catch {
            return {} as T;
        }
    }

}