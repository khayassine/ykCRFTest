import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { FormulaireTemplate } from 'app/shared/model/formulaire-template.model';
import { FormulaireTemplateService } from './formulaire-template.service';
import { FormulaireTemplateComponent } from './formulaire-template.component';
import { FormulaireTemplateDetailComponent } from './formulaire-template-detail.component';
import { FormulaireTemplateUpdateComponent } from './formulaire-template-update.component';
import { FormulaireTemplateDeletePopupComponent } from './formulaire-template-delete-dialog.component';
import { IFormulaireTemplate } from 'app/shared/model/formulaire-template.model';

@Injectable({ providedIn: 'root' })
export class FormulaireTemplateResolve implements Resolve<IFormulaireTemplate> {
    constructor(private service: FormulaireTemplateService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<FormulaireTemplate> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<FormulaireTemplate>) => response.ok),
                map((formulaireTemplate: HttpResponse<FormulaireTemplate>) => formulaireTemplate.body)
            );
        }
        return of(new FormulaireTemplate());
    }
}

export const formulaireTemplateRoute: Routes = [
    {
        path: 'formulaire-template',
        component: FormulaireTemplateComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'ykCrfApp.formulaireTemplate.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'formulaire-template/:id/view',
        component: FormulaireTemplateDetailComponent,
        resolve: {
            formulaireTemplate: FormulaireTemplateResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ykCrfApp.formulaireTemplate.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'formulaire-template/new',
        component: FormulaireTemplateUpdateComponent,
        resolve: {
            formulaireTemplate: FormulaireTemplateResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ykCrfApp.formulaireTemplate.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'formulaire-template/:id/edit',
        component: FormulaireTemplateUpdateComponent,
        resolve: {
            formulaireTemplate: FormulaireTemplateResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ykCrfApp.formulaireTemplate.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const formulaireTemplatePopupRoute: Routes = [
    {
        path: 'formulaire-template/:id/delete',
        component: FormulaireTemplateDeletePopupComponent,
        resolve: {
            formulaireTemplate: FormulaireTemplateResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ykCrfApp.formulaireTemplate.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
