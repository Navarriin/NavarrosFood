import { Component } from '@angular/core';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [],
  templateUrl: './navbar.component.html',
  styles: `
  :host {
  display: flex;
  justify-content: space-between;

  padding: 1rem 2.5rem;

  background-color: #F9F9F9;
  box-shadow: rgba(0, 0, 0, 0.16) 0px 1px 4px;
}
  `,
})
export class NavbarComponent {}
