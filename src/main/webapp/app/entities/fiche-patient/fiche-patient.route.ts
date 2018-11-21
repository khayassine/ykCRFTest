import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { FichePatient } from 'app/shared/model/fiche-patient.model';
import { FichePatientService } from './fiche-patient.service';
import { FichePatientComponent } from './fiche-patient.component';
import { FichePatientDetailComponent } from './fiche-patient-detail.component';
import { FichePatientUpdateComponent } from './fiche-patient-update.component';
import { FichePatientDeletePopupComponent } from './fiche-patient-delete-dialog.component';
import { IFichePatient } from 'app/shared/model/fiche-patient.model';

@Injectable({ providedIn: 'root' })
export class FichePatientResolve implements Resolve<IFichePatient> {
    constructor(private service: FichePatientService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<FichePatient> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<FichePatient>) => response.ok),
                map((fichePatient: HttpResponse<FichePatient>) => fichePatient.body)
            );
        }
        return of(new FichePatient());
    }
}

export const fichePatientRoute: Routes = [
    {
        path: 'fiche-patient',
        component: FichePatientComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'ykCrfApp.fichePatient.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'fiche-patient/:id/view',
        component: FichePatientDetailComponent,
        resolve: {
            fichePatient: FichePatientResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ykCrfApp.fichePatient.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'fiche-patient/new',
        component: FichePatientUpdateComponent,
        resolve: {
            fichePatient: FichePatientResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ykCrfApp.fichePatient.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'fiche-patient/:id/edit',
        component: FichePatientUpdateComponent,
        resolve: {
            fichePatient: FichePatientResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ykCrfApp.fichePatient.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const fichePatientPopupRoute: Routes = [
    {
        path: 'fiche-patient/:id/delete',
        component: FichePatientDeletePopupComponent,
        resolve: {
            fichePatient: FichePatientResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ykCrfApp.fichePatient.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
