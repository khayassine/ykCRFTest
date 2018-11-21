import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { YkCrfSharedModule } from 'app/shared';
import {
    FichePatientComponent,
    FichePatientDetailComponent,
    FichePatientUpdateComponent,
    FichePatientDeletePopupComponent,
    FichePatientDeleteDialogComponent,
    fichePatientRoute,
    fichePatientPopupRoute
} from './';

const ENTITY_STATES = [...fichePatientRoute, ...fichePatientPopupRoute];

@NgModule({
    imports: [YkCrfSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        FichePatientComponent,
        FichePatientDetailComponent,
        FichePatientUpdateComponent,
        FichePatientDeleteDialogComponent,
        FichePatientDeletePopupComponent
    ],
    entryComponents: [
        FichePatientComponent,
        FichePatientUpdateComponent,
        FichePatientDeleteDialogComponent,
        FichePatientDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class YkCrfFichePatientModule {}
