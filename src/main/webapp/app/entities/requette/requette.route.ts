import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Requette } from 'app/shared/model/requette.model';
import { RequetteService } from './requette.service';
import { RequetteComponent } from './requette.component';
import { RequetteDetailComponent } from './requette-detail.component';
import { RequetteUpdateComponent } from './requette-update.component';
import { RequetteDeletePopupComponent } from './requette-delete-dialog.component';
import { IRequette } from 'app/shared/model/requette.model';

@Injectable({ providedIn: 'root' })
export class RequetteResolve implements Resolve<IRequette> {
    constructor(private service: RequetteService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Requette> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Requette>) => response.ok),
                map((requette: HttpResponse<Requette>) => requette.body)
            );
        }
        return of(new Requette());
    }
}

export const requetteRoute: Routes = [
    {
        path: 'requette',
        component: RequetteComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'ykCrfApp.requette.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'requette/:id/view',
        component: RequetteDetailComponent,
        resolve: {
            requette: RequetteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ykCrfApp.requette.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'requette/new',
        component: RequetteUpdateComponent,
        resolve: {
            requette: RequetteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ykCrfApp.requette.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'requette/:id/edit',
        component: RequetteUpdateComponent,
        resolve: {
            requette: RequetteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ykCrfApp.requette.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const requettePopupRoute: Routes = [
    {
        path: 'requette/:id/delete',
        component: RequetteDeletePopupComponent,
        resolve: {
            requette: RequetteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ykCrfApp.requette.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
