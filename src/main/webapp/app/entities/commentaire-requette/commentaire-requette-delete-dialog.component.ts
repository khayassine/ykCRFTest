import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICommentaireRequette } from 'app/shared/model/commentaire-requette.model';
import { CommentaireRequetteService } from './commentaire-requette.service';

@Component({
    selector: 'jhi-commentaire-requette-delete-dialog',
    templateUrl: './commentaire-requette-delete-dialog.component.html'
})
export class CommentaireRequetteDeleteDialogComponent {
    commentaireRequette: ICommentaireRequette;

    constructor(
        private commentaireRequetteService: CommentaireRequetteService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.commentaireRequetteService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'commentaireRequetteListModification',
                content: 'Deleted an commentaireRequette'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-commentaire-requette-delete-popup',
    template: ''
})
export class CommentaireRequetteDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ commentaireRequette }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CommentaireRequetteDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.commentaireRequette = commentaireRequette;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
