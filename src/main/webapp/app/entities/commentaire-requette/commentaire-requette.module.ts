import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { YkCrfSharedModule } from 'app/shared';
import {
    CommentaireRequetteComponent,
    CommentaireRequetteDetailComponent,
    CommentaireRequetteUpdateComponent,
    CommentaireRequetteDeletePopupComponent,
    CommentaireRequetteDeleteDialogComponent,
    commentaireRequetteRoute,
    commentaireRequettePopupRoute
} from './';

const ENTITY_STATES = [...commentaireRequetteRoute, ...commentaireRequettePopupRoute];

@NgModule({
    imports: [YkCrfSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CommentaireRequetteComponent,
        CommentaireRequetteDetailComponent,
        CommentaireRequetteUpdateComponent,
        CommentaireRequetteDeleteDialogComponent,
        CommentaireRequetteDeletePopupComponent
    ],
    entryComponents: [
        CommentaireRequetteComponent,
        CommentaireRequetteUpdateComponent,
        CommentaireRequetteDeleteDialogComponent,
        CommentaireRequetteDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class YkCrfCommentaireRequetteModule {}
