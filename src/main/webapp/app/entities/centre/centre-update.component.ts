import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ICentre } from 'app/shared/model/centre.model';
import { CentreService } from './centre.service';
import { IVille } from 'app/shared/model/ville.model';
import { VilleService } from 'app/entities/ville';
import { IRegion } from 'app/shared/model/region.model';
import { RegionService } from 'app/entities/region';
import { ISousRegion } from 'app/shared/model/sous-region.model';
import { SousRegionService } from 'app/entities/sous-region';

@Component({
    selector: 'jhi-centre-update',
    templateUrl: './centre-update.component.html'
})
export class CentreUpdateComponent implements OnInit {
    centre: ICentre;
    isSaving: boolean;

    villes: IVille[];

    regions: IRegion[];

    sousregions: ISousRegion[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private centreService: CentreService,
        private villeService: VilleService,
        private regionService: RegionService,
        private sousRegionService: SousRegionService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ centre }) => {
            this.centre = centre;
        });
        this.villeService.query({ filter: 'centre-is-null' }).subscribe(
            (res: HttpResponse<IVille[]>) => {
                if (!this.centre.villeId) {
                    this.villes = res.body;
                } else {
                    this.villeService.find(this.centre.villeId).subscribe(
                        (subRes: HttpResponse<IVille>) => {
                            this.villes = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.regionService.query({ filter: 'centre-is-null' }).subscribe(
            (res: HttpResponse<IRegion[]>) => {
                if (!this.centre.regionId) {
                    this.regions = res.body;
                } else {
                    this.regionService.find(this.centre.regionId).subscribe(
                        (subRes: HttpResponse<IRegion>) => {
                            this.regions = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.sousRegionService.query({ filter: 'centre-is-null' }).subscribe(
            (res: HttpResponse<ISousRegion[]>) => {
                if (!this.centre.sousRegionId) {
                    this.sousregions = res.body;
                } else {
                    this.sousRegionService.find(this.centre.sousRegionId).subscribe(
                        (subRes: HttpResponse<ISousRegion>) => {
                            this.sousregions = [subRes.body].concat(res.body);
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
        if (this.centre.id !== undefined) {
            this.subscribeToSaveResponse(this.centreService.update(this.centre));
        } else {
            this.subscribeToSaveResponse(this.centreService.create(this.centre));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ICentre>>) {
        result.subscribe((res: HttpResponse<ICentre>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackVilleById(index: number, item: IVille) {
        return item.id;
    }

    trackRegionById(index: number, item: IRegion) {
        return item.id;
    }

    trackSousRegionById(index: number, item: ISousRegion) {
        return item.id;
    }
}
