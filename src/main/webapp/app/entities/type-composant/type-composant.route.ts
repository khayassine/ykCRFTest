import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TypeComposant } from 'app/shared/model/type-composant.model';
import { TypeComposantService } from './type-composant.service';
import { TypeComposantComponent } from './type-composant.component';
import { TypeComposantDetailComponent } from './type-composant-detail.component';
import { TypeComposantUpdateComponent } from './type-composant-update.component';
import { TypeComposantDeletePopupComponent } from './type-composant-delete-dialog.component';
import { ITypeComposant } from 'app/shared/model/type-composant.model';

@Injectable({ providedIn: 'root' })
export class TypeComposantResolve implements Resolve<ITypeComposant> {
    constructor(private service: TypeComposantService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<TypeComposant> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<TypeComposant>) => response.ok),
                map((typeComposant: HttpResponse<TypeComposant>) => typeComposant.body)
            );
        }
        return of(new TypeComposant());
    }
}

export const typeComposantRoute: Routes = [
    {
        path: 'type-composant',
        component: TypeComposantComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'ykCrfApp.typeComposant.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'type-composant/:id/view',
        component: TypeComposantDetailComponent,
        resolve: {
            typeComposant: TypeComposantResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ykCrfApp.typeComposant.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'type-composant/new',
        component: TypeComposantUpdateComponent,
        resolve: {
            typeComposant: TypeComposantResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ykCrfApp.typeComposant.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'type-composant/:id/edit',
        component: TypeComposantUpdateComponent,
        resolve: {
            typeComposant: TypeComposantResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ykCrfApp.typeComposant.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const typeComposantPopupRoute: Routes = [
    {
        path: 'type-composant/:id/delete',
        component: TypeComposantDeletePopupComponent,
        resolve: {
            typeComposant: TypeComposantResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ykCrfApp.typeComposant.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
