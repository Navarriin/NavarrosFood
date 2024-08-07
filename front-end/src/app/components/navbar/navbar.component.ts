import { Component, Signal } from '@angular/core';
import { RouterLink } from '@angular/router';
import { StatesService } from '../../services/states/states.service';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [RouterLink],
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
export class NavbarComponent {
  protected login: Signal<boolean>;
  protected name: Signal<string>;

  constructor(private states: StatesService) {
    this.login = this.states.getAuthState();
    this.name = this.states.getNameSubject();
  }

  logout(): void {
    localStorage.removeItem('userData');
    this.states.setAuthState(false);
  }
}
