import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { YkCrfSharedModule } from 'app/shared';
import {
    EtudeComponent,
    EtudeDetailComponent,
    EtudeUpdateComponent,
    EtudeDeletePopupComponent,
    EtudeDeleteDialogComponent,
    etudeRoute,
    etudePopupRoute
} from './';

const ENTITY_STATES = [...etudeRoute, ...etudePopupRoute];

@NgModule({
    imports: [YkCrfSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [EtudeComponent, EtudeDetailComponent, EtudeUpdateComponent, EtudeDeleteDialogComponent, EtudeDeletePopupComponent],
    entryComponents: [EtudeComponent, EtudeUpdateComponent, EtudeDeleteDialogComponent, EtudeDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class YkCrfEtudeModule {}
