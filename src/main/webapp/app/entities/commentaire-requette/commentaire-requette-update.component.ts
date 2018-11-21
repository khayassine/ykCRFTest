import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ICommentaireRequette } from 'app/shared/model/commentaire-requette.model';
import { CommentaireRequetteService } from './commentaire-requette.service';
import { IRequette } from 'app/shared/model/requette.model';
import { RequetteService } from 'app/entities/requette';

@Component({
    selector: 'jhi-commentaire-requette-update',
    templateUrl: './commentaire-requette-update.component.html'
})
export class CommentaireRequetteUpdateComponent implements OnInit {
    commentaireRequette: ICommentaireRequette;
    isSaving: boolean;

    requettes: IRequette[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private commentaireRequetteService: CommentaireRequetteService,
        private requetteService: RequetteService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ commentaireRequette }) => {
            this.commentaireRequette = commentaireRequette;
        });
        this.requetteService.query().subscribe(
            (res: HttpResponse<IRequette[]>) => {
                this.requettes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.commentaireRequette.id !== undefined) {
            this.subscribeToSaveResponse(this.commentaireRequetteService.update(this.commentaireRequette));
        } else {
            this.subscribeToSaveResponse(this.commentaireRequetteService.create(this.commentaireRequette));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ICommentaireRequette>>) {
        result.subscribe((res: HttpResponse<ICommentaireRequette>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackRequetteById(index: number, item: IRequette) {
        return item.id;
    }
}
