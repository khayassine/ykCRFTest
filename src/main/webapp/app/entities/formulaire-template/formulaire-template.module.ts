import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { YkCrfSharedModule } from 'app/shared';
import {
    FormulaireTemplateComponent,
    FormulaireTemplateDetailComponent,
    FormulaireTemplateUpdateComponent,
    FormulaireTemplateDeletePopupComponent,
    FormulaireTemplateDeleteDialogComponent,
    formulaireTemplateRoute,
    formulaireTemplatePopupRoute
} from './';

const ENTITY_STATES = [...formulaireTemplateRoute, ...formulaireTemplatePopupRoute];

@NgModule({
    imports: [YkCrfSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        FormulaireTemplateComponent,
        FormulaireTemplateDetailComponent,
        FormulaireTemplateUpdateComponent,
        FormulaireTemplateDeleteDialogComponent,
        FormulaireTemplateDeletePopupComponent
    ],
    entryComponents: [
        FormulaireTemplateComponent,
        FormulaireTemplateUpdateComponent,
        FormulaireTemplateDeleteDialogComponent,
        FormulaireTemplateDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class YkCrfFormulaireTemplateModule {}
