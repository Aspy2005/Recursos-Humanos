import { TestBed } from '@angular/core/testing';

import { VacacionesPermisosService } from './vacaciones-permisos.service';

describe('VacacionesPermisosService', () => {
  let service: VacacionesPermisosService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VacacionesPermisosService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
