/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { YkCrfTestModule } from '../../../test.module';
import { SousRegionDeleteDialogComponent } from 'app/entities/sous-region/sous-region-delete-dialog.component';
import { SousRegionService } from 'app/entities/sous-region/sous-region.service';

describe('Component Tests', () => {
    describe('SousRegion Management Delete Component', () => {
        let comp: SousRegionDeleteDialogComponent;
        let fixture: ComponentFixture<SousRegionDeleteDialogComponent>;
        let service: SousRegionService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YkCrfTestModule],
                declarations: [SousRegionDeleteDialogComponent]
            })
                .overrideTemplate(SousRegionDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SousRegionDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SousRegionService);
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
