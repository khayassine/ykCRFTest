import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ITypeComposant } from 'app/shared/model/type-composant.model';
import { TypeComposantService } from './type-composant.service';

@Component({
    selector: 'jhi-type-composant-update',
    templateUrl: './type-composant-update.component.html'
})
export class TypeComposantUpdateComponent implements OnInit {
    typeComposant: ITypeComposant;
    isSaving: boolean;

    constructor(private typeComposantService: TypeComposantService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ typeComposant }) => {
            this.typeComposant = typeComposant;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.typeComposant.id !== undefined) {
            this.subscribeToSaveResponse(this.typeComposantService.update(this.typeComposant));
        } else {
            this.subscribeToSaveResponse(this.typeComposantService.create(this.typeComposant));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ITypeComposant>>) {
        result.subscribe((res: HttpResponse<ITypeComposant>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
