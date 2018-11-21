import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IComposantTemplate } from 'app/shared/model/composant-template.model';
import { ComposantTemplateService } from './composant-template.service';

@Component({
    selector: 'jhi-composant-template-delete-dialog',
    templateUrl: './composant-template-delete-dialog.component.html'
})
export class ComposantTemplateDeleteDialogComponent {
    composantTemplate: IComposantTemplate;

    constructor(
        private composantTemplateService: ComposantTemplateService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.composantTemplateService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'composantTemplateListModification',
                content: 'Deleted an composantTemplate'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-composant-template-delete-popup',
    template: ''
})
export class ComposantTemplateDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ composantTemplate }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ComposantTemplateDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.composantTemplate = composantTemplate;
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
