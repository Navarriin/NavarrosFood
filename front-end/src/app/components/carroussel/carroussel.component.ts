import { Component, OnInit } from '@angular/core';
import { CarouselModule } from 'primeng/carousel';
import { ButtonModule } from 'primeng/button';
import { TagModule } from 'primeng/tag';
import { CardInterface } from '../../interfaces/card.interface';
import { CardComponent } from '../card/card.component';

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
export class CarrousselComponent implements OnInit {
  cards!: CardInterface[];

  responsiveOptions: Options[] | undefined;

  // constructor() {}

  ngOnInit(): void {
    // this.productService.getProductsSmall().then((products) => {
    //     this.cards = products;
    // });
    console.log("foi")
    this.cards = [
      {
        title: 'Strogonoff',
        value: 22,
        linkImg:
          'https://p2.trrsf.com/image/fget/cf/1200/1200/middle/images.terra.com/2022/08/03/1769155807-receita-de-strogonoff-de-carne-ou-frango-02.jpeg',
      },
      {
        title: 'Macarrão com Queijo',
        value: 22,
        linkImg:
          'https://p2.trrsf.com/image/fget/cf/1200/1200/middle/images.terra.com/2022/08/03/1769155807-receita-de-strogonoff-de-carne-ou-frango-02.jpeg',
      },
      {
        title: 'Test Title',
        value: 22,
        linkImg:
          'https://p2.trrsf.com/image/fget/cf/1200/1200/middle/images.terra.com/2022/08/03/1769155807-receita-de-strogonoff-de-carne-ou-frango-02.jpeg',
      },
      {
        title: 'Macarrão com Queijo',
        value: 22,
        linkImg:
          'https://p2.trrsf.com/image/fget/cf/1200/1200/middle/images.terra.com/2022/08/03/1769155807-receita-de-strogonoff-de-carne-ou-frango-02.jpeg',
      },
      {
        title: 'Test Title',
        value: 22,
        linkImg:
          'https://p2.trrsf.com/image/fget/cf/1200/1200/middle/images.terra.com/2022/08/03/1769155807-receita-de-strogonoff-de-carne-ou-frango-02.jpeg',
      },
      {
        title: 'Macarrão com Queijo',
        value: 22,
        linkImg:
          'https://p2.trrsf.com/image/fget/cf/1200/1200/middle/images.terra.com/2022/08/03/1769155807-receita-de-strogonoff-de-carne-ou-frango-02.jpeg',
      },
      {
        title: 'Test Title',
        value: 22,
        linkImg:
          'https://p2.trrsf.com/image/fget/cf/1200/1200/middle/images.terra.com/2022/08/03/1769155807-receita-de-strogonoff-de-carne-ou-frango-02.jpeg',
      },
      {
        title: 'Macarrão com Queijo',
        value: 22,
        linkImg:
          'https://p2.trrsf.com/image/fget/cf/1200/1200/middle/images.terra.com/2022/08/03/1769155807-receita-de-strogonoff-de-carne-ou-frango-02.jpeg',
      },
      {
        title: 'Test Title',
        value: 22,
        linkImg:
          'https://p2.trrsf.com/image/fget/cf/1200/1200/middle/images.terra.com/2022/08/03/1769155807-receita-de-strogonoff-de-carne-ou-frango-02.jpeg',
      },
    ];

    this.responsiveOptions = [
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
