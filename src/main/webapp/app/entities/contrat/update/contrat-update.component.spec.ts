import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { IPmEntreprise } from 'app/entities/pm-entreprise/pm-entreprise.model';
import { PmEntrepriseService } from 'app/entities/pm-entreprise/service/pm-entreprise.service';
import { ContratService } from '../service/contrat.service';
import { IContrat } from '../contrat.model';
import { ContratFormService } from './contrat-form.service';

import { ContratUpdateComponent } from './contrat-update.component';

describe('Contrat Management Update Component', () => {
  let comp: ContratUpdateComponent;
  let fixture: ComponentFixture<ContratUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let contratFormService: ContratFormService;
  let contratService: ContratService;
  let pmEntrepriseService: PmEntrepriseService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [ContratUpdateComponent],
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
      .overrideTemplate(ContratUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ContratUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    contratFormService = TestBed.inject(ContratFormService);
    contratService = TestBed.inject(ContratService);
    pmEntrepriseService = TestBed.inject(PmEntrepriseService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('should call pmEntreprise query and add missing value', () => {
      const contrat: IContrat = { id: 25812 };
      const pmEntreprise: IPmEntreprise = { id: 16964 };
      contrat.pmEntreprise = pmEntreprise;

      const pmEntrepriseCollection: IPmEntreprise[] = [{ id: 16964 }];
      jest.spyOn(pmEntrepriseService, 'query').mockReturnValue(of(new HttpResponse({ body: pmEntrepriseCollection })));
      const expectedCollection: IPmEntreprise[] = [pmEntreprise, ...pmEntrepriseCollection];
      jest.spyOn(pmEntrepriseService, 'addPmEntrepriseToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ contrat });
      comp.ngOnInit();

      expect(pmEntrepriseService.query).toHaveBeenCalled();
      expect(pmEntrepriseService.addPmEntrepriseToCollectionIfMissing).toHaveBeenCalledWith(pmEntrepriseCollection, pmEntreprise);
      expect(comp.pmEntreprisesCollection).toEqual(expectedCollection);
    });

    it('should update editForm', () => {
      const contrat: IContrat = { id: 25812 };
      const pmEntreprise: IPmEntreprise = { id: 16964 };
      contrat.pmEntreprise = pmEntreprise;

      activatedRoute.data = of({ contrat });
      comp.ngOnInit();

      expect(comp.pmEntreprisesCollection).toContainEqual(pmEntreprise);
      expect(comp.contrat).toEqual(contrat);
    });
  });

  describe('save', () => {
    it('should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IContrat>>();
      const contrat = { id: 7637 };
      jest.spyOn(contratFormService, 'getContrat').mockReturnValue(contrat);
      jest.spyOn(contratService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ contrat });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: contrat }));
      saveSubject.complete();

      // THEN
      expect(contratFormService.getContrat).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(contratService.update).toHaveBeenCalledWith(expect.objectContaining(contrat));
      expect(comp.isSaving).toEqual(false);
    });

    it('should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IContrat>>();
      const contrat = { id: 7637 };
      jest.spyOn(contratFormService, 'getContrat').mockReturnValue({ id: null });
      jest.spyOn(contratService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ contrat: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: contrat }));
      saveSubject.complete();

      // THEN
      expect(contratFormService.getContrat).toHaveBeenCalled();
      expect(contratService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IContrat>>();
      const contrat = { id: 7637 };
      jest.spyOn(contratService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ contrat });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(contratService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('comparePmEntreprise', () => {
      it('should forward to pmEntrepriseService', () => {
        const entity = { id: 16964 };
        const entity2 = { id: 18362 };
        jest.spyOn(pmEntrepriseService, 'comparePmEntreprise');
        comp.comparePmEntreprise(entity, entity2);
        expect(pmEntrepriseService.comparePmEntreprise).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
