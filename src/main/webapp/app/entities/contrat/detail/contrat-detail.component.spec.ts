import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { ContratDetailComponent } from './contrat-detail.component';

describe('Contrat Management Detail Component', () => {
  let comp: ContratDetailComponent;
  let fixture: ComponentFixture<ContratDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ContratDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () => import('./contrat-detail.component').then(m => m.ContratDetailComponent),
              resolve: { contrat: () => of({ id: 7637 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(ContratDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ContratDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('should load contrat on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', ContratDetailComponent);

      // THEN
      expect(instance.contrat()).toEqual(expect.objectContaining({ id: 7637 }));
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
