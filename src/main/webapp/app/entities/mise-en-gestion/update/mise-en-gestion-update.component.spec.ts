import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { IDemandeXRM } from 'app/entities/demande-xrm/demande-xrm.model';
import { DemandeXRMService } from 'app/entities/demande-xrm/service/demande-xrm.service';
import { IPmEtablissement } from 'app/entities/pm-etablissement/pm-etablissement.model';
import { PmEtablissementService } from 'app/entities/pm-etablissement/service/pm-etablissement.service';
import { IMiseEnGestion } from '../mise-en-gestion.model';
import { MiseEnGestionService } from '../service/mise-en-gestion.service';
import { MiseEnGestionFormService } from './mise-en-gestion-form.service';

import { MiseEnGestionUpdateComponent } from './mise-en-gestion-update.component';

describe('MiseEnGestion Management Update Component', () => {
  let comp: MiseEnGestionUpdateComponent;
  let fixture: ComponentFixture<MiseEnGestionUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let miseEnGestionFormService: MiseEnGestionFormService;
  let miseEnGestionService: MiseEnGestionService;
  let demandeXRMService: DemandeXRMService;
  let pmEtablissementService: PmEtablissementService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [MiseEnGestionUpdateComponent],
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
      .overrideTemplate(MiseEnGestionUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(MiseEnGestionUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    miseEnGestionFormService = TestBed.inject(MiseEnGestionFormService);
    miseEnGestionService = TestBed.inject(MiseEnGestionService);
    demandeXRMService = TestBed.inject(DemandeXRMService);
    pmEtablissementService = TestBed.inject(PmEtablissementService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('should call DemandeXRM query and add missing value', () => {
      const miseEnGestion: IMiseEnGestion = { id: 12141 };
      const demandeXRMS: IDemandeXRM[] = [{ id: 16042 }];
      miseEnGestion.demandeXRMS = demandeXRMS;

      const demandeXRMCollection: IDemandeXRM[] = [{ id: 16042 }];
      jest.spyOn(demandeXRMService, 'query').mockReturnValue(of(new HttpResponse({ body: demandeXRMCollection })));
      const additionalDemandeXRMS = [...demandeXRMS];
      const expectedCollection: IDemandeXRM[] = [...additionalDemandeXRMS, ...demandeXRMCollection];
      jest.spyOn(demandeXRMService, 'addDemandeXRMToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ miseEnGestion });
      comp.ngOnInit();

      expect(demandeXRMService.query).toHaveBeenCalled();
      expect(demandeXRMService.addDemandeXRMToCollectionIfMissing).toHaveBeenCalledWith(
        demandeXRMCollection,
        ...additionalDemandeXRMS.map(expect.objectContaining),
      );
      expect(comp.demandeXRMSSharedCollection).toEqual(expectedCollection);
    });

    it('should call PmEtablissement query and add missing value', () => {
      const miseEnGestion: IMiseEnGestion = { id: 12141 };
      const pmEtablissements: IPmEtablissement[] = [{ id: 16761 }];
      miseEnGestion.pmEtablissements = pmEtablissements;

      const pmEtablissementCollection: IPmEtablissement[] = [{ id: 16761 }];
      jest.spyOn(pmEtablissementService, 'query').mockReturnValue(of(new HttpResponse({ body: pmEtablissementCollection })));
      const additionalPmEtablissements = [...pmEtablissements];
      const expectedCollection: IPmEtablissement[] = [...additionalPmEtablissements, ...pmEtablissementCollection];
      jest.spyOn(pmEtablissementService, 'addPmEtablissementToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ miseEnGestion });
      comp.ngOnInit();

      expect(pmEtablissementService.query).toHaveBeenCalled();
      expect(pmEtablissementService.addPmEtablissementToCollectionIfMissing).toHaveBeenCalledWith(
        pmEtablissementCollection,
        ...additionalPmEtablissements.map(expect.objectContaining),
      );
      expect(comp.pmEtablissementsSharedCollection).toEqual(expectedCollection);
    });

    it('should update editForm', () => {
      const miseEnGestion: IMiseEnGestion = { id: 12141 };
      const demandeXRM: IDemandeXRM = { id: 16042 };
      miseEnGestion.demandeXRMS = [demandeXRM];
      const pmEtablissement: IPmEtablissement = { id: 16761 };
      miseEnGestion.pmEtablissements = [pmEtablissement];

      activatedRoute.data = of({ miseEnGestion });
      comp.ngOnInit();

      expect(comp.demandeXRMSSharedCollection).toContainEqual(demandeXRM);
      expect(comp.pmEtablissementsSharedCollection).toContainEqual(pmEtablissement);
      expect(comp.miseEnGestion).toEqual(miseEnGestion);
    });
  });

  describe('save', () => {
    it('should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IMiseEnGestion>>();
      const miseEnGestion = { id: 9935 };
      jest.spyOn(miseEnGestionFormService, 'getMiseEnGestion').mockReturnValue(miseEnGestion);
      jest.spyOn(miseEnGestionService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ miseEnGestion });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: miseEnGestion }));
      saveSubject.complete();

      // THEN
      expect(miseEnGestionFormService.getMiseEnGestion).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(miseEnGestionService.update).toHaveBeenCalledWith(expect.objectContaining(miseEnGestion));
      expect(comp.isSaving).toEqual(false);
    });

    it('should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IMiseEnGestion>>();
      const miseEnGestion = { id: 9935 };
      jest.spyOn(miseEnGestionFormService, 'getMiseEnGestion').mockReturnValue({ id: null });
      jest.spyOn(miseEnGestionService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ miseEnGestion: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: miseEnGestion }));
      saveSubject.complete();

      // THEN
      expect(miseEnGestionFormService.getMiseEnGestion).toHaveBeenCalled();
      expect(miseEnGestionService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IMiseEnGestion>>();
      const miseEnGestion = { id: 9935 };
      jest.spyOn(miseEnGestionService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ miseEnGestion });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(miseEnGestionService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareDemandeXRM', () => {
      it('should forward to demandeXRMService', () => {
        const entity = { id: 16042 };
        const entity2 = { id: 11627 };
        jest.spyOn(demandeXRMService, 'compareDemandeXRM');
        comp.compareDemandeXRM(entity, entity2);
        expect(demandeXRMService.compareDemandeXRM).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('comparePmEtablissement', () => {
      it('should forward to pmEtablissementService', () => {
        const entity = { id: 16761 };
        const entity2 = { id: 14323 };
        jest.spyOn(pmEtablissementService, 'comparePmEtablissement');
        comp.comparePmEtablissement(entity, entity2);
        expect(pmEtablissementService.comparePmEtablissement).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
