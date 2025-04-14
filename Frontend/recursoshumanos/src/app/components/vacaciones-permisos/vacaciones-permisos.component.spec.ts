import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VacacionesPermisosComponent } from './vacaciones-permisos.component';

describe('VacacionesPermisosComponent', () => {
  let component: VacacionesPermisosComponent;
  let fixture: ComponentFixture<VacacionesPermisosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VacacionesPermisosComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(VacacionesPermisosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
