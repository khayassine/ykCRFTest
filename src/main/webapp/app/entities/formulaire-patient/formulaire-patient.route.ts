import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { FormulairePatient } from 'app/shared/model/formulaire-patient.model';
import { FormulairePatientService } from './formulaire-patient.service';
import { FormulairePatientComponent } from './formulaire-patient.component';
import { FormulairePatientDetailComponent } from './formulaire-patient-detail.component';
import { FormulairePatientUpdateComponent } from './formulaire-patient-update.component';
import { FormulairePatientDeletePopupComponent } from './formulaire-patient-delete-dialog.component';
import { IFormulairePatient } from 'app/shared/model/formulaire-patient.model';

@Injectable({ providedIn: 'root' })
export class FormulairePatientResolve implements Resolve<IFormulairePatient> {
    constructor(private service: FormulairePatientService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<FormulairePatient> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<FormulairePatient>) => response.ok),
                map((formulairePatient: HttpResponse<FormulairePatient>) => formulairePatient.body)
            );
        }
        return of(new FormulairePatient());
    }
}

export const formulairePatientRoute: Routes = [
    {
        path: 'formulaire-patient',
        component: FormulairePatientComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ykCrfApp.formulairePatient.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'formulaire-patient/:id/view',
        component: FormulairePatientDetailComponent,
        resolve: {
            formulairePatient: FormulairePatientResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ykCrfApp.formulairePatient.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'formulaire-patient/new',
        component: FormulairePatientUpdateComponent,
        resolve: {
            formulairePatient: FormulairePatientResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ykCrfApp.formulairePatient.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'formulaire-patient/:id/edit',
        component: FormulairePatientUpdateComponent,
        resolve: {
            formulairePatient: FormulairePatientResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ykCrfApp.formulairePatient.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const formulairePatientPopupRoute: Routes = [
    {
        path: 'formulaire-patient/:id/delete',
        component: FormulairePatientDeletePopupComponent,
        resolve: {
            formulairePatient: FormulairePatientResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ykCrfApp.formulairePatient.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
