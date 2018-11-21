/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { YkCrfTestModule } from '../../../test.module';
import { FormulairePatientDeleteDialogComponent } from 'app/entities/formulaire-patient/formulaire-patient-delete-dialog.component';
import { FormulairePatientService } from 'app/entities/formulaire-patient/formulaire-patient.service';

describe('Component Tests', () => {
    describe('FormulairePatient Management Delete Component', () => {
        let comp: FormulairePatientDeleteDialogComponent;
        let fixture: ComponentFixture<FormulairePatientDeleteDialogComponent>;
        let service: FormulairePatientService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YkCrfTestModule],
                declarations: [FormulairePatientDeleteDialogComponent]
            })
                .overrideTemplate(FormulairePatientDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(FormulairePatientDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FormulairePatientService);
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
