import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { YkCrfSharedModule } from 'app/shared';
import {
    ComposantValeurComponent,
    ComposantValeurDetailComponent,
    ComposantValeurUpdateComponent,
    ComposantValeurDeletePopupComponent,
    ComposantValeurDeleteDialogComponent,
    composantValeurRoute,
    composantValeurPopupRoute
} from './';

const ENTITY_STATES = [...composantValeurRoute, ...composantValeurPopupRoute];

@NgModule({
    imports: [YkCrfSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ComposantValeurComponent,
        ComposantValeurDetailComponent,
        ComposantValeurUpdateComponent,
        ComposantValeurDeleteDialogComponent,
        ComposantValeurDeletePopupComponent
    ],
    entryComponents: [
        ComposantValeurComponent,
        ComposantValeurUpdateComponent,
        ComposantValeurDeleteDialogComponent,
        ComposantValeurDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class YkCrfComposantValeurModule {}
