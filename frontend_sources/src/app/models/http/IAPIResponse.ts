export interface IAPIResponse<T> {
  error: number,
  errorReason: string,
  response?: T
}
