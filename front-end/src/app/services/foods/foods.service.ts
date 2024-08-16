import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { FoodInterface } from '../../interfaces/food.interface';
import { inject, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class FoodsService {
  private readonly url: string = 'http://localhost:8080/api/foods';

  private http = inject(HttpClient);

  getAllFoods(): Observable<FoodInterface[]> {
    return this.http.get<FoodInterface[]>(this.url);
  }

  getFoodById(id: number): Observable<FoodInterface> {
    return this.http.get<FoodInterface>(`${this.url}/${id}`);
  }

  addNewFood(body: FoodInterface) {
    return this.http.post<FoodInterface>(this.url, body);
  }

  deleteFoodById(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/${id}`);
  }
}
