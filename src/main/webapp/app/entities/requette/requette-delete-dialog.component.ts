import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRequette } from 'app/shared/model/requette.model';
import { RequetteService } from './requette.service';

@Component({
    selector: 'jhi-requette-delete-dialog',
    templateUrl: './requette-delete-dialog.component.html'
})
export class RequetteDeleteDialogComponent {
    requette: IRequette;

    constructor(private requetteService: RequetteService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.requetteService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'requetteListModification',
                content: 'Deleted an requette'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-requette-delete-popup',
    template: ''
})
export class RequetteDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ requette }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RequetteDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.requette = requette;
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
