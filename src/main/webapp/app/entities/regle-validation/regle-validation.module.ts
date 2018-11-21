import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { YkCrfSharedModule } from 'app/shared';
import {
    RegleValidationComponent,
    RegleValidationDetailComponent,
    RegleValidationUpdateComponent,
    RegleValidationDeletePopupComponent,
    RegleValidationDeleteDialogComponent,
    regleValidationRoute,
    regleValidationPopupRoute
} from './';

const ENTITY_STATES = [...regleValidationRoute, ...regleValidationPopupRoute];

@NgModule({
    imports: [YkCrfSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        RegleValidationComponent,
        RegleValidationDetailComponent,
        RegleValidationUpdateComponent,
        RegleValidationDeleteDialogComponent,
        RegleValidationDeletePopupComponent
    ],
    entryComponents: [
        RegleValidationComponent,
        RegleValidationUpdateComponent,
        RegleValidationDeleteDialogComponent,
        RegleValidationDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class YkCrfRegleValidationModule {}
