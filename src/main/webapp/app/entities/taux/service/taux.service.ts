import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ITaux, NewTaux } from '../taux.model';

export type PartialUpdateTaux = Partial<ITaux> & Pick<ITaux, 'id'>;

export type EntityResponseType = HttpResponse<ITaux>;
export type EntityArrayResponseType = HttpResponse<ITaux[]>;

@Injectable({ providedIn: 'root' })
export class TauxService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/tauxes');

  create(taux: NewTaux): Observable<EntityResponseType> {
    return this.http.post<ITaux>(this.resourceUrl, taux, { observe: 'response' });
  }

  update(taux: ITaux): Observable<EntityResponseType> {
    return this.http.put<ITaux>(`${this.resourceUrl}/${this.getTauxIdentifier(taux)}`, taux, { observe: 'response' });
  }

  partialUpdate(taux: PartialUpdateTaux): Observable<EntityResponseType> {
    return this.http.patch<ITaux>(`${this.resourceUrl}/${this.getTauxIdentifier(taux)}`, taux, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITaux>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITaux[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getTauxIdentifier(taux: Pick<ITaux, 'id'>): number {
    return taux.id;
  }

  compareTaux(o1: Pick<ITaux, 'id'> | null, o2: Pick<ITaux, 'id'> | null): boolean {
    return o1 && o2 ? this.getTauxIdentifier(o1) === this.getTauxIdentifier(o2) : o1 === o2;
  }

  addTauxToCollectionIfMissing<Type extends Pick<ITaux, 'id'>>(
    tauxCollection: Type[],
    ...tauxesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const tauxes: Type[] = tauxesToCheck.filter(isPresent);
    if (tauxes.length > 0) {
      const tauxCollectionIdentifiers = tauxCollection.map(tauxItem => this.getTauxIdentifier(tauxItem));
      const tauxesToAdd = tauxes.filter(tauxItem => {
        const tauxIdentifier = this.getTauxIdentifier(tauxItem);
        if (tauxCollectionIdentifiers.includes(tauxIdentifier)) {
          return false;
        }
        tauxCollectionIdentifiers.push(tauxIdentifier);
        return true;
      });
      return [...tauxesToAdd, ...tauxCollection];
    }
    return tauxCollection;
  }
}
