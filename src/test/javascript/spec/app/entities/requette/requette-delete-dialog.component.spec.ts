/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { YkCrfTestModule } from '../../../test.module';
import { RequetteDeleteDialogComponent } from 'app/entities/requette/requette-delete-dialog.component';
import { RequetteService } from 'app/entities/requette/requette.service';

describe('Component Tests', () => {
    describe('Requette Management Delete Component', () => {
        let comp: RequetteDeleteDialogComponent;
        let fixture: ComponentFixture<RequetteDeleteDialogComponent>;
        let service: RequetteService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YkCrfTestModule],
                declarations: [RequetteDeleteDialogComponent]
            })
                .overrideTemplate(RequetteDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RequetteDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RequetteService);
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
