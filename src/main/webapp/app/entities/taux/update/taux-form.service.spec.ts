import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../taux.test-samples';

import { TauxFormService } from './taux-form.service';

describe('Taux Form Service', () => {
  let service: TauxFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TauxFormService);
  });

  describe('Service methods', () => {
    describe('createTauxFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createTauxFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            codeVariableDeclarative: expect.any(Object),
            uniteVariableDeclarative: expect.any(Object),
            valeurFacteurMontant: expect.any(Object),
            valeurFacteurTaux: expect.any(Object),
            garantie: expect.any(Object),
          }),
        );
      });

      it('passing ITaux should create a new form with FormGroup', () => {
        const formGroup = service.createTauxFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            codeVariableDeclarative: expect.any(Object),
            uniteVariableDeclarative: expect.any(Object),
            valeurFacteurMontant: expect.any(Object),
            valeurFacteurTaux: expect.any(Object),
            garantie: expect.any(Object),
          }),
        );
      });
    });

    describe('getTaux', () => {
      it('should return NewTaux for default Taux initial value', () => {
        const formGroup = service.createTauxFormGroup(sampleWithNewData);

        const taux = service.getTaux(formGroup) as any;

        expect(taux).toMatchObject(sampleWithNewData);
      });

      it('should return NewTaux for empty Taux initial value', () => {
        const formGroup = service.createTauxFormGroup();

        const taux = service.getTaux(formGroup) as any;

        expect(taux).toMatchObject({});
      });

      it('should return ITaux', () => {
        const formGroup = service.createTauxFormGroup(sampleWithRequiredData);

        const taux = service.getTaux(formGroup) as any;

        expect(taux).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ITaux should not enable id FormControl', () => {
        const formGroup = service.createTauxFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewTaux should disable id FormControl', () => {
        const formGroup = service.createTauxFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
