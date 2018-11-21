import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IComposantTemplate } from 'app/shared/model/composant-template.model';
import { ComposantTemplateService } from './composant-template.service';
import { IFormulaireTemplate } from 'app/shared/model/formulaire-template.model';
import { FormulaireTemplateService } from 'app/entities/formulaire-template';
import { ITypeComposant } from 'app/shared/model/type-composant.model';
import { TypeComposantService } from 'app/entities/type-composant';

@Component({
    selector: 'jhi-composant-template-update',
    templateUrl: './composant-template-update.component.html'
})
export class ComposantTemplateUpdateComponent implements OnInit {
    composantTemplate: IComposantTemplate;
    isSaving: boolean;

    formulairetemplates: IFormulaireTemplate[];

    typecomposants: ITypeComposant[];

    composanttemplates: IComposantTemplate[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private composantTemplateService: ComposantTemplateService,
        private formulaireTemplateService: FormulaireTemplateService,
        private typeComposantService: TypeComposantService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ composantTemplate }) => {
            this.composantTemplate = composantTemplate;
        });
        this.formulaireTemplateService.query().subscribe(
            (res: HttpResponse<IFormulaireTemplate[]>) => {
                this.formulairetemplates = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.typeComposantService.query({ filter: 'composanttemplate-is-null' }).subscribe(
            (res: HttpResponse<ITypeComposant[]>) => {
                if (!this.composantTemplate.typeComposantId) {
                    this.typecomposants = res.body;
                } else {
                    this.typeComposantService.find(this.composantTemplate.typeComposantId).subscribe(
                        (subRes: HttpResponse<ITypeComposant>) => {
                            this.typecomposants = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.composantTemplateService.query().subscribe(
            (res: HttpResponse<IComposantTemplate[]>) => {
                this.composanttemplates = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.composantTemplate.id !== undefined) {
            this.subscribeToSaveResponse(this.composantTemplateService.update(this.composantTemplate));
        } else {
            this.subscribeToSaveResponse(this.composantTemplateService.create(this.composantTemplate));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IComposantTemplate>>) {
        result.subscribe((res: HttpResponse<IComposantTemplate>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackFormulaireTemplateById(index: number, item: IFormulaireTemplate) {
        return item.id;
    }

    trackTypeComposantById(index: number, item: ITypeComposant) {
        return item.id;
    }

    trackComposantTemplateById(index: number, item: IComposantTemplate) {
        return item.id;
    }
}
