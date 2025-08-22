import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { IGarantie } from 'app/entities/garantie/garantie.model';
import { GarantieService } from 'app/entities/garantie/service/garantie.service';
import { TauxService } from '../service/taux.service';
import { ITaux } from '../taux.model';
import { TauxFormService } from './taux-form.service';

import { TauxUpdateComponent } from './taux-update.component';

describe('Taux Management Update Component', () => {
  let comp: TauxUpdateComponent;
  let fixture: ComponentFixture<TauxUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let tauxFormService: TauxFormService;
  let tauxService: TauxService;
  let garantieService: GarantieService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [TauxUpdateComponent],
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
      .overrideTemplate(TauxUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(TauxUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    tauxFormService = TestBed.inject(TauxFormService);
    tauxService = TestBed.inject(TauxService);
    garantieService = TestBed.inject(GarantieService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('should call Garantie query and add missing value', () => {
      const taux: ITaux = { id: 14513 };
      const garantie: IGarantie = { id: 28294 };
      taux.garantie = garantie;

      const garantieCollection: IGarantie[] = [{ id: 28294 }];
      jest.spyOn(garantieService, 'query').mockReturnValue(of(new HttpResponse({ body: garantieCollection })));
      const additionalGaranties = [garantie];
      const expectedCollection: IGarantie[] = [...additionalGaranties, ...garantieCollection];
      jest.spyOn(garantieService, 'addGarantieToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ taux });
      comp.ngOnInit();

      expect(garantieService.query).toHaveBeenCalled();
      expect(garantieService.addGarantieToCollectionIfMissing).toHaveBeenCalledWith(
        garantieCollection,
        ...additionalGaranties.map(expect.objectContaining),
      );
      expect(comp.garantiesSharedCollection).toEqual(expectedCollection);
    });

    it('should update editForm', () => {
      const taux: ITaux = { id: 14513 };
      const garantie: IGarantie = { id: 28294 };
      taux.garantie = garantie;

      activatedRoute.data = of({ taux });
      comp.ngOnInit();

      expect(comp.garantiesSharedCollection).toContainEqual(garantie);
      expect(comp.taux).toEqual(taux);
    });
  });

  describe('save', () => {
    it('should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ITaux>>();
      const taux = { id: 25175 };
      jest.spyOn(tauxFormService, 'getTaux').mockReturnValue(taux);
      jest.spyOn(tauxService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ taux });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: taux }));
      saveSubject.complete();

      // THEN
      expect(tauxFormService.getTaux).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(tauxService.update).toHaveBeenCalledWith(expect.objectContaining(taux));
      expect(comp.isSaving).toEqual(false);
    });

    it('should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ITaux>>();
      const taux = { id: 25175 };
      jest.spyOn(tauxFormService, 'getTaux').mockReturnValue({ id: null });
      jest.spyOn(tauxService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ taux: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: taux }));
      saveSubject.complete();

      // THEN
      expect(tauxFormService.getTaux).toHaveBeenCalled();
      expect(tauxService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ITaux>>();
      const taux = { id: 25175 };
      jest.spyOn(tauxService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ taux });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(tauxService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareGarantie', () => {
      it('should forward to garantieService', () => {
        const entity = { id: 28294 };
        const entity2 = { id: 30301 };
        jest.spyOn(garantieService, 'compareGarantie');
        comp.compareGarantie(entity, entity2);
        expect(garantieService.compareGarantie).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
