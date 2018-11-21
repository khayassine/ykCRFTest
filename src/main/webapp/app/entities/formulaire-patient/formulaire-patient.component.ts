import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IFormulairePatient } from 'app/shared/model/formulaire-patient.model';
import { Principal } from 'app/core';
import { FormulairePatientService } from './formulaire-patient.service';

@Component({
    selector: 'jhi-formulaire-patient',
    templateUrl: './formulaire-patient.component.html'
})
export class FormulairePatientComponent implements OnInit, OnDestroy {
    formulairePatients: IFormulairePatient[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private formulairePatientService: FormulairePatientService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.formulairePatientService.query().subscribe(
            (res: HttpResponse<IFormulairePatient[]>) => {
                this.formulairePatients = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInFormulairePatients();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IFormulairePatient) {
        return item.id;
    }

    registerChangeInFormulairePatients() {
        this.eventSubscriber = this.eventManager.subscribe('formulairePatientListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
