import { FoodsService } from '../../services/foods/foods.service';
import { StatesService } from '../../services/states/states.service';
import { FoodInterface } from '../../interfaces/food.interface';
import { Component, effect } from '@angular/core';

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [],
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.scss',
})
export class CartComponent {
  protected foods: FoodInterface[] = [];

  constructor(
    private states: StatesService,
    private foodsService: FoodsService
  ) {
    effect(() => this.updateListOfFoods());
  }

  protected clear(): void {
    this.foods = [];
  }

  private updateListOfFoods(): void {
    const foodId = this.states.getSelectedCardId()();

    if (foodId != null) {
      this.foodsService.getFoodById(foodId).subscribe({
        next: (food) => this.checksFoods(food),
        error: (err) => console.log(err), // Pensar em oq fazer caso der erro
      });
    }
  }

  private checksFoods(food: FoodInterface): void {
    if (this.foods.some((f) => f.id === food.id))
      console.log('Comida ja adicionada');
    // Pensar em oq fazer caso ja existir a comida no carrinho
    else this.foods.push(food);
  }
}
