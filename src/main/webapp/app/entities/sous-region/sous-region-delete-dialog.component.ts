import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISousRegion } from 'app/shared/model/sous-region.model';
import { SousRegionService } from './sous-region.service';

@Component({
    selector: 'jhi-sous-region-delete-dialog',
    templateUrl: './sous-region-delete-dialog.component.html'
})
export class SousRegionDeleteDialogComponent {
    sousRegion: ISousRegion;

    constructor(private sousRegionService: SousRegionService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.sousRegionService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'sousRegionListModification',
                content: 'Deleted an sousRegion'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-sous-region-delete-popup',
    template: ''
})
export class SousRegionDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sousRegion }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SousRegionDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.sousRegion = sousRegion;
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
