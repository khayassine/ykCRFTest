import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ComposantValeur } from 'app/shared/model/composant-valeur.model';
import { ComposantValeurService } from './composant-valeur.service';
import { ComposantValeurComponent } from './composant-valeur.component';
import { ComposantValeurDetailComponent } from './composant-valeur-detail.component';
import { ComposantValeurUpdateComponent } from './composant-valeur-update.component';
import { ComposantValeurDeletePopupComponent } from './composant-valeur-delete-dialog.component';
import { IComposantValeur } from 'app/shared/model/composant-valeur.model';

@Injectable({ providedIn: 'root' })
export class ComposantValeurResolve implements Resolve<IComposantValeur> {
    constructor(private service: ComposantValeurService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ComposantValeur> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ComposantValeur>) => response.ok),
                map((composantValeur: HttpResponse<ComposantValeur>) => composantValeur.body)
            );
        }
        return of(new ComposantValeur());
    }
}

export const composantValeurRoute: Routes = [
    {
        path: 'composant-valeur',
        component: ComposantValeurComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ykCrfApp.composantValeur.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'composant-valeur/:id/view',
        component: ComposantValeurDetailComponent,
        resolve: {
            composantValeur: ComposantValeurResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ykCrfApp.composantValeur.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'composant-valeur/new',
        component: ComposantValeurUpdateComponent,
        resolve: {
            composantValeur: ComposantValeurResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ykCrfApp.composantValeur.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'composant-valeur/:id/edit',
        component: ComposantValeurUpdateComponent,
        resolve: {
            composantValeur: ComposantValeurResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ykCrfApp.composantValeur.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const composantValeurPopupRoute: Routes = [
    {
        path: 'composant-valeur/:id/delete',
        component: ComposantValeurDeletePopupComponent,
        resolve: {
            composantValeur: ComposantValeurResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ykCrfApp.composantValeur.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
