export class HttpError extends Error {
    public statusCode: number = 0

    constructor(code: number, ...params: any[]) {
        super(...params);
        if (Error.captureStackTrace) {
            Error.captureStackTrace(this, HttpError)
        }

        this.name = 'HttpError';
        this.statusCode = code;
    }
}