import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable, map } from 'rxjs';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IProduit, NewProduit } from '../produit.model';

export type PartialUpdateProduit = Partial<IProduit> & Pick<IProduit, 'id'>;

type RestOf<T extends IProduit | NewProduit> = Omit<T, 'dateAdhesionProduit' | 'dateRadiationProduit'> & {
  dateAdhesionProduit?: string | null;
  dateRadiationProduit?: string | null;
};

export type RestProduit = RestOf<IProduit>;

export type NewRestProduit = RestOf<NewProduit>;

export type PartialUpdateRestProduit = RestOf<PartialUpdateProduit>;

export type EntityResponseType = HttpResponse<IProduit>;
export type EntityArrayResponseType = HttpResponse<IProduit[]>;

@Injectable({ providedIn: 'root' })
export class ProduitService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/produits');

  create(produit: NewProduit): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(produit);
    return this.http
      .post<RestProduit>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(produit: IProduit): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(produit);
    return this.http
      .put<RestProduit>(`${this.resourceUrl}/${this.getProduitIdentifier(produit)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(produit: PartialUpdateProduit): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(produit);
    return this.http
      .patch<RestProduit>(`${this.resourceUrl}/${this.getProduitIdentifier(produit)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestProduit>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestProduit[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getProduitIdentifier(produit: Pick<IProduit, 'id'>): number {
    return produit.id;
  }

  compareProduit(o1: Pick<IProduit, 'id'> | null, o2: Pick<IProduit, 'id'> | null): boolean {
    return o1 && o2 ? this.getProduitIdentifier(o1) === this.getProduitIdentifier(o2) : o1 === o2;
  }

  addProduitToCollectionIfMissing<Type extends Pick<IProduit, 'id'>>(
    produitCollection: Type[],
    ...produitsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const produits: Type[] = produitsToCheck.filter(isPresent);
    if (produits.length > 0) {
      const produitCollectionIdentifiers = produitCollection.map(produitItem => this.getProduitIdentifier(produitItem));
      const produitsToAdd = produits.filter(produitItem => {
        const produitIdentifier = this.getProduitIdentifier(produitItem);
        if (produitCollectionIdentifiers.includes(produitIdentifier)) {
          return false;
        }
        produitCollectionIdentifiers.push(produitIdentifier);
        return true;
      });
      return [...produitsToAdd, ...produitCollection];
    }
    return produitCollection;
  }

  protected convertDateFromClient<T extends IProduit | NewProduit | PartialUpdateProduit>(produit: T): RestOf<T> {
    return {
      ...produit,
      dateAdhesionProduit: produit.dateAdhesionProduit?.format(DATE_FORMAT) ?? null,
      dateRadiationProduit: produit.dateRadiationProduit?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restProduit: RestProduit): IProduit {
    return {
      ...restProduit,
      dateAdhesionProduit: restProduit.dateAdhesionProduit ? dayjs(restProduit.dateAdhesionProduit) : undefined,
      dateRadiationProduit: restProduit.dateRadiationProduit ? dayjs(restProduit.dateRadiationProduit) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestProduit>): HttpResponse<IProduit> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestProduit[]>): HttpResponse<IProduit[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
