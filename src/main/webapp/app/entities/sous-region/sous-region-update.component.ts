import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ISousRegion } from 'app/shared/model/sous-region.model';
import { SousRegionService } from './sous-region.service';
import { IRegion } from 'app/shared/model/region.model';
import { RegionService } from 'app/entities/region';

@Component({
    selector: 'jhi-sous-region-update',
    templateUrl: './sous-region-update.component.html'
})
export class SousRegionUpdateComponent implements OnInit {
    sousRegion: ISousRegion;
    isSaving: boolean;

    regions: IRegion[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private sousRegionService: SousRegionService,
        private regionService: RegionService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ sousRegion }) => {
            this.sousRegion = sousRegion;
        });
        this.regionService.query().subscribe(
            (res: HttpResponse<IRegion[]>) => {
                this.regions = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.sousRegion.id !== undefined) {
            this.subscribeToSaveResponse(this.sousRegionService.update(this.sousRegion));
        } else {
            this.subscribeToSaveResponse(this.sousRegionService.create(this.sousRegion));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ISousRegion>>) {
        result.subscribe((res: HttpResponse<ISousRegion>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackRegionById(index: number, item: IRegion) {
        return item.id;
    }
}
