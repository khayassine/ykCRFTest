import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFormulairePatient } from 'app/shared/model/formulaire-patient.model';

@Component({
    selector: 'jhi-formulaire-patient-detail',
    templateUrl: './formulaire-patient-detail.component.html'
})
export class FormulairePatientDetailComponent implements OnInit {
    formulairePatient: IFormulairePatient;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ formulairePatient }) => {
            this.formulairePatient = formulairePatient;
        });
    }

    previousState() {
        window.history.back();
    }
}
