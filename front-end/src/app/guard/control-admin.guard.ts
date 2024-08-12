import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const controlAdminGuard: CanActivateFn = () => {
  const route = inject(Router);
  const userData = localStorage.getItem('userData');

  if (userData) {
    const { role } = JSON.parse(userData) as { role: string };

    if (role.toLowerCase() === 'admin') {
      return true;
    }
  }
  route.navigate(['menu']);
  return false;
};
