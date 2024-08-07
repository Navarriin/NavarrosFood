import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { FoodInterface } from '../../interfaces/food.interface';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class FoodsService {
  private readonly url: string = 'http://localhost:8080/api/foods';

  private token: string;
  private headers: HttpHeaders;

  constructor(private http: HttpClient) {
    this.token = this.getTokenStorage();

    this.headers = new HttpHeaders({
      Authorization: `Bearer ${this.token}`,
    });
  }

  getAllFoods(): Observable<FoodInterface[]> {
    return this.http.get<FoodInterface[]>(this.url, { headers: this.headers });
  }

  getFoodById(id: number): Observable<FoodInterface> {
    return this.http.get<FoodInterface>(`${this.url}/${id}`, {
      headers: this.headers,
    });
  }

  private getTokenStorage(): string {
    const userData = localStorage.getItem('userData');
    if (userData) {
      const parsedData = JSON.parse(userData);
      return parsedData.token;
    }
    return '';
  }
}
