import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IFormulaireTemplate } from 'app/shared/model/formulaire-template.model';
import { FormulaireTemplateService } from './formulaire-template.service';

@Component({
    selector: 'jhi-formulaire-template-update',
    templateUrl: './formulaire-template-update.component.html'
})
export class FormulaireTemplateUpdateComponent implements OnInit {
    formulaireTemplate: IFormulaireTemplate;
    isSaving: boolean;

    constructor(private formulaireTemplateService: FormulaireTemplateService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ formulaireTemplate }) => {
            this.formulaireTemplate = formulaireTemplate;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.formulaireTemplate.id !== undefined) {
            this.subscribeToSaveResponse(this.formulaireTemplateService.update(this.formulaireTemplate));
        } else {
            this.subscribeToSaveResponse(this.formulaireTemplateService.create(this.formulaireTemplate));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IFormulaireTemplate>>) {
        result.subscribe((res: HttpResponse<IFormulaireTemplate>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
