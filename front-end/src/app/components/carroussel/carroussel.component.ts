import { TagModule } from 'primeng/tag';
import { ButtonModule } from 'primeng/button';
import { CardComponent } from '../card/card.component';
import { FoodInterface } from '../../interfaces/food.interface';
import { CarouselModule } from 'primeng/carousel';
import { Component, input } from '@angular/core';

interface Options {
  breakpoint: string;
  numVisible: number;
  numScroll: number;
}

@Component({
  selector: 'app-carroussel',
  standalone: true,
  imports: [CarouselModule, ButtonModule, TagModule, CardComponent],
  templateUrl: './carroussel.component.html',
  styleUrl: './carroussel.component.scss',
})
export class CarrousselComponent {
  cards = input.required<FoodInterface[]>();

  responsiveOptions: Options[] | undefined;  

  constructor() {
    this.responsiveOptions = [  // Ajustar responsividade
      {
        breakpoint: '1199px',
        numVisible: 1,
        numScroll: 1,
      },
      {
        breakpoint: '991px',
        numVisible: 2,
        numScroll: 1,
      },
      {
        breakpoint: '767px',
        numVisible: 1,
        numScroll: 1,
      },
    ];
  }
}
