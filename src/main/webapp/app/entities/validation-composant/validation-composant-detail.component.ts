import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IValidationComposant } from 'app/shared/model/validation-composant.model';

@Component({
    selector: 'jhi-validation-composant-detail',
    templateUrl: './validation-composant-detail.component.html'
})
export class ValidationComposantDetailComponent implements OnInit {
    validationComposant: IValidationComposant;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ validationComposant }) => {
            this.validationComposant = validationComposant;
        });
    }

    previousState() {
        window.history.back();
    }
}
