import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IRequette } from 'app/shared/model/requette.model';
import { RequetteService } from './requette.service';
import { IComposantValeur } from 'app/shared/model/composant-valeur.model';
import { ComposantValeurService } from 'app/entities/composant-valeur';
import { IValidationComposant } from 'app/shared/model/validation-composant.model';
import { ValidationComposantService } from 'app/entities/validation-composant';

@Component({
    selector: 'jhi-requette-update',
    templateUrl: './requette-update.component.html'
})
export class RequetteUpdateComponent implements OnInit {
    requette: IRequette;
    isSaving: boolean;

    composantvaleurs: IComposantValeur[];

    validationcomposants: IValidationComposant[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private requetteService: RequetteService,
        private composantValeurService: ComposantValeurService,
        private validationComposantService: ValidationComposantService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ requette }) => {
            this.requette = requette;
        });
        this.composantValeurService.query({ filter: 'requette-is-null' }).subscribe(
            (res: HttpResponse<IComposantValeur[]>) => {
                if (!this.requette.composantValeurId) {
                    this.composantvaleurs = res.body;
                } else {
                    this.composantValeurService.find(this.requette.composantValeurId).subscribe(
                        (subRes: HttpResponse<IComposantValeur>) => {
                            this.composantvaleurs = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.validationComposantService.query({ filter: 'requette-is-null' }).subscribe(
            (res: HttpResponse<IValidationComposant[]>) => {
                if (!this.requette.validationComposantId) {
                    this.validationcomposants = res.body;
                } else {
                    this.validationComposantService.find(this.requette.validationComposantId).subscribe(
                        (subRes: HttpResponse<IValidationComposant>) => {
                            this.validationcomposants = [subRes.body].concat(res.body);
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
        if (this.requette.id !== undefined) {
            this.subscribeToSaveResponse(this.requetteService.update(this.requette));
        } else {
            this.subscribeToSaveResponse(this.requetteService.create(this.requette));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IRequette>>) {
        result.subscribe((res: HttpResponse<IRequette>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackComposantValeurById(index: number, item: IComposantValeur) {
        return item.id;
    }

    trackValidationComposantById(index: number, item: IValidationComposant) {
        return item.id;
    }
}
