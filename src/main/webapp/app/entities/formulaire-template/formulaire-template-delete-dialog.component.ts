import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFormulaireTemplate } from 'app/shared/model/formulaire-template.model';
import { FormulaireTemplateService } from './formulaire-template.service';

@Component({
    selector: 'jhi-formulaire-template-delete-dialog',
    templateUrl: './formulaire-template-delete-dialog.component.html'
})
export class FormulaireTemplateDeleteDialogComponent {
    formulaireTemplate: IFormulaireTemplate;

    constructor(
        private formulaireTemplateService: FormulaireTemplateService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.formulaireTemplateService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'formulaireTemplateListModification',
                content: 'Deleted an formulaireTemplate'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-formulaire-template-delete-popup',
    template: ''
})
export class FormulaireTemplateDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ formulaireTemplate }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(FormulaireTemplateDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.formulaireTemplate = formulaireTemplate;
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
