import { Component, input } from '@angular/core';
import { StatesService } from '../../services/states/states.service';

@Component({
  selector: 'app-card',
  standalone: true,
  imports: [],
  templateUrl: './card.component.html',
  styles: `
  :host {
  display: flex;
  flex-direction: column;
  gap: .4rem;

  width: 14rem;
  height: 22rem;
  
  border-radius: .5rem;
  background-color: white;
}
  `,
})
export class CardComponent {
  id = input<number>();
  name = input.required<string>();
  value = input.required<number>();
  image = input<string>(); // Adicionar uma imagem default

  constructor(private state: StatesService) {}

  toAdd(): void {
    this.state.setSelectedCardId(this.id()!);
  }
}
