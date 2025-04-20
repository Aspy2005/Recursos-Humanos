import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SolicitudVacacionPermisoComponent } from './solicitud-vacacion-permiso.component';

describe('SolicitudVacacionPermisoComponent', () => {
  let component: SolicitudVacacionPermisoComponent;
  let fixture: ComponentFixture<SolicitudVacacionPermisoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SolicitudVacacionPermisoComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SolicitudVacacionPermisoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
