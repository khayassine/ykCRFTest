import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IComposantTemplate } from 'app/shared/model/composant-template.model';

@Component({
    selector: 'jhi-composant-template-detail',
    templateUrl: './composant-template-detail.component.html'
})
export class ComposantTemplateDetailComponent implements OnInit {
    composantTemplate: IComposantTemplate;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ composantTemplate }) => {
            this.composantTemplate = composantTemplate;
        });
    }

    previousState() {
        window.history.back();
    }
}
