import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { SousRegion } from 'app/shared/model/sous-region.model';
import { SousRegionService } from './sous-region.service';
import { SousRegionComponent } from './sous-region.component';
import { SousRegionDetailComponent } from './sous-region-detail.component';
import { SousRegionUpdateComponent } from './sous-region-update.component';
import { SousRegionDeletePopupComponent } from './sous-region-delete-dialog.component';
import { ISousRegion } from 'app/shared/model/sous-region.model';

@Injectable({ providedIn: 'root' })
export class SousRegionResolve implements Resolve<ISousRegion> {
    constructor(private service: SousRegionService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<SousRegion> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<SousRegion>) => response.ok),
                map((sousRegion: HttpResponse<SousRegion>) => sousRegion.body)
            );
        }
        return of(new SousRegion());
    }
}

export const sousRegionRoute: Routes = [
    {
        path: 'sous-region',
        component: SousRegionComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'ykCrfApp.sousRegion.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sous-region/:id/view',
        component: SousRegionDetailComponent,
        resolve: {
            sousRegion: SousRegionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ykCrfApp.sousRegion.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sous-region/new',
        component: SousRegionUpdateComponent,
        resolve: {
            sousRegion: SousRegionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ykCrfApp.sousRegion.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sous-region/:id/edit',
        component: SousRegionUpdateComponent,
        resolve: {
            sousRegion: SousRegionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ykCrfApp.sousRegion.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const sousRegionPopupRoute: Routes = [
    {
        path: 'sous-region/:id/delete',
        component: SousRegionDeletePopupComponent,
        resolve: {
            sousRegion: SousRegionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ykCrfApp.sousRegion.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
