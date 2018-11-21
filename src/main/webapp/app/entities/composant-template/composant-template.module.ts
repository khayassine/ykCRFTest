import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { YkCrfSharedModule } from 'app/shared';
import {
    ComposantTemplateComponent,
    ComposantTemplateDetailComponent,
    ComposantTemplateUpdateComponent,
    ComposantTemplateDeletePopupComponent,
    ComposantTemplateDeleteDialogComponent,
    composantTemplateRoute,
    composantTemplatePopupRoute
} from './';

const ENTITY_STATES = [...composantTemplateRoute, ...composantTemplatePopupRoute];

@NgModule({
    imports: [YkCrfSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ComposantTemplateComponent,
        ComposantTemplateDetailComponent,
        ComposantTemplateUpdateComponent,
        ComposantTemplateDeleteDialogComponent,
        ComposantTemplateDeletePopupComponent
    ],
    entryComponents: [
        ComposantTemplateComponent,
        ComposantTemplateUpdateComponent,
        ComposantTemplateDeleteDialogComponent,
        ComposantTemplateDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class YkCrfComposantTemplateModule {}
