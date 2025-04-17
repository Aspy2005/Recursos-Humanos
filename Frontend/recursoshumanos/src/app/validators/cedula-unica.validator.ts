import { AbstractControl, ValidationErrors, AsyncValidatorFn } from '@angular/forms';
import { Observable, of } from 'rxjs';
import { debounceTime, switchMap, map, catchError } from 'rxjs/operators';
import { EmpleadoService } from '../services/empleado.service';

export function cedulaUnicaValidator(empleadoService: EmpleadoService): AsyncValidatorFn {
    return (control: AbstractControl): Observable<ValidationErrors | null> => {
      const cedula = control.value;
      if (!cedula) {
        return of(null);  // Si el valor de la cédula está vacío, no se hace ninguna validación.
      }
  
      return empleadoService.validarCedula(cedula).pipe(
        debounceTime(300),
        switchMap((exists: boolean) => {
          // Si existe, retorna el error
          return exists ? of({ cedulaUnica: 'La cédula ya está registrada' }) : of(null);
        }),
        catchError(() => of(null)) // En caso de error, no bloqueamos el formulario
      );
    };
  }
  
  
