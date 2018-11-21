import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRegleValidation } from 'app/shared/model/regle-validation.model';

@Component({
    selector: 'jhi-regle-validation-detail',
    templateUrl: './regle-validation-detail.component.html'
})
export class RegleValidationDetailComponent implements OnInit {
    regleValidation: IRegleValidation;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ regleValidation }) => {
            this.regleValidation = regleValidation;
        });
    }

    previousState() {
        window.history.back();
    }
}
