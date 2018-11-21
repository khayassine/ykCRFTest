import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IEtude } from 'app/shared/model/etude.model';
import { EtudeService } from './etude.service';

@Component({
    selector: 'jhi-etude-update',
    templateUrl: './etude-update.component.html'
})
export class EtudeUpdateComponent implements OnInit {
    etude: IEtude;
    isSaving: boolean;

    constructor(private etudeService: EtudeService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ etude }) => {
            this.etude = etude;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.etude.id !== undefined) {
            this.subscribeToSaveResponse(this.etudeService.update(this.etude));
        } else {
            this.subscribeToSaveResponse(this.etudeService.create(this.etude));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IEtude>>) {
        result.subscribe((res: HttpResponse<IEtude>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
