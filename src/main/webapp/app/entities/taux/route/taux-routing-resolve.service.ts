import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ITaux } from '../taux.model';
import { TauxService } from '../service/taux.service';

const tauxResolve = (route: ActivatedRouteSnapshot): Observable<null | ITaux> => {
  const id = route.params.id;
  if (id) {
    return inject(TauxService)
      .find(id)
      .pipe(
        mergeMap((taux: HttpResponse<ITaux>) => {
          if (taux.body) {
            return of(taux.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default tauxResolve;
