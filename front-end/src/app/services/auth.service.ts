import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { Response } from '../interfaces/responses.interface';
import {
  RequestLogin,
  RequestRegister,
} from '../interfaces/requests.interface';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private readonly url: string = 'http://localhost:8080/auth';
  private authState = new BehaviorSubject<boolean>(false);
  private nameSubject = new BehaviorSubject<string>('');

  constructor(private http: HttpClient) {}

  get isLoggedinIn(): Observable<boolean> {
    return this.authState.asObservable();
  }

  get getName(): Observable<string> {
    return this.nameSubject.asObservable();
  }

  login(body: RequestLogin): Observable<Response> {
    return this.http.post<Response>(`${this.url}/login`, body).pipe(
      tap((response) => {
        this.authState.next(true);
        this.saveStorage(response);
        this.nameSubject.next(response.name);
      })
    );
  }

  register(body: RequestRegister): Observable<Response> {
    return this.http.post<Response>(`${this.url}/register`, body).pipe(
      tap((response) => {
        this.authState.next(true);
        this.saveStorage(response);
        this.nameSubject.next(response.name);
      })
    );
  }

  logout(): void {
    localStorage.removeItem('userData');
    this.authState.next(false);
  }

  private saveStorage(data: { name: string; token: string }): void {
    localStorage.setItem('userData', JSON.stringify(data));
  }
}
