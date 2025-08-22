import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IPmEtablissement } from 'app/entities/pm-etablissement/pm-etablissement.model';
import { PmEtablissementService } from 'app/entities/pm-etablissement/service/pm-etablissement.service';
import { IEmail } from '../email.model';
import { EmailService } from '../service/email.service';
import { EmailFormGroup, EmailFormService } from './email-form.service';

@Component({
  selector: 'jhi-email-update',
  templateUrl: './email-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class EmailUpdateComponent implements OnInit {
  isSaving = false;
  email: IEmail | null = null;

  pmEtablissementsSharedCollection: IPmEtablissement[] = [];

  protected emailService = inject(EmailService);
  protected emailFormService = inject(EmailFormService);
  protected pmEtablissementService = inject(PmEtablissementService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: EmailFormGroup = this.emailFormService.createEmailFormGroup();

  comparePmEtablissement = (o1: IPmEtablissement | null, o2: IPmEtablissement | null): boolean =>
    this.pmEtablissementService.comparePmEtablissement(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ email }) => {
      this.email = email;
      if (email) {
        this.updateForm(email);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const email = this.emailFormService.getEmail(this.editForm);
    if (email.id !== null) {
      this.subscribeToSaveResponse(this.emailService.update(email));
    } else {
      this.subscribeToSaveResponse(this.emailService.create(email));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEmail>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(email: IEmail): void {
    this.email = email;
    this.emailFormService.resetForm(this.editForm, email);

    this.pmEtablissementsSharedCollection = this.pmEtablissementService.addPmEtablissementToCollectionIfMissing<IPmEtablissement>(
      this.pmEtablissementsSharedCollection,
      email.pmEtablissement,
    );
  }

  protected loadRelationshipsOptions(): void {
    this.pmEtablissementService
      .query()
      .pipe(map((res: HttpResponse<IPmEtablissement[]>) => res.body ?? []))
      .pipe(
        map((pmEtablissements: IPmEtablissement[]) =>
          this.pmEtablissementService.addPmEtablissementToCollectionIfMissing<IPmEtablissement>(
            pmEtablissements,
            this.email?.pmEtablissement,
          ),
        ),
      )
      .subscribe((pmEtablissements: IPmEtablissement[]) => (this.pmEtablissementsSharedCollection = pmEtablissements));
  }
}
