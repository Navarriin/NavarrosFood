import { Component } from '@angular/core';
import { CarrousselComponent } from '../../components/carroussel/carroussel.component';
import { CartComponent } from '../../components/cart/cart.component';
import { FoodsService } from '../../services/foods.service';
import { FoodInterface } from '../../interfaces/food.interface';

@Component({
  selector: 'app-menu',
  standalone: true,
  imports: [CarrousselComponent, CartComponent],
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.scss',
})
export class MenuComponent {
  protected salted!: FoodInterface[];
  protected sweet!: FoodInterface[];

  constructor(private foodsService: FoodsService) {
    this.initFoods();
  }

  private initFoods(): void {
    this.foodsService.getAllFoods().subscribe({
      next: (foods: FoodInterface[]) => this.filterFoods(foods),
      error: (err) => console.log('Erro ao carregar comidas', err),
    });
  }

  private filterFoods(foods: FoodInterface[]): void {
    this.salted = [];
    this.sweet = [];

    for (const food of foods) {
      const type = food.type.toLowerCase();

      switch (type) {
        case 'salted':
          this.salted.push(food);
          break;
        case 'sweet':
          this.sweet.push(food);
          break;
      }
    }
  }
}
