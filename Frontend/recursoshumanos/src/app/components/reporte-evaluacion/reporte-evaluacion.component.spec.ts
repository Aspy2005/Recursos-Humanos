import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReporteEvaluacionComponent } from './reporte-evaluacion.component';

describe('ReporteEvaluacionComponent', () => {
  let component: ReporteEvaluacionComponent;
  let fixture: ComponentFixture<ReporteEvaluacionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReporteEvaluacionComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ReporteEvaluacionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
