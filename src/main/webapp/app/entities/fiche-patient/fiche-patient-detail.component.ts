import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFichePatient } from 'app/shared/model/fiche-patient.model';

@Component({
    selector: 'jhi-fiche-patient-detail',
    templateUrl: './fiche-patient-detail.component.html'
})
export class FichePatientDetailComponent implements OnInit {
    fichePatient: IFichePatient;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ fichePatient }) => {
            this.fichePatient = fichePatient;
        });
    }

    previousState() {
        window.history.back();
    }
}
