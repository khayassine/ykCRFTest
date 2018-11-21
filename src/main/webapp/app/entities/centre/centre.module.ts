import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { YkCrfSharedModule } from 'app/shared';
import {
    CentreComponent,
    CentreDetailComponent,
    CentreUpdateComponent,
    CentreDeletePopupComponent,
    CentreDeleteDialogComponent,
    centreRoute,
    centrePopupRoute
} from './';

const ENTITY_STATES = [...centreRoute, ...centrePopupRoute];

@NgModule({
    imports: [YkCrfSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [CentreComponent, CentreDetailComponent, CentreUpdateComponent, CentreDeleteDialogComponent, CentreDeletePopupComponent],
    entryComponents: [CentreComponent, CentreUpdateComponent, CentreDeleteDialogComponent, CentreDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class YkCrfCentreModule {}
