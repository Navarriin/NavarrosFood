import { Routes } from '@angular/router';
import { ErrorComponent } from './pages/error/error.component';

export const routes: Routes = [
  {
    path: 'menu',
    loadComponent: () => import('./pages/menu/menu.component').then(page => page.MenuComponent)
  },
  {
    path: "auth/:login",
    loadComponent: () => import('./pages/sing-up/sing-up.component').then(page => page.SingUpComponent)
  },
  {
    path: "auth/:register",
    loadComponent: () => import('./pages/sing-up/sing-up.component').then(page => page.SingUpComponent)
  },
  {
    path: 'error',
    component: ErrorComponent
  },
  {
    path: '**',
    redirectTo: 'error'
  }
];
