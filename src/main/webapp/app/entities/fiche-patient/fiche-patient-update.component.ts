import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IFichePatient } from 'app/shared/model/fiche-patient.model';
import { FichePatientService } from './fiche-patient.service';
import { ICentre } from 'app/shared/model/centre.model';
import { CentreService } from 'app/entities/centre';

@Component({
    selector: 'jhi-fiche-patient-update',
    templateUrl: './fiche-patient-update.component.html'
})
export class FichePatientUpdateComponent implements OnInit {
    fichePatient: IFichePatient;
    isSaving: boolean;

    centres: ICentre[];
    oneDate: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private fichePatientService: FichePatientService,
        private centreService: CentreService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ fichePatient }) => {
            this.fichePatient = fichePatient;
            this.oneDate = this.fichePatient.oneDate != null ? this.fichePatient.oneDate.format(DATE_TIME_FORMAT) : null;
        });
        this.centreService.query({ filter: 'fichepatient-is-null' }).subscribe(
            (res: HttpResponse<ICentre[]>) => {
                if (!this.fichePatient.centreId) {
                    this.centres = res.body;
                } else {
                    this.centreService.find(this.fichePatient.centreId).subscribe(
                        (subRes: HttpResponse<ICentre>) => {
                            this.centres = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.fichePatient.oneDate = this.oneDate != null ? moment(this.oneDate, DATE_TIME_FORMAT) : null;
        if (this.fichePatient.id !== undefined) {
            this.subscribeToSaveResponse(this.fichePatientService.update(this.fichePatient));
        } else {
            this.subscribeToSaveResponse(this.fichePatientService.create(this.fichePatient));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IFichePatient>>) {
        result.subscribe((res: HttpResponse<IFichePatient>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackCentreById(index: number, item: ICentre) {
        return item.id;
    }
}
