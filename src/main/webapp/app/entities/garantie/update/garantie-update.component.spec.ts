import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { IProduit } from 'app/entities/produit/produit.model';
import { ProduitService } from 'app/entities/produit/service/produit.service';
import { GarantieService } from '../service/garantie.service';
import { IGarantie } from '../garantie.model';
import { GarantieFormService } from './garantie-form.service';

import { GarantieUpdateComponent } from './garantie-update.component';

describe('Garantie Management Update Component', () => {
  let comp: GarantieUpdateComponent;
  let fixture: ComponentFixture<GarantieUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let garantieFormService: GarantieFormService;
  let garantieService: GarantieService;
  let produitService: ProduitService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [GarantieUpdateComponent],
      providers: [
        provideHttpClient(),
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(GarantieUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(GarantieUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    garantieFormService = TestBed.inject(GarantieFormService);
    garantieService = TestBed.inject(GarantieService);
    produitService = TestBed.inject(ProduitService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('should call Produit query and add missing value', () => {
      const garantie: IGarantie = { id: 30301 };
      const produit: IProduit = { id: 28529 };
      garantie.produit = produit;

      const produitCollection: IProduit[] = [{ id: 28529 }];
      jest.spyOn(produitService, 'query').mockReturnValue(of(new HttpResponse({ body: produitCollection })));
      const additionalProduits = [produit];
      const expectedCollection: IProduit[] = [...additionalProduits, ...produitCollection];
      jest.spyOn(produitService, 'addProduitToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ garantie });
      comp.ngOnInit();

      expect(produitService.query).toHaveBeenCalled();
      expect(produitService.addProduitToCollectionIfMissing).toHaveBeenCalledWith(
        produitCollection,
        ...additionalProduits.map(expect.objectContaining),
      );
      expect(comp.produitsSharedCollection).toEqual(expectedCollection);
    });

    it('should update editForm', () => {
      const garantie: IGarantie = { id: 30301 };
      const produit: IProduit = { id: 28529 };
      garantie.produit = produit;

      activatedRoute.data = of({ garantie });
      comp.ngOnInit();

      expect(comp.produitsSharedCollection).toContainEqual(produit);
      expect(comp.garantie).toEqual(garantie);
    });
  });

  describe('save', () => {
    it('should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IGarantie>>();
      const garantie = { id: 28294 };
      jest.spyOn(garantieFormService, 'getGarantie').mockReturnValue(garantie);
      jest.spyOn(garantieService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ garantie });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: garantie }));
      saveSubject.complete();

      // THEN
      expect(garantieFormService.getGarantie).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(garantieService.update).toHaveBeenCalledWith(expect.objectContaining(garantie));
      expect(comp.isSaving).toEqual(false);
    });

    it('should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IGarantie>>();
      const garantie = { id: 28294 };
      jest.spyOn(garantieFormService, 'getGarantie').mockReturnValue({ id: null });
      jest.spyOn(garantieService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ garantie: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: garantie }));
      saveSubject.complete();

      // THEN
      expect(garantieFormService.getGarantie).toHaveBeenCalled();
      expect(garantieService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IGarantie>>();
      const garantie = { id: 28294 };
      jest.spyOn(garantieService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ garantie });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(garantieService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareProduit', () => {
      it('should forward to produitService', () => {
        const entity = { id: 28529 };
        const entity2 = { id: 21239 };
        jest.spyOn(produitService, 'compareProduit');
        comp.compareProduit(entity, entity2);
        expect(produitService.compareProduit).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
