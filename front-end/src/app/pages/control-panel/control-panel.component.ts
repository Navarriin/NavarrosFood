import { Component } from '@angular/core';
import { FoodListComponent } from './components/food-list/food-list.component';

@Component({
  selector: 'app-control-panel',
  standalone: true,
  imports: [FoodListComponent],
  templateUrl: './control-panel.component.html',
  styleUrl: './control-panel.component.scss',
})
export class ControlPanelComponent {}
