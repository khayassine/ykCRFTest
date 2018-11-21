import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { YkCrfSharedModule } from 'app/shared';
import {
    FormulairePatientComponent,
    FormulairePatientDetailComponent,
    FormulairePatientUpdateComponent,
    FormulairePatientDeletePopupComponent,
    FormulairePatientDeleteDialogComponent,
    formulairePatientRoute,
    formulairePatientPopupRoute
} from './';

const ENTITY_STATES = [...formulairePatientRoute, ...formulairePatientPopupRoute];

@NgModule({
    imports: [YkCrfSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        FormulairePatientComponent,
        FormulairePatientDetailComponent,
        FormulairePatientUpdateComponent,
        FormulairePatientDeleteDialogComponent,
        FormulairePatientDeletePopupComponent
    ],
    entryComponents: [
        FormulairePatientComponent,
        FormulairePatientUpdateComponent,
        FormulairePatientDeleteDialogComponent,
        FormulairePatientDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class YkCrfFormulairePatientModule {}
