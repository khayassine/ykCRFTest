import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRequette } from 'app/shared/model/requette.model';

@Component({
    selector: 'jhi-requette-detail',
    templateUrl: './requette-detail.component.html'
})
export class RequetteDetailComponent implements OnInit {
    requette: IRequette;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ requette }) => {
            this.requette = requette;
        });
    }

    previousState() {
        window.history.back();
    }
}
