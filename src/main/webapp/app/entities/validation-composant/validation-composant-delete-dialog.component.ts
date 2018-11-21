import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IValidationComposant } from 'app/shared/model/validation-composant.model';
import { ValidationComposantService } from './validation-composant.service';

@Component({
    selector: 'jhi-validation-composant-delete-dialog',
    templateUrl: './validation-composant-delete-dialog.component.html'
})
export class ValidationComposantDeleteDialogComponent {
    validationComposant: IValidationComposant;

    constructor(
        private validationComposantService: ValidationComposantService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.validationComposantService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'validationComposantListModification',
                content: 'Deleted an validationComposant'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-validation-composant-delete-popup',
    template: ''
})
export class ValidationComposantDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ validationComposant }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ValidationComposantDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.validationComposant = validationComposant;
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
