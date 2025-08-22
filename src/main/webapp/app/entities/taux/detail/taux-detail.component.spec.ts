import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { TauxDetailComponent } from './taux-detail.component';

describe('Taux Management Detail Component', () => {
  let comp: TauxDetailComponent;
  let fixture: ComponentFixture<TauxDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TauxDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () => import('./taux-detail.component').then(m => m.TauxDetailComponent),
              resolve: { taux: () => of({ id: 25175 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(TauxDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TauxDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('should load taux on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', TauxDetailComponent);

      // THEN
      expect(instance.taux()).toEqual(expect.objectContaining({ id: 25175 }));
    });
  });

  describe('PreviousState', () => {
    it('should navigate to previous state', () => {
      jest.spyOn(window.history, 'back');
      comp.previousState();
      expect(window.history.back).toHaveBeenCalled();
    });
  });
});
