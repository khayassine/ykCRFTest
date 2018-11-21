import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IRegleValidation } from 'app/shared/model/regle-validation.model';
import { RegleValidationService } from './regle-validation.service';

@Component({
    selector: 'jhi-regle-validation-update',
    templateUrl: './regle-validation-update.component.html'
})
export class RegleValidationUpdateComponent implements OnInit {
    regleValidation: IRegleValidation;
    isSaving: boolean;

    constructor(private regleValidationService: RegleValidationService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ regleValidation }) => {
            this.regleValidation = regleValidation;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.regleValidation.id !== undefined) {
            this.subscribeToSaveResponse(this.regleValidationService.update(this.regleValidation));
        } else {
            this.subscribeToSaveResponse(this.regleValidationService.create(this.regleValidation));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IRegleValidation>>) {
        result.subscribe((res: HttpResponse<IRegleValidation>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
