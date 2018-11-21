import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITypeComposant } from 'app/shared/model/type-composant.model';

@Component({
    selector: 'jhi-type-composant-detail',
    templateUrl: './type-composant-detail.component.html'
})
export class TypeComposantDetailComponent implements OnInit {
    typeComposant: ITypeComposant;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ typeComposant }) => {
            this.typeComposant = typeComposant;
        });
    }

    previousState() {
        window.history.back();
    }
}
