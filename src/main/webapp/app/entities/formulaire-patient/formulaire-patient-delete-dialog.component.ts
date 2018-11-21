import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFormulairePatient } from 'app/shared/model/formulaire-patient.model';
import { FormulairePatientService } from './formulaire-patient.service';

@Component({
    selector: 'jhi-formulaire-patient-delete-dialog',
    templateUrl: './formulaire-patient-delete-dialog.component.html'
})
export class FormulairePatientDeleteDialogComponent {
    formulairePatient: IFormulairePatient;

    constructor(
        private formulairePatientService: FormulairePatientService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.formulairePatientService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'formulairePatientListModification',
                content: 'Deleted an formulairePatient'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-formulaire-patient-delete-popup',
    template: ''
})
export class FormulairePatientDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ formulairePatient }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(FormulairePatientDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.formulairePatient = formulairePatient;
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
