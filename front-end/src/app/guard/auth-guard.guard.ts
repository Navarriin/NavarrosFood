import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { StatesService } from '../services/states/states.service';

export const authGuard: CanActivateFn = () => {
  const router = inject(Router);
  const states = inject(StatesService);

  if (states.getAuthState()()) return true;
  else {
    router.navigate(['auth/login']);
    return false;
  }
};
