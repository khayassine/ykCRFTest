/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { YkCrfTestModule } from '../../../test.module';
import { CommentaireRequetteDeleteDialogComponent } from 'app/entities/commentaire-requette/commentaire-requette-delete-dialog.component';
import { CommentaireRequetteService } from 'app/entities/commentaire-requette/commentaire-requette.service';

describe('Component Tests', () => {
    describe('CommentaireRequette Management Delete Component', () => {
        let comp: CommentaireRequetteDeleteDialogComponent;
        let fixture: ComponentFixture<CommentaireRequetteDeleteDialogComponent>;
        let service: CommentaireRequetteService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YkCrfTestModule],
                declarations: [CommentaireRequetteDeleteDialogComponent]
            })
                .overrideTemplate(CommentaireRequetteDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CommentaireRequetteDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CommentaireRequetteService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
