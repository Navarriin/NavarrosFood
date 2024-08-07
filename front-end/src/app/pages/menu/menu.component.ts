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

  constructor(private foodsService: FoodsService) {
    this.salted();
  }

  protected salted(): void {
    this.lookForFiltredFoods('salted');
  }

  protected sweet(): void {
    this.lookForFiltredFoods('sweet');
  }

  protected handleKeyDownSalted(event: KeyboardEvent): void {
    if (event.key === 'Enter' || event.key === ' ') {
      this.salted();
    }
  }

  protected handleKeyDownSweet(event: KeyboardEvent): void {
    if (event.key === 'Enter' || event.key === ' ') {
      this.sweet();
    }
  }

  private lookForFiltredFoods(filter: string): void {
    this.foodsService.getAllFoods().subscribe({
      next: (foods: FoodInterface[]) => {
        this.foods = foods.filter((food) => food.type.toLowerCase() === filter);
      },
      error: (err) => console.log(err),
    });
  }
}
