import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { YkCrfSharedModule } from 'app/shared';
import {
    RequetteComponent,
    RequetteDetailComponent,
    RequetteUpdateComponent,
    RequetteDeletePopupComponent,
    RequetteDeleteDialogComponent,
    requetteRoute,
    requettePopupRoute
} from './';

const ENTITY_STATES = [...requetteRoute, ...requettePopupRoute];

@NgModule({
    imports: [YkCrfSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        RequetteComponent,
        RequetteDetailComponent,
        RequetteUpdateComponent,
        RequetteDeleteDialogComponent,
        RequetteDeletePopupComponent
    ],
    entryComponents: [RequetteComponent, RequetteUpdateComponent, RequetteDeleteDialogComponent, RequetteDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class YkCrfRequetteModule {}
