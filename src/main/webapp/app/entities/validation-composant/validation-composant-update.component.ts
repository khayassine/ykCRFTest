import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IValidationComposant } from 'app/shared/model/validation-composant.model';
import { ValidationComposantService } from './validation-composant.service';
import { IComposantTemplate } from 'app/shared/model/composant-template.model';
import { ComposantTemplateService } from 'app/entities/composant-template';
import { IRegleValidation } from 'app/shared/model/regle-validation.model';
import { RegleValidationService } from 'app/entities/regle-validation';

@Component({
    selector: 'jhi-validation-composant-update',
    templateUrl: './validation-composant-update.component.html'
})
export class ValidationComposantUpdateComponent implements OnInit {
    validationComposant: IValidationComposant;
    isSaving: boolean;

    composanttemplates: IComposantTemplate[];

    reglevalidations: IRegleValidation[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private validationComposantService: ValidationComposantService,
        private composantTemplateService: ComposantTemplateService,
        private regleValidationService: RegleValidationService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ validationComposant }) => {
            this.validationComposant = validationComposant;
        });
        this.composantTemplateService.query().subscribe(
            (res: HttpResponse<IComposantTemplate[]>) => {
                this.composanttemplates = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.regleValidationService.query({ filter: 'validationcomposant-is-null' }).subscribe(
            (res: HttpResponse<IRegleValidation[]>) => {
                if (!this.validationComposant.regleValidationId) {
                    this.reglevalidations = res.body;
                } else {
                    this.regleValidationService.find(this.validationComposant.regleValidationId).subscribe(
                        (subRes: HttpResponse<IRegleValidation>) => {
                            this.reglevalidations = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.validationComposant.id !== undefined) {
            this.subscribeToSaveResponse(this.validationComposantService.update(this.validationComposant));
        } else {
            this.subscribeToSaveResponse(this.validationComposantService.create(this.validationComposant));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IValidationComposant>>) {
        result.subscribe((res: HttpResponse<IValidationComposant>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackComposantTemplateById(index: number, item: IComposantTemplate) {
        return item.id;
    }

    trackRegleValidationById(index: number, item: IRegleValidation) {
        return item.id;
    }
}
