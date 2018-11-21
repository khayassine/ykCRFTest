import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ComposantTemplate } from 'app/shared/model/composant-template.model';
import { ComposantTemplateService } from './composant-template.service';
import { ComposantTemplateComponent } from './composant-template.component';
import { ComposantTemplateDetailComponent } from './composant-template-detail.component';
import { ComposantTemplateUpdateComponent } from './composant-template-update.component';
import { ComposantTemplateDeletePopupComponent } from './composant-template-delete-dialog.component';
import { IComposantTemplate } from 'app/shared/model/composant-template.model';

@Injectable({ providedIn: 'root' })
export class ComposantTemplateResolve implements Resolve<IComposantTemplate> {
    constructor(private service: ComposantTemplateService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ComposantTemplate> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ComposantTemplate>) => response.ok),
                map((composantTemplate: HttpResponse<ComposantTemplate>) => composantTemplate.body)
            );
        }
        return of(new ComposantTemplate());
    }
}

export const composantTemplateRoute: Routes = [
    {
        path: 'composant-template',
        component: ComposantTemplateComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'ykCrfApp.composantTemplate.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'composant-template/:id/view',
        component: ComposantTemplateDetailComponent,
        resolve: {
            composantTemplate: ComposantTemplateResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ykCrfApp.composantTemplate.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'composant-template/new',
        component: ComposantTemplateUpdateComponent,
        resolve: {
            composantTemplate: ComposantTemplateResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ykCrfApp.composantTemplate.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'composant-template/:id/edit',
        component: ComposantTemplateUpdateComponent,
        resolve: {
            composantTemplate: ComposantTemplateResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ykCrfApp.composantTemplate.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const composantTemplatePopupRoute: Routes = [
    {
        path: 'composant-template/:id/delete',
        component: ComposantTemplateDeletePopupComponent,
        resolve: {
            composantTemplate: ComposantTemplateResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ykCrfApp.composantTemplate.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
