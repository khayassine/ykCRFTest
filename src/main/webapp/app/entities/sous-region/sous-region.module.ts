import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { YkCrfSharedModule } from 'app/shared';
import {
    SousRegionComponent,
    SousRegionDetailComponent,
    SousRegionUpdateComponent,
    SousRegionDeletePopupComponent,
    SousRegionDeleteDialogComponent,
    sousRegionRoute,
    sousRegionPopupRoute
} from './';

const ENTITY_STATES = [...sousRegionRoute, ...sousRegionPopupRoute];

@NgModule({
    imports: [YkCrfSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SousRegionComponent,
        SousRegionDetailComponent,
        SousRegionUpdateComponent,
        SousRegionDeleteDialogComponent,
        SousRegionDeletePopupComponent
    ],
    entryComponents: [SousRegionComponent, SousRegionUpdateComponent, SousRegionDeleteDialogComponent, SousRegionDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class YkCrfSousRegionModule {}
