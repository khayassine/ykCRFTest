import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IFormulairePatient } from 'app/shared/model/formulaire-patient.model';
import { FormulairePatientService } from './formulaire-patient.service';
import { IFichePatient } from 'app/shared/model/fiche-patient.model';
import { FichePatientService } from 'app/entities/fiche-patient';
import { IFormulaireTemplate } from 'app/shared/model/formulaire-template.model';
import { FormulaireTemplateService } from 'app/entities/formulaire-template';

@Component({
    selector: 'jhi-formulaire-patient-update',
    templateUrl: './formulaire-patient-update.component.html'
})
export class FormulairePatientUpdateComponent implements OnInit {
    formulairePatient: IFormulairePatient;
    isSaving: boolean;

    fichepatients: IFichePatient[];

    formulairetemplates: IFormulaireTemplate[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private formulairePatientService: FormulairePatientService,
        private fichePatientService: FichePatientService,
        private formulaireTemplateService: FormulaireTemplateService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ formulairePatient }) => {
            this.formulairePatient = formulairePatient;
        });
        this.fichePatientService.query().subscribe(
            (res: HttpResponse<IFichePatient[]>) => {
                this.fichepatients = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.formulaireTemplateService.query({ filter: 'formulairepatient-is-null' }).subscribe(
            (res: HttpResponse<IFormulaireTemplate[]>) => {
                if (!this.formulairePatient.formulaireTemplateId) {
                    this.formulairetemplates = res.body;
                } else {
                    this.formulaireTemplateService.find(this.formulairePatient.formulaireTemplateId).subscribe(
                        (subRes: HttpResponse<IFormulaireTemplate>) => {
                            this.formulairetemplates = [subRes.body].concat(res.body);
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
        if (this.formulairePatient.id !== undefined) {
            this.subscribeToSaveResponse(this.formulairePatientService.update(this.formulairePatient));
        } else {
            this.subscribeToSaveResponse(this.formulairePatientService.create(this.formulairePatient));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IFormulairePatient>>) {
        result.subscribe((res: HttpResponse<IFormulairePatient>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackFichePatientById(index: number, item: IFichePatient) {
        return item.id;
    }

    trackFormulaireTemplateById(index: number, item: IFormulaireTemplate) {
        return item.id;
    }
}
