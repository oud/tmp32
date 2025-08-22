import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { IContrat } from 'app/entities/contrat/contrat.model';
import { ContratService } from 'app/entities/contrat/service/contrat.service';
import { OperationService } from '../service/operation.service';
import { IOperation } from '../operation.model';
import { OperationFormService } from './operation-form.service';

import { OperationUpdateComponent } from './operation-update.component';

describe('Operation Management Update Component', () => {
  let comp: OperationUpdateComponent;
  let fixture: ComponentFixture<OperationUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let operationFormService: OperationFormService;
  let operationService: OperationService;
  let contratService: ContratService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OperationUpdateComponent],
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
      .overrideTemplate(OperationUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OperationUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    operationFormService = TestBed.inject(OperationFormService);
    operationService = TestBed.inject(OperationService);
    contratService = TestBed.inject(ContratService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('should call Contrat query and add missing value', () => {
      const operation: IOperation = { id: 5986 };
      const contrat: IContrat = { id: 7637 };
      operation.contrat = contrat;

      const contratCollection: IContrat[] = [{ id: 7637 }];
      jest.spyOn(contratService, 'query').mockReturnValue(of(new HttpResponse({ body: contratCollection })));
      const additionalContrats = [contrat];
      const expectedCollection: IContrat[] = [...additionalContrats, ...contratCollection];
      jest.spyOn(contratService, 'addContratToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ operation });
      comp.ngOnInit();

      expect(contratService.query).toHaveBeenCalled();
      expect(contratService.addContratToCollectionIfMissing).toHaveBeenCalledWith(
        contratCollection,
        ...additionalContrats.map(expect.objectContaining),
      );
      expect(comp.contratsSharedCollection).toEqual(expectedCollection);
    });

    it('should update editForm', () => {
      const operation: IOperation = { id: 5986 };
      const contrat: IContrat = { id: 7637 };
      operation.contrat = contrat;

      activatedRoute.data = of({ operation });
      comp.ngOnInit();

      expect(comp.contratsSharedCollection).toContainEqual(contrat);
      expect(comp.operation).toEqual(operation);
    });
  });

  describe('save', () => {
    it('should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOperation>>();
      const operation = { id: 13822 };
      jest.spyOn(operationFormService, 'getOperation').mockReturnValue(operation);
      jest.spyOn(operationService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ operation });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: operation }));
      saveSubject.complete();

      // THEN
      expect(operationFormService.getOperation).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(operationService.update).toHaveBeenCalledWith(expect.objectContaining(operation));
      expect(comp.isSaving).toEqual(false);
    });

    it('should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOperation>>();
      const operation = { id: 13822 };
      jest.spyOn(operationFormService, 'getOperation').mockReturnValue({ id: null });
      jest.spyOn(operationService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ operation: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: operation }));
      saveSubject.complete();

      // THEN
      expect(operationFormService.getOperation).toHaveBeenCalled();
      expect(operationService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOperation>>();
      const operation = { id: 13822 };
      jest.spyOn(operationService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ operation });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(operationService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareContrat', () => {
      it('should forward to contratService', () => {
        const entity = { id: 7637 };
        const entity2 = { id: 25812 };
        jest.spyOn(contratService, 'compareContrat');
        comp.compareContrat(entity, entity2);
        expect(contratService.compareContrat).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
