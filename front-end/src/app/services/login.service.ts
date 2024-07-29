import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Response } from '../interfaces/responses.interface';
import {
  RequestLogin,
  RequestRegister,
} from '../interfaces/requests.interface';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  private readonly url: string = 'http://localhost:8080/auth';

  constructor(private http: HttpClient) {}

  login(body: RequestLogin): Observable<Response> {
    return this.http.post<Response>(`${this.url}/login`, body);
  }

  register(body: RequestRegister): Observable<Response> {
    return this.http.post<Response>(`${this.url}/register`, body);
  }
}
