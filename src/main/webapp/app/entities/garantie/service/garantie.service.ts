import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable, map } from 'rxjs';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IGarantie, NewGarantie } from '../garantie.model';

export type PartialUpdateGarantie = Partial<IGarantie> & Pick<IGarantie, 'id'>;

type RestOf<T extends IGarantie | NewGarantie> = Omit<T, 'dateAdhesionGarantie' | 'dateRadiationGarantie'> & {
  dateAdhesionGarantie?: string | null;
  dateRadiationGarantie?: string | null;
};

export type RestGarantie = RestOf<IGarantie>;

export type NewRestGarantie = RestOf<NewGarantie>;

export type PartialUpdateRestGarantie = RestOf<PartialUpdateGarantie>;

export type EntityResponseType = HttpResponse<IGarantie>;
export type EntityArrayResponseType = HttpResponse<IGarantie[]>;

@Injectable({ providedIn: 'root' })
export class GarantieService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/garanties');

  create(garantie: NewGarantie): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(garantie);
    return this.http
      .post<RestGarantie>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(garantie: IGarantie): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(garantie);
    return this.http
      .put<RestGarantie>(`${this.resourceUrl}/${this.getGarantieIdentifier(garantie)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(garantie: PartialUpdateGarantie): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(garantie);
    return this.http
      .patch<RestGarantie>(`${this.resourceUrl}/${this.getGarantieIdentifier(garantie)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestGarantie>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestGarantie[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getGarantieIdentifier(garantie: Pick<IGarantie, 'id'>): number {
    return garantie.id;
  }

  compareGarantie(o1: Pick<IGarantie, 'id'> | null, o2: Pick<IGarantie, 'id'> | null): boolean {
    return o1 && o2 ? this.getGarantieIdentifier(o1) === this.getGarantieIdentifier(o2) : o1 === o2;
  }

  addGarantieToCollectionIfMissing<Type extends Pick<IGarantie, 'id'>>(
    garantieCollection: Type[],
    ...garantiesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const garanties: Type[] = garantiesToCheck.filter(isPresent);
    if (garanties.length > 0) {
      const garantieCollectionIdentifiers = garantieCollection.map(garantieItem => this.getGarantieIdentifier(garantieItem));
      const garantiesToAdd = garanties.filter(garantieItem => {
        const garantieIdentifier = this.getGarantieIdentifier(garantieItem);
        if (garantieCollectionIdentifiers.includes(garantieIdentifier)) {
          return false;
        }
        garantieCollectionIdentifiers.push(garantieIdentifier);
        return true;
      });
      return [...garantiesToAdd, ...garantieCollection];
    }
    return garantieCollection;
  }

  protected convertDateFromClient<T extends IGarantie | NewGarantie | PartialUpdateGarantie>(garantie: T): RestOf<T> {
    return {
      ...garantie,
      dateAdhesionGarantie: garantie.dateAdhesionGarantie?.format(DATE_FORMAT) ?? null,
      dateRadiationGarantie: garantie.dateRadiationGarantie?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restGarantie: RestGarantie): IGarantie {
    return {
      ...restGarantie,
      dateAdhesionGarantie: restGarantie.dateAdhesionGarantie ? dayjs(restGarantie.dateAdhesionGarantie) : undefined,
      dateRadiationGarantie: restGarantie.dateRadiationGarantie ? dayjs(restGarantie.dateRadiationGarantie) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestGarantie>): HttpResponse<IGarantie> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestGarantie[]>): HttpResponse<IGarantie[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
