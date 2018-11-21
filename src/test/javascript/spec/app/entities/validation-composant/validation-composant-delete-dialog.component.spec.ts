/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { YkCrfTestModule } from '../../../test.module';
import { ValidationComposantDeleteDialogComponent } from 'app/entities/validation-composant/validation-composant-delete-dialog.component';
import { ValidationComposantService } from 'app/entities/validation-composant/validation-composant.service';

describe('Component Tests', () => {
    describe('ValidationComposant Management Delete Component', () => {
        let comp: ValidationComposantDeleteDialogComponent;
        let fixture: ComponentFixture<ValidationComposantDeleteDialogComponent>;
        let service: ValidationComposantService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YkCrfTestModule],
                declarations: [ValidationComposantDeleteDialogComponent]
            })
                .overrideTemplate(ValidationComposantDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ValidationComposantDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ValidationComposantService);
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
