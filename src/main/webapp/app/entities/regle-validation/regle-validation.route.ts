import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { RegleValidation } from 'app/shared/model/regle-validation.model';
import { RegleValidationService } from './regle-validation.service';
import { RegleValidationComponent } from './regle-validation.component';
import { RegleValidationDetailComponent } from './regle-validation-detail.component';
import { RegleValidationUpdateComponent } from './regle-validation-update.component';
import { RegleValidationDeletePopupComponent } from './regle-validation-delete-dialog.component';
import { IRegleValidation } from 'app/shared/model/regle-validation.model';

@Injectable({ providedIn: 'root' })
export class RegleValidationResolve implements Resolve<IRegleValidation> {
    constructor(private service: RegleValidationService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<RegleValidation> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<RegleValidation>) => response.ok),
                map((regleValidation: HttpResponse<RegleValidation>) => regleValidation.body)
            );
        }
        return of(new RegleValidation());
    }
}

export const regleValidationRoute: Routes = [
    {
        path: 'regle-validation',
        component: RegleValidationComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'ykCrfApp.regleValidation.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'regle-validation/:id/view',
        component: RegleValidationDetailComponent,
        resolve: {
            regleValidation: RegleValidationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ykCrfApp.regleValidation.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'regle-validation/new',
        component: RegleValidationUpdateComponent,
        resolve: {
            regleValidation: RegleValidationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ykCrfApp.regleValidation.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'regle-validation/:id/edit',
        component: RegleValidationUpdateComponent,
        resolve: {
            regleValidation: RegleValidationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ykCrfApp.regleValidation.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const regleValidationPopupRoute: Routes = [
    {
        path: 'regle-validation/:id/delete',
        component: RegleValidationDeletePopupComponent,
        resolve: {
            regleValidation: RegleValidationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ykCrfApp.regleValidation.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
