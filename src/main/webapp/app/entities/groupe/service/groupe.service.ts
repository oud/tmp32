import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable, map } from 'rxjs';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IGroupe, NewGroupe } from '../groupe.model';

export type PartialUpdateGroupe = Partial<IGroupe> & Pick<IGroupe, 'id'>;

type RestOf<T extends IGroupe | NewGroupe> = Omit<T, 'dateDebutPeriodeGroupeAssures'> & {
  dateDebutPeriodeGroupeAssures?: string | null;
};

export type RestGroupe = RestOf<IGroupe>;

export type NewRestGroupe = RestOf<NewGroupe>;

export type PartialUpdateRestGroupe = RestOf<PartialUpdateGroupe>;

export type EntityResponseType = HttpResponse<IGroupe>;
export type EntityArrayResponseType = HttpResponse<IGroupe[]>;

@Injectable({ providedIn: 'root' })
export class GroupeService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/groupes');

  create(groupe: NewGroupe): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(groupe);
    return this.http
      .post<RestGroupe>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(groupe: IGroupe): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(groupe);
    return this.http
      .put<RestGroupe>(`${this.resourceUrl}/${this.getGroupeIdentifier(groupe)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(groupe: PartialUpdateGroupe): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(groupe);
    return this.http
      .patch<RestGroupe>(`${this.resourceUrl}/${this.getGroupeIdentifier(groupe)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestGroupe>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestGroupe[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getGroupeIdentifier(groupe: Pick<IGroupe, 'id'>): number {
    return groupe.id;
  }

  compareGroupe(o1: Pick<IGroupe, 'id'> | null, o2: Pick<IGroupe, 'id'> | null): boolean {
    return o1 && o2 ? this.getGroupeIdentifier(o1) === this.getGroupeIdentifier(o2) : o1 === o2;
  }

  addGroupeToCollectionIfMissing<Type extends Pick<IGroupe, 'id'>>(
    groupeCollection: Type[],
    ...groupesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const groupes: Type[] = groupesToCheck.filter(isPresent);
    if (groupes.length > 0) {
      const groupeCollectionIdentifiers = groupeCollection.map(groupeItem => this.getGroupeIdentifier(groupeItem));
      const groupesToAdd = groupes.filter(groupeItem => {
        const groupeIdentifier = this.getGroupeIdentifier(groupeItem);
        if (groupeCollectionIdentifiers.includes(groupeIdentifier)) {
          return false;
        }
        groupeCollectionIdentifiers.push(groupeIdentifier);
        return true;
      });
      return [...groupesToAdd, ...groupeCollection];
    }
    return groupeCollection;
  }

  protected convertDateFromClient<T extends IGroupe | NewGroupe | PartialUpdateGroupe>(groupe: T): RestOf<T> {
    return {
      ...groupe,
      dateDebutPeriodeGroupeAssures: groupe.dateDebutPeriodeGroupeAssures?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restGroupe: RestGroupe): IGroupe {
    return {
      ...restGroupe,
      dateDebutPeriodeGroupeAssures: restGroupe.dateDebutPeriodeGroupeAssures ? dayjs(restGroupe.dateDebutPeriodeGroupeAssures) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestGroupe>): HttpResponse<IGroupe> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestGroupe[]>): HttpResponse<IGroupe[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
