import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    loadComponent: () => import('./pages/menu/menu.component').then(module => module.MenuComponent)
  }
];
