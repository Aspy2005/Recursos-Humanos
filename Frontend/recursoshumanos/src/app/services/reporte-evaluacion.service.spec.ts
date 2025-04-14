import { TestBed } from '@angular/core/testing';

import { ReporteEvaluacionService } from './reporte-evaluacion.service';

describe('ReporteEvaluacionService', () => {
  let service: ReporteEvaluacionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ReporteEvaluacionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
