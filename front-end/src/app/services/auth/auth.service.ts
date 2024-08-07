import { Response } from '../../interfaces/responses.interface';
import { HttpClient } from '@angular/common/http';
import { StatesService } from '../states/states.service';
import { Observable, tap } from 'rxjs';
import { inject, Injectable } from '@angular/core';
import {
  RequestLogin,
  RequestRegister,
} from '../../interfaces/requests.interface';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private readonly url: string = 'http://localhost:8080/auth';

  private http = inject(HttpClient);
  private states = inject(StatesService);

  login(body: RequestLogin): Observable<Response> {
    return this.postMapping('login', body);
  }

  register(body: RequestRegister): Observable<Response> {
    return this.postMapping('register', body);
  }

  private postMapping(
    path: string,
    body: RequestLogin | RequestRegister
  ): Observable<Response> {
    return this.http
      .post<Response>(`${this.url}/${path}`, body)
      .pipe(tap((response) => this.saveStatesAndStorage(response)));
  }

  private saveStatesAndStorage(response: Response) {
    this.states.setAuthState(true);
    this.states.setNameSubject(response.name);
    localStorage.setItem('userData', JSON.stringify(response));
  }
}
