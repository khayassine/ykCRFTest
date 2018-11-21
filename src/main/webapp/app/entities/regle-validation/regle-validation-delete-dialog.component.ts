import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRegleValidation } from 'app/shared/model/regle-validation.model';
import { RegleValidationService } from './regle-validation.service';

@Component({
    selector: 'jhi-regle-validation-delete-dialog',
    templateUrl: './regle-validation-delete-dialog.component.html'
})
export class RegleValidationDeleteDialogComponent {
    regleValidation: IRegleValidation;

    constructor(
        private regleValidationService: RegleValidationService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.regleValidationService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'regleValidationListModification',
                content: 'Deleted an regleValidation'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-regle-validation-delete-popup',
    template: ''
})
export class RegleValidationDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ regleValidation }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RegleValidationDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.regleValidation = regleValidation;
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
