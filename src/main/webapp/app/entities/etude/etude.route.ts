import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Etude } from 'app/shared/model/etude.model';
import { EtudeService } from './etude.service';
import { EtudeComponent } from './etude.component';
import { EtudeDetailComponent } from './etude-detail.component';
import { EtudeUpdateComponent } from './etude-update.component';
import { EtudeDeletePopupComponent } from './etude-delete-dialog.component';
import { IEtude } from 'app/shared/model/etude.model';

@Injectable({ providedIn: 'root' })
export class EtudeResolve implements Resolve<IEtude> {
    constructor(private service: EtudeService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Etude> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Etude>) => response.ok),
                map((etude: HttpResponse<Etude>) => etude.body)
            );
        }
        return of(new Etude());
    }
}

export const etudeRoute: Routes = [
    {
        path: 'etude',
        component: EtudeComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ykCrfApp.etude.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'etude/:id/view',
        component: EtudeDetailComponent,
        resolve: {
            etude: EtudeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ykCrfApp.etude.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'etude/new',
        component: EtudeUpdateComponent,
        resolve: {
            etude: EtudeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ykCrfApp.etude.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'etude/:id/edit',
        component: EtudeUpdateComponent,
        resolve: {
            etude: EtudeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ykCrfApp.etude.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const etudePopupRoute: Routes = [
    {
        path: 'etude/:id/delete',
        component: EtudeDeletePopupComponent,
        resolve: {
            etude: EtudeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ykCrfApp.etude.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
