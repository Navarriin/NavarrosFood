import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { FoodInterface } from '../interfaces/food.interface';

@Injectable({
  providedIn: 'root'
})
export class FoodsService {
  private readonly url: string = 'http://localhost:8080/api/foods'
  private token!: string;

  constructor(private http: HttpClient) {
    this.getTokenStorage();
  }

  private getTokenStorage() {
    const userData = localStorage.getItem('userData');
    const parsedData = JSON.parse(userData!);
    this.token = parsedData.token;
  }

  getAllFoods(): Observable<FoodInterface[]> {
    const headers = new HttpHeaders({
       'Authorization': `Bearer ${this.token}`
    })

    return this.http.get<FoodInterface[]>(this.url, { headers });
  }
}
