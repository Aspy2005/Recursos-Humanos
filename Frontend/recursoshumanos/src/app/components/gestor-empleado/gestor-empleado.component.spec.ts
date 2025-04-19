import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GestorEmpleadoComponent } from './gestor-empleado.component';

describe('GestorEmpleadoComponent', () => {
  let component: GestorEmpleadoComponent;
  let fixture: ComponentFixture<GestorEmpleadoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GestorEmpleadoComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(GestorEmpleadoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
