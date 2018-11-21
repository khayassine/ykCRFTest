import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { YkCrfSharedModule } from 'app/shared';
import {
    VilleComponent,
    VilleDetailComponent,
    VilleUpdateComponent,
    VilleDeletePopupComponent,
    VilleDeleteDialogComponent,
    villeRoute,
    villePopupRoute
} from './';

const ENTITY_STATES = [...villeRoute, ...villePopupRoute];

@NgModule({
    imports: [YkCrfSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [VilleComponent, VilleDetailComponent, VilleUpdateComponent, VilleDeleteDialogComponent, VilleDeletePopupComponent],
    entryComponents: [VilleComponent, VilleUpdateComponent, VilleDeleteDialogComponent, VilleDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class YkCrfVilleModule {}
