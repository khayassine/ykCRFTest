import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { YkCrfEtudeModule } from './etude/etude.module';
import { YkCrfFormulaireTemplateModule } from './formulaire-template/formulaire-template.module';
import { YkCrfComposantTemplateModule } from './composant-template/composant-template.module';
import { YkCrfTypeComposantModule } from './type-composant/type-composant.module';
import { YkCrfValidationComposantModule } from './validation-composant/validation-composant.module';
import { YkCrfRegleValidationModule } from './regle-validation/regle-validation.module';
import { YkCrfRequetteModule } from './requette/requette.module';
import { YkCrfCommentaireRequetteModule } from './commentaire-requette/commentaire-requette.module';
import { YkCrfFichePatientModule } from './fiche-patient/fiche-patient.module';
import { YkCrfFormulairePatientModule } from './formulaire-patient/formulaire-patient.module';
import { YkCrfComposantValeurModule } from './composant-valeur/composant-valeur.module';
import { YkCrfCentreModule } from './centre/centre.module';
import { YkCrfVilleModule } from './ville/ville.module';
import { YkCrfRegionModule } from './region/region.module';
import { YkCrfSousRegionModule } from './sous-region/sous-region.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        YkCrfEtudeModule,
        YkCrfFormulaireTemplateModule,
        YkCrfComposantTemplateModule,
        YkCrfTypeComposantModule,
        YkCrfValidationComposantModule,
        YkCrfRegleValidationModule,
        YkCrfRequetteModule,
        YkCrfCommentaireRequetteModule,
        YkCrfFichePatientModule,
        YkCrfFormulairePatientModule,
        YkCrfComposantValeurModule,
        YkCrfCentreModule,
        YkCrfVilleModule,
        YkCrfRegionModule,
        YkCrfSousRegionModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class YkCrfEntityModule {}
