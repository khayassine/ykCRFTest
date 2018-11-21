import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFichePatient } from 'app/shared/model/fiche-patient.model';
import { FichePatientService } from './fiche-patient.service';

@Component({
    selector: 'jhi-fiche-patient-delete-dialog',
    templateUrl: './fiche-patient-delete-dialog.component.html'
})
export class FichePatientDeleteDialogComponent {
    fichePatient: IFichePatient;

    constructor(
        private fichePatientService: FichePatientService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.fichePatientService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'fichePatientListModification',
                content: 'Deleted an fichePatient'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-fiche-patient-delete-popup',
    template: ''
})
export class FichePatientDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ fichePatient }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(FichePatientDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.fichePatient = fichePatient;
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
