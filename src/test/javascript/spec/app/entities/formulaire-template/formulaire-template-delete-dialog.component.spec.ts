/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { YkCrfTestModule } from '../../../test.module';
import { FormulaireTemplateDeleteDialogComponent } from 'app/entities/formulaire-template/formulaire-template-delete-dialog.component';
import { FormulaireTemplateService } from 'app/entities/formulaire-template/formulaire-template.service';

describe('Component Tests', () => {
    describe('FormulaireTemplate Management Delete Component', () => {
        let comp: FormulaireTemplateDeleteDialogComponent;
        let fixture: ComponentFixture<FormulaireTemplateDeleteDialogComponent>;
        let service: FormulaireTemplateService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YkCrfTestModule],
                declarations: [FormulaireTemplateDeleteDialogComponent]
            })
                .overrideTemplate(FormulaireTemplateDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(FormulaireTemplateDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FormulaireTemplateService);
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
