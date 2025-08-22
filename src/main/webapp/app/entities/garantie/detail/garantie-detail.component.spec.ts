import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { GarantieDetailComponent } from './garantie-detail.component';

describe('Garantie Management Detail Component', () => {
  let comp: GarantieDetailComponent;
  let fixture: ComponentFixture<GarantieDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GarantieDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () => import('./garantie-detail.component').then(m => m.GarantieDetailComponent),
              resolve: { garantie: () => of({ id: 28294 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(GarantieDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GarantieDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('should load garantie on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', GarantieDetailComponent);

      // THEN
      expect(instance.garantie()).toEqual(expect.objectContaining({ id: 28294 }));
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
