/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { YkCrfTestModule } from '../../../test.module';
import { TypeComposantDeleteDialogComponent } from 'app/entities/type-composant/type-composant-delete-dialog.component';
import { TypeComposantService } from 'app/entities/type-composant/type-composant.service';

describe('Component Tests', () => {
    describe('TypeComposant Management Delete Component', () => {
        let comp: TypeComposantDeleteDialogComponent;
        let fixture: ComponentFixture<TypeComposantDeleteDialogComponent>;
        let service: TypeComposantService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YkCrfTestModule],
                declarations: [TypeComposantDeleteDialogComponent]
            })
                .overrideTemplate(TypeComposantDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TypeComposantDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TypeComposantService);
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
