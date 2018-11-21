/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { YkCrfTestModule } from '../../../test.module';
import { CentreDeleteDialogComponent } from 'app/entities/centre/centre-delete-dialog.component';
import { CentreService } from 'app/entities/centre/centre.service';

describe('Component Tests', () => {
    describe('Centre Management Delete Component', () => {
        let comp: CentreDeleteDialogComponent;
        let fixture: ComponentFixture<CentreDeleteDialogComponent>;
        let service: CentreService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YkCrfTestModule],
                declarations: [CentreDeleteDialogComponent]
            })
                .overrideTemplate(CentreDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CentreDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CentreService);
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
