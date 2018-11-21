import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Centre } from 'app/shared/model/centre.model';
import { CentreService } from './centre.service';
import { CentreComponent } from './centre.component';
import { CentreDetailComponent } from './centre-detail.component';
import { CentreUpdateComponent } from './centre-update.component';
import { CentreDeletePopupComponent } from './centre-delete-dialog.component';
import { ICentre } from 'app/shared/model/centre.model';

@Injectable({ providedIn: 'root' })
export class CentreResolve implements Resolve<ICentre> {
    constructor(private service: CentreService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Centre> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Centre>) => response.ok),
                map((centre: HttpResponse<Centre>) => centre.body)
            );
        }
        return of(new Centre());
    }
}

export const centreRoute: Routes = [
    {
        path: 'centre',
        component: CentreComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'ykCrfApp.centre.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'centre/:id/view',
        component: CentreDetailComponent,
        resolve: {
            centre: CentreResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ykCrfApp.centre.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'centre/new',
        component: CentreUpdateComponent,
        resolve: {
            centre: CentreResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ykCrfApp.centre.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'centre/:id/edit',
        component: CentreUpdateComponent,
        resolve: {
            centre: CentreResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ykCrfApp.centre.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const centrePopupRoute: Routes = [
    {
        path: 'centre/:id/delete',
        component: CentreDeletePopupComponent,
        resolve: {
            centre: CentreResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ykCrfApp.centre.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
