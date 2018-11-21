import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITypeComposant } from 'app/shared/model/type-composant.model';
import { TypeComposantService } from './type-composant.service';

@Component({
    selector: 'jhi-type-composant-delete-dialog',
    templateUrl: './type-composant-delete-dialog.component.html'
})
export class TypeComposantDeleteDialogComponent {
    typeComposant: ITypeComposant;

    constructor(
        private typeComposantService: TypeComposantService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.typeComposantService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'typeComposantListModification',
                content: 'Deleted an typeComposant'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-type-composant-delete-popup',
    template: ''
})
export class TypeComposantDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ typeComposant }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TypeComposantDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.typeComposant = typeComposant;
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
