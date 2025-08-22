import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { IGroupe } from 'app/entities/groupe/groupe.model';
import { GroupeService } from 'app/entities/groupe/service/groupe.service';
import { ProduitService } from '../service/produit.service';
import { IProduit } from '../produit.model';
import { ProduitFormService } from './produit-form.service';

import { ProduitUpdateComponent } from './produit-update.component';

describe('Produit Management Update Component', () => {
  let comp: ProduitUpdateComponent;
  let fixture: ComponentFixture<ProduitUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let produitFormService: ProduitFormService;
  let produitService: ProduitService;
  let groupeService: GroupeService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [ProduitUpdateComponent],
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
      .overrideTemplate(ProduitUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ProduitUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    produitFormService = TestBed.inject(ProduitFormService);
    produitService = TestBed.inject(ProduitService);
    groupeService = TestBed.inject(GroupeService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('should call Groupe query and add missing value', () => {
      const produit: IProduit = { id: 21239 };
      const groupe: IGroupe = { id: 10264 };
      produit.groupe = groupe;

      const groupeCollection: IGroupe[] = [{ id: 10264 }];
      jest.spyOn(groupeService, 'query').mockReturnValue(of(new HttpResponse({ body: groupeCollection })));
      const additionalGroupes = [groupe];
      const expectedCollection: IGroupe[] = [...additionalGroupes, ...groupeCollection];
      jest.spyOn(groupeService, 'addGroupeToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ produit });
      comp.ngOnInit();

      expect(groupeService.query).toHaveBeenCalled();
      expect(groupeService.addGroupeToCollectionIfMissing).toHaveBeenCalledWith(
        groupeCollection,
        ...additionalGroupes.map(expect.objectContaining),
      );
      expect(comp.groupesSharedCollection).toEqual(expectedCollection);
    });

    it('should update editForm', () => {
      const produit: IProduit = { id: 21239 };
      const groupe: IGroupe = { id: 10264 };
      produit.groupe = groupe;

      activatedRoute.data = of({ produit });
      comp.ngOnInit();

      expect(comp.groupesSharedCollection).toContainEqual(groupe);
      expect(comp.produit).toEqual(produit);
    });
  });

  describe('save', () => {
    it('should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IProduit>>();
      const produit = { id: 28529 };
      jest.spyOn(produitFormService, 'getProduit').mockReturnValue(produit);
      jest.spyOn(produitService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ produit });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: produit }));
      saveSubject.complete();

      // THEN
      expect(produitFormService.getProduit).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(produitService.update).toHaveBeenCalledWith(expect.objectContaining(produit));
      expect(comp.isSaving).toEqual(false);
    });

    it('should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IProduit>>();
      const produit = { id: 28529 };
      jest.spyOn(produitFormService, 'getProduit').mockReturnValue({ id: null });
      jest.spyOn(produitService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ produit: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: produit }));
      saveSubject.complete();

      // THEN
      expect(produitFormService.getProduit).toHaveBeenCalled();
      expect(produitService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IProduit>>();
      const produit = { id: 28529 };
      jest.spyOn(produitService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ produit });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(produitService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareGroupe', () => {
      it('should forward to groupeService', () => {
        const entity = { id: 10264 };
        const entity2 = { id: 17963 };
        jest.spyOn(groupeService, 'compareGroupe');
        comp.compareGroupe(entity, entity2);
        expect(groupeService.compareGroupe).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
