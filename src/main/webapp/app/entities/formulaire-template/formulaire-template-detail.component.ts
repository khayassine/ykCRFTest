import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFormulaireTemplate } from 'app/shared/model/formulaire-template.model';

@Component({
    selector: 'jhi-formulaire-template-detail',
    templateUrl: './formulaire-template-detail.component.html'
})
export class FormulaireTemplateDetailComponent implements OnInit {
    formulaireTemplate: IFormulaireTemplate;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ formulaireTemplate }) => {
            this.formulaireTemplate = formulaireTemplate;
        });
    }

    previousState() {
        window.history.back();
    }
}
