import { Component } from '@angular/core';
import { debounceTime } from 'rxjs';
import { FoodsService } from '../../../../services/foods/foods.service';
import { FoodInterface } from '../../../../interfaces/food.interface';
import { FormControl, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-food-list',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './food-list.component.html',
  styleUrl: './food-list.component.scss',
})
export class FoodListComponent {
  protected foods!: FoodInterface[];
  protected filteredFoods!: FoodInterface[];

  protected search = new FormControl('');
  private timeoutId: any = null;

  constructor(private foodsService: FoodsService) {
    this.initFoods();
    this.initSearch();
  }

  protected onSubmit(event: Event): void {
    event.preventDefault();
  }

  private initFoods(): void {
    this.foodsService.getAllFoods().subscribe({
      next: (food) => {
        this.foods = food;
        this.filteredFoods = food;
      },
      error: (err) => console.log(err), // Tratar possivel erro
    });
  }

  private initSearch() {
    this.search.valueChanges.pipe(debounceTime(250)).subscribe((value) => {
      this.filterFoods(value);

      if (this.timeoutId) {
        clearTimeout(this.timeoutId);
      }

      this.timeoutId = setTimeout(() => {
        this.search.setValue('');
        this.filteredFoods = this.foods;
      }, 1000 * 10);
    });
  }

  private filterFoods(searchTerm: string | null): void {
    if (this.search && searchTerm) {
      this.filteredFoods = this.foods.filter((f) =>
        f.name.toLowerCase().includes(searchTerm.toLowerCase())
      );
    }
  }
}
