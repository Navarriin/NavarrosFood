import { Component } from '@angular/core';
import { CarrousselComponent } from '../../components/carroussel/carroussel.component';
import { CartComponent } from "../../components/cart/cart.component";

@Component({
  selector: 'app-menu',
  standalone: true,
  imports: [CarrousselComponent, CartComponent],
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.scss',
})
export class MenuComponent {}
