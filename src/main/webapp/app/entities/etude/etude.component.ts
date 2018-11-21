import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IEtude } from 'app/shared/model/etude.model';
import { Principal } from 'app/core';
import { EtudeService } from './etude.service';

@Component({
    selector: 'jhi-etude',
    templateUrl: './etude.component.html'
})
export class EtudeComponent implements OnInit, OnDestroy {
    etudes: IEtude[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private etudeService: EtudeService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.etudeService.query().subscribe(
            (res: HttpResponse<IEtude[]>) => {
                this.etudes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInEtudes();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IEtude) {
        return item.id;
    }

    registerChangeInEtudes() {
        this.eventSubscriber = this.eventManager.subscribe('etudeListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
