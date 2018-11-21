import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IComposantValeur } from 'app/shared/model/composant-valeur.model';
import { ComposantValeurService } from './composant-valeur.service';
import { IFormulairePatient } from 'app/shared/model/formulaire-patient.model';
import { FormulairePatientService } from 'app/entities/formulaire-patient';
import { IComposantTemplate } from 'app/shared/model/composant-template.model';
import { ComposantTemplateService } from 'app/entities/composant-template';

@Component({
    selector: 'jhi-composant-valeur-update',
    templateUrl: './composant-valeur-update.component.html'
})
export class ComposantValeurUpdateComponent implements OnInit {
    composantValeur: IComposantValeur;
    isSaving: boolean;

    formulairepatients: IFormulairePatient[];

    composanttemplates: IComposantTemplate[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private composantValeurService: ComposantValeurService,
        private formulairePatientService: FormulairePatientService,
        private composantTemplateService: ComposantTemplateService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ composantValeur }) => {
            this.composantValeur = composantValeur;
        });
        this.formulairePatientService.query().subscribe(
            (res: HttpResponse<IFormulairePatient[]>) => {
                this.formulairepatients = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.composantTemplateService.query({ filter: 'composantvaleur-is-null' }).subscribe(
            (res: HttpResponse<IComposantTemplate[]>) => {
                if (!this.composantValeur.composantTemplateId) {
                    this.composanttemplates = res.body;
                } else {
                    this.composantTemplateService.find(this.composantValeur.composantTemplateId).subscribe(
                        (subRes: HttpResponse<IComposantTemplate>) => {
                            this.composanttemplates = [subRes.body].concat(res.body);
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
        if (this.composantValeur.id !== undefined) {
            this.subscribeToSaveResponse(this.composantValeurService.update(this.composantValeur));
        } else {
            this.subscribeToSaveResponse(this.composantValeurService.create(this.composantValeur));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IComposantValeur>>) {
        result.subscribe((res: HttpResponse<IComposantValeur>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackFormulairePatientById(index: number, item: IFormulairePatient) {
        return item.id;
    }

    trackComposantTemplateById(index: number, item: IComposantTemplate) {
        return item.id;
    }
}
