import { Component } from '@angular/core';
import { FoodsService } from '../../services/foods/foods.service';
import { CartComponent } from '../../components/cart/cart.component';
import { FoodInterface } from '../../interfaces/food.interface';
import { CarrousselComponent } from '../../components/carroussel/carroussel.component';

@Component({
  selector: 'app-menu',
  standalone: true,
  imports: [CarrousselComponent, CartComponent],
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.scss',
})
export class MenuComponent {
  protected foods!: FoodInterface[];
  protected filteredFoods!: FoodInterface[];

  constructor(private foodsService: FoodsService) {
    this.initFoods();
  }

  protected filterByType(type: string) {
    this.filterFoods(type);
  }

  protected handleKeyDown(event: KeyboardEvent, type: string): void {
    if (event.key === 'Enter' || event.key === ' ') {
      this.filterFoods(type);
    }
  }

  private initFoods(): void {
    this.foodsService.getAllFoods().subscribe({
      next: (food) => {
        this.foods = food;
        this.filterFoods('salted');
      },
      error: (err) => console.log(err), // Tratar erro de requisição
    });
  }

  private filterFoods(type: string): void {
    if (this.foods) {
      this.filteredFoods = this.foods.filter(
        (food) => food.type.toLowerCase() === type,
      );
    }
  }
}
