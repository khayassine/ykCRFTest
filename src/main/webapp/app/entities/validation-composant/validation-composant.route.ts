import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ValidationComposant } from 'app/shared/model/validation-composant.model';
import { ValidationComposantService } from './validation-composant.service';
import { ValidationComposantComponent } from './validation-composant.component';
import { ValidationComposantDetailComponent } from './validation-composant-detail.component';
import { ValidationComposantUpdateComponent } from './validation-composant-update.component';
import { ValidationComposantDeletePopupComponent } from './validation-composant-delete-dialog.component';
import { IValidationComposant } from 'app/shared/model/validation-composant.model';

@Injectable({ providedIn: 'root' })
export class ValidationComposantResolve implements Resolve<IValidationComposant> {
    constructor(private service: ValidationComposantService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ValidationComposant> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ValidationComposant>) => response.ok),
                map((validationComposant: HttpResponse<ValidationComposant>) => validationComposant.body)
            );
        }
        return of(new ValidationComposant());
    }
}

export const validationComposantRoute: Routes = [
    {
        path: 'validation-composant',
        component: ValidationComposantComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'ykCrfApp.validationComposant.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'validation-composant/:id/view',
        component: ValidationComposantDetailComponent,
        resolve: {
            validationComposant: ValidationComposantResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ykCrfApp.validationComposant.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'validation-composant/new',
        component: ValidationComposantUpdateComponent,
        resolve: {
            validationComposant: ValidationComposantResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ykCrfApp.validationComposant.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'validation-composant/:id/edit',
        component: ValidationComposantUpdateComponent,
        resolve: {
            validationComposant: ValidationComposantResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ykCrfApp.validationComposant.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const validationComposantPopupRoute: Routes = [
    {
        path: 'validation-composant/:id/delete',
        component: ValidationComposantDeletePopupComponent,
        resolve: {
            validationComposant: ValidationComposantResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ykCrfApp.validationComposant.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
