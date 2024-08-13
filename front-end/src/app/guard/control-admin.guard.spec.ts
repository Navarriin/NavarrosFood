import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';

import { controlAdminGuard } from './control-admin.guard';

describe('controlAdminGuard', () => {
  const executeGuard: CanActivateFn = (...guardParameters) => 
      TestBed.runInInjectionContext(() => controlAdminGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
