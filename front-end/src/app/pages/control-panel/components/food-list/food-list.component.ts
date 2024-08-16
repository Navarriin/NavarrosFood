import { Component } from '@angular/core';
import { debounceTime } from 'rxjs';
import { FoodsService } from '../../../../services/foods/foods.service';
import { FoodInterface } from '../../../../interfaces/food.interface';
import {
  FormGroup,
  FormControl,
  ReactiveFormsModule,
  FormBuilder,
} from '@angular/forms';

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

  protected list = true;

  protected form: FormGroup<{
    id?: FormControl<number | null>;
    name: FormControl<string | null>;
    image: FormControl<string | null>;
    value: FormControl<number | null>;
    type: FormControl<string | null>;
  }>;

  constructor(
    private foodsService: FoodsService,
    private formBuilder: FormBuilder
  ) {
    this.initFoods();
    this.initSearch();

    this.form = this.formBuilder.group({
      // id: [0],
      name: [''],
      image: [''],
      value: [0],
      type: [''],
    });
  }

  protected submit() {
    if (this.form.valid) {
      const value = this.form.value as FoodInterface;
      this.foodsService.addNewFood(value).subscribe({
        next: (response) => {
          console.log('foi', response);
          this.list = true;
          this.form.reset();
          this.initFoods();
        },
        error: (err) => console.log(err), // Tratar erro
      });
    }
  }

  protected onSubmit(event: Event): void {
    event.preventDefault();
  }

  protected edit() {
    this.list = false;

    // Preencher esse form com os dados daquela food
    console.log('editando');
  }

  protected deleteFoodById(id: number) {
    this.foodsService.deleteFoodById(id).subscribe({
      next: () => this.initFoods(),
      error: (err) => console.log(err), // Tratar possivel erro
    });
  }

  handleKeyDown(event: Event) {
    console.log('eids', event);
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
