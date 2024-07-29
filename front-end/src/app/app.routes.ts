import { Routes } from '@angular/router';
import { ErrorComponent } from './pages/error/error.component';

export const routes: Routes = [
  {
    path: 'menu',
    loadComponent: () => import('./pages/menu/menu.component').then(module => module.MenuComponent)
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
