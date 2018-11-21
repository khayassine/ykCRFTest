import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IComposantValeur } from 'app/shared/model/composant-valeur.model';
import { ComposantValeurService } from './composant-valeur.service';

@Component({
    selector: 'jhi-composant-valeur-delete-dialog',
    templateUrl: './composant-valeur-delete-dialog.component.html'
})
export class ComposantValeurDeleteDialogComponent {
    composantValeur: IComposantValeur;

    constructor(
        private composantValeurService: ComposantValeurService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.composantValeurService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'composantValeurListModification',
                content: 'Deleted an composantValeur'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-composant-valeur-delete-popup',
    template: ''
})
export class ComposantValeurDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ composantValeur }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ComposantValeurDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.composantValeur = composantValeur;
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
