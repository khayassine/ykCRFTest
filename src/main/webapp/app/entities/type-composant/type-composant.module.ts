import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { YkCrfSharedModule } from 'app/shared';
import {
    TypeComposantComponent,
    TypeComposantDetailComponent,
    TypeComposantUpdateComponent,
    TypeComposantDeletePopupComponent,
    TypeComposantDeleteDialogComponent,
    typeComposantRoute,
    typeComposantPopupRoute
} from './';

const ENTITY_STATES = [...typeComposantRoute, ...typeComposantPopupRoute];

@NgModule({
    imports: [YkCrfSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TypeComposantComponent,
        TypeComposantDetailComponent,
        TypeComposantUpdateComponent,
        TypeComposantDeleteDialogComponent,
        TypeComposantDeletePopupComponent
    ],
    entryComponents: [
        TypeComposantComponent,
        TypeComposantUpdateComponent,
        TypeComposantDeleteDialogComponent,
        TypeComposantDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class YkCrfTypeComposantModule {}
