import { Component, input } from '@angular/core';

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
  title = input<string>();
  value = input<number>();
  linkImg = input<string>();
}
