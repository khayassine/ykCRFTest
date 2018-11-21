/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { YkCrfTestModule } from '../../../test.module';
import { ComposantValeurDeleteDialogComponent } from 'app/entities/composant-valeur/composant-valeur-delete-dialog.component';
import { ComposantValeurService } from 'app/entities/composant-valeur/composant-valeur.service';

describe('Component Tests', () => {
    describe('ComposantValeur Management Delete Component', () => {
        let comp: ComposantValeurDeleteDialogComponent;
        let fixture: ComponentFixture<ComposantValeurDeleteDialogComponent>;
        let service: ComposantValeurService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YkCrfTestModule],
                declarations: [ComposantValeurDeleteDialogComponent]
            })
                .overrideTemplate(ComposantValeurDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ComposantValeurDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ComposantValeurService);
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
