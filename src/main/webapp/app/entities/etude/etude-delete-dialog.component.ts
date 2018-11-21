import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEtude } from 'app/shared/model/etude.model';
import { EtudeService } from './etude.service';

@Component({
    selector: 'jhi-etude-delete-dialog',
    templateUrl: './etude-delete-dialog.component.html'
})
export class EtudeDeleteDialogComponent {
    etude: IEtude;

    constructor(private etudeService: EtudeService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.etudeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'etudeListModification',
                content: 'Deleted an etude'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-etude-delete-popup',
    template: ''
})
export class EtudeDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ etude }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(EtudeDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.etude = etude;
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
