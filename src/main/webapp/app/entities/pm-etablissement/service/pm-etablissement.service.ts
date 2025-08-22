import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable, map } from 'rxjs';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IPmEtablissement, NewPmEtablissement } from '../pm-etablissement.model';

export type PartialUpdatePmEtablissement = Partial<IPmEtablissement> & Pick<IPmEtablissement, 'id'>;

type RestOf<T extends IPmEtablissement | NewPmEtablissement> = Omit<
  T,
  'dateCreationJuridique' | 'dateEtat' | 'dateFermetureJuridique' | 'dateCessationActivite' | 'dateEffectifOfficiel'
> & {
  dateCreationJuridique?: string | null;
  dateEtat?: string | null;
  dateFermetureJuridique?: string | null;
  dateCessationActivite?: string | null;
  dateEffectifOfficiel?: string | null;
};

export type RestPmEtablissement = RestOf<IPmEtablissement>;

export type NewRestPmEtablissement = RestOf<NewPmEtablissement>;

export type PartialUpdateRestPmEtablissement = RestOf<PartialUpdatePmEtablissement>;

export type EntityResponseType = HttpResponse<IPmEtablissement>;
export type EntityArrayResponseType = HttpResponse<IPmEtablissement[]>;

@Injectable({ providedIn: 'root' })
export class PmEtablissementService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/pm-etablissements');

  create(pmEtablissement: NewPmEtablissement): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(pmEtablissement);
    return this.http
      .post<RestPmEtablissement>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(pmEtablissement: IPmEtablissement): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(pmEtablissement);
    return this.http
      .put<RestPmEtablissement>(`${this.resourceUrl}/${this.getPmEtablissementIdentifier(pmEtablissement)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(pmEtablissement: PartialUpdatePmEtablissement): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(pmEtablissement);
    return this.http
      .patch<RestPmEtablissement>(`${this.resourceUrl}/${this.getPmEtablissementIdentifier(pmEtablissement)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestPmEtablissement>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestPmEtablissement[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getPmEtablissementIdentifier(pmEtablissement: Pick<IPmEtablissement, 'id'>): number {
    return pmEtablissement.id;
  }

  comparePmEtablissement(o1: Pick<IPmEtablissement, 'id'> | null, o2: Pick<IPmEtablissement, 'id'> | null): boolean {
    return o1 && o2 ? this.getPmEtablissementIdentifier(o1) === this.getPmEtablissementIdentifier(o2) : o1 === o2;
  }

  addPmEtablissementToCollectionIfMissing<Type extends Pick<IPmEtablissement, 'id'>>(
    pmEtablissementCollection: Type[],
    ...pmEtablissementsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const pmEtablissements: Type[] = pmEtablissementsToCheck.filter(isPresent);
    if (pmEtablissements.length > 0) {
      const pmEtablissementCollectionIdentifiers = pmEtablissementCollection.map(pmEtablissementItem =>
        this.getPmEtablissementIdentifier(pmEtablissementItem),
      );
      const pmEtablissementsToAdd = pmEtablissements.filter(pmEtablissementItem => {
        const pmEtablissementIdentifier = this.getPmEtablissementIdentifier(pmEtablissementItem);
        if (pmEtablissementCollectionIdentifiers.includes(pmEtablissementIdentifier)) {
          return false;
        }
        pmEtablissementCollectionIdentifiers.push(pmEtablissementIdentifier);
        return true;
      });
      return [...pmEtablissementsToAdd, ...pmEtablissementCollection];
    }
    return pmEtablissementCollection;
  }

  protected convertDateFromClient<T extends IPmEtablissement | NewPmEtablissement | PartialUpdatePmEtablissement>(
    pmEtablissement: T,
  ): RestOf<T> {
    return {
      ...pmEtablissement,
      dateCreationJuridique: pmEtablissement.dateCreationJuridique?.format(DATE_FORMAT) ?? null,
      dateEtat: pmEtablissement.dateEtat?.format(DATE_FORMAT) ?? null,
      dateFermetureJuridique: pmEtablissement.dateFermetureJuridique?.format(DATE_FORMAT) ?? null,
      dateCessationActivite: pmEtablissement.dateCessationActivite?.format(DATE_FORMAT) ?? null,
      dateEffectifOfficiel: pmEtablissement.dateEffectifOfficiel?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restPmEtablissement: RestPmEtablissement): IPmEtablissement {
    return {
      ...restPmEtablissement,
      dateCreationJuridique: restPmEtablissement.dateCreationJuridique ? dayjs(restPmEtablissement.dateCreationJuridique) : undefined,
      dateEtat: restPmEtablissement.dateEtat ? dayjs(restPmEtablissement.dateEtat) : undefined,
      dateFermetureJuridique: restPmEtablissement.dateFermetureJuridique ? dayjs(restPmEtablissement.dateFermetureJuridique) : undefined,
      dateCessationActivite: restPmEtablissement.dateCessationActivite ? dayjs(restPmEtablissement.dateCessationActivite) : undefined,
      dateEffectifOfficiel: restPmEtablissement.dateEffectifOfficiel ? dayjs(restPmEtablissement.dateEffectifOfficiel) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestPmEtablissement>): HttpResponse<IPmEtablissement> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestPmEtablissement[]>): HttpResponse<IPmEtablissement[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
