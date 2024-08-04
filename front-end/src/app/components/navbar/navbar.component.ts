import { Component, OnDestroy, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { Observable, Subscription } from 'rxjs';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule, RouterLink],
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
export class NavbarComponent implements OnInit, OnDestroy {
  protected login$: Observable<boolean> = new Observable<boolean>();
  protected name$: Observable<string> = new Observable<string>();
  private nameSubscription!: Subscription;

  constructor(private authService: AuthService) {}

  ngOnInit(): void {
    this.login$ = this.authService.isLoggedinIn;
    this.name$ = this.authService.getName;
  }

  ngOnDestroy(): void {
    if (this.nameSubscription) {
      this.nameSubscription.unsubscribe();
    }
  }

  logout(): void {
    this.authService.logout();
  }
}
