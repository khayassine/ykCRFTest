import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { YkCrfSharedModule } from 'app/shared';
import {
    ValidationComposantComponent,
    ValidationComposantDetailComponent,
    ValidationComposantUpdateComponent,
    ValidationComposantDeletePopupComponent,
    ValidationComposantDeleteDialogComponent,
    validationComposantRoute,
    validationComposantPopupRoute
} from './';

const ENTITY_STATES = [...validationComposantRoute, ...validationComposantPopupRoute];

@NgModule({
    imports: [YkCrfSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ValidationComposantComponent,
        ValidationComposantDetailComponent,
        ValidationComposantUpdateComponent,
        ValidationComposantDeleteDialogComponent,
        ValidationComposantDeletePopupComponent
    ],
    entryComponents: [
        ValidationComposantComponent,
        ValidationComposantUpdateComponent,
        ValidationComposantDeleteDialogComponent,
        ValidationComposantDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class YkCrfValidationComposantModule {}
