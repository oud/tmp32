import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { IContrat } from 'app/entities/contrat/contrat.model';
import { ContratService } from 'app/entities/contrat/service/contrat.service';
import { GroupeService } from '../service/groupe.service';
import { IGroupe } from '../groupe.model';
import { GroupeFormService } from './groupe-form.service';

import { GroupeUpdateComponent } from './groupe-update.component';

describe('Groupe Management Update Component', () => {
  let comp: GroupeUpdateComponent;
  let fixture: ComponentFixture<GroupeUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let groupeFormService: GroupeFormService;
  let groupeService: GroupeService;
  let contratService: ContratService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [GroupeUpdateComponent],
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
      .overrideTemplate(GroupeUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(GroupeUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    groupeFormService = TestBed.inject(GroupeFormService);
    groupeService = TestBed.inject(GroupeService);
    contratService = TestBed.inject(ContratService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('should call Contrat query and add missing value', () => {
      const groupe: IGroupe = { id: 17963 };
      const contrat: IContrat = { id: 7637 };
      groupe.contrat = contrat;

      const contratCollection: IContrat[] = [{ id: 7637 }];
      jest.spyOn(contratService, 'query').mockReturnValue(of(new HttpResponse({ body: contratCollection })));
      const additionalContrats = [contrat];
      const expectedCollection: IContrat[] = [...additionalContrats, ...contratCollection];
      jest.spyOn(contratService, 'addContratToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ groupe });
      comp.ngOnInit();

      expect(contratService.query).toHaveBeenCalled();
      expect(contratService.addContratToCollectionIfMissing).toHaveBeenCalledWith(
        contratCollection,
        ...additionalContrats.map(expect.objectContaining),
      );
      expect(comp.contratsSharedCollection).toEqual(expectedCollection);
    });

    it('should update editForm', () => {
      const groupe: IGroupe = { id: 17963 };
      const contrat: IContrat = { id: 7637 };
      groupe.contrat = contrat;

      activatedRoute.data = of({ groupe });
      comp.ngOnInit();

      expect(comp.contratsSharedCollection).toContainEqual(contrat);
      expect(comp.groupe).toEqual(groupe);
    });
  });

  describe('save', () => {
    it('should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IGroupe>>();
      const groupe = { id: 10264 };
      jest.spyOn(groupeFormService, 'getGroupe').mockReturnValue(groupe);
      jest.spyOn(groupeService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ groupe });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: groupe }));
      saveSubject.complete();

      // THEN
      expect(groupeFormService.getGroupe).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(groupeService.update).toHaveBeenCalledWith(expect.objectContaining(groupe));
      expect(comp.isSaving).toEqual(false);
    });

    it('should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IGroupe>>();
      const groupe = { id: 10264 };
      jest.spyOn(groupeFormService, 'getGroupe').mockReturnValue({ id: null });
      jest.spyOn(groupeService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ groupe: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: groupe }));
      saveSubject.complete();

      // THEN
      expect(groupeFormService.getGroupe).toHaveBeenCalled();
      expect(groupeService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IGroupe>>();
      const groupe = { id: 10264 };
      jest.spyOn(groupeService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ groupe });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(groupeService.update).toHaveBeenCalled();
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
