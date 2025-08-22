import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { IPmEtablissement } from 'app/entities/pm-etablissement/pm-etablissement.model';
import { PmEtablissementService } from 'app/entities/pm-etablissement/service/pm-etablissement.service';
import { EmailService } from '../service/email.service';
import { IEmail } from '../email.model';
import { EmailFormService } from './email-form.service';

import { EmailUpdateComponent } from './email-update.component';

describe('Email Management Update Component', () => {
  let comp: EmailUpdateComponent;
  let fixture: ComponentFixture<EmailUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let emailFormService: EmailFormService;
  let emailService: EmailService;
  let pmEtablissementService: PmEtablissementService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [EmailUpdateComponent],
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
      .overrideTemplate(EmailUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(EmailUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    emailFormService = TestBed.inject(EmailFormService);
    emailService = TestBed.inject(EmailService);
    pmEtablissementService = TestBed.inject(PmEtablissementService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('should call PmEtablissement query and add missing value', () => {
      const email: IEmail = { id: 30632 };
      const pmEtablissement: IPmEtablissement = { id: 16761 };
      email.pmEtablissement = pmEtablissement;

      const pmEtablissementCollection: IPmEtablissement[] = [{ id: 16761 }];
      jest.spyOn(pmEtablissementService, 'query').mockReturnValue(of(new HttpResponse({ body: pmEtablissementCollection })));
      const additionalPmEtablissements = [pmEtablissement];
      const expectedCollection: IPmEtablissement[] = [...additionalPmEtablissements, ...pmEtablissementCollection];
      jest.spyOn(pmEtablissementService, 'addPmEtablissementToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ email });
      comp.ngOnInit();

      expect(pmEtablissementService.query).toHaveBeenCalled();
      expect(pmEtablissementService.addPmEtablissementToCollectionIfMissing).toHaveBeenCalledWith(
        pmEtablissementCollection,
        ...additionalPmEtablissements.map(expect.objectContaining),
      );
      expect(comp.pmEtablissementsSharedCollection).toEqual(expectedCollection);
    });

    it('should update editForm', () => {
      const email: IEmail = { id: 30632 };
      const pmEtablissement: IPmEtablissement = { id: 16761 };
      email.pmEtablissement = pmEtablissement;

      activatedRoute.data = of({ email });
      comp.ngOnInit();

      expect(comp.pmEtablissementsSharedCollection).toContainEqual(pmEtablissement);
      expect(comp.email).toEqual(email);
    });
  });

  describe('save', () => {
    it('should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IEmail>>();
      const email = { id: 20273 };
      jest.spyOn(emailFormService, 'getEmail').mockReturnValue(email);
      jest.spyOn(emailService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ email });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: email }));
      saveSubject.complete();

      // THEN
      expect(emailFormService.getEmail).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(emailService.update).toHaveBeenCalledWith(expect.objectContaining(email));
      expect(comp.isSaving).toEqual(false);
    });

    it('should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IEmail>>();
      const email = { id: 20273 };
      jest.spyOn(emailFormService, 'getEmail').mockReturnValue({ id: null });
      jest.spyOn(emailService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ email: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: email }));
      saveSubject.complete();

      // THEN
      expect(emailFormService.getEmail).toHaveBeenCalled();
      expect(emailService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IEmail>>();
      const email = { id: 20273 };
      jest.spyOn(emailService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ email });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(emailService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
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
