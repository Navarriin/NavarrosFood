import { Component } from '@angular/core';
import { FoodsService } from '../../../../services/foods/foods.service';
import { FoodInterface } from '../../../../interfaces/food.interface';

@Component({
  selector: 'app-food-list',
  standalone: true,
  imports: [],
  templateUrl: './food-list.component.html',
  styleUrl: './food-list.component.scss',
})
export class FoodListComponent {
  protected foods!: FoodInterface[];

  constructor(private foodsService: FoodsService) {
    this.initFoods();
  }

  private initFoods(): void {
    this.foodsService.getAllFoods().subscribe({
      next: (food) => (this.foods = food),
      error: (err) => console.log(err), // Tratar possivel erro
    });
  }
}
