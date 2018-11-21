import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICommentaireRequette } from 'app/shared/model/commentaire-requette.model';

@Component({
    selector: 'jhi-commentaire-requette-detail',
    templateUrl: './commentaire-requette-detail.component.html'
})
export class CommentaireRequetteDetailComponent implements OnInit {
    commentaireRequette: ICommentaireRequette;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ commentaireRequette }) => {
            this.commentaireRequette = commentaireRequette;
        });
    }

    previousState() {
        window.history.back();
    }
}
