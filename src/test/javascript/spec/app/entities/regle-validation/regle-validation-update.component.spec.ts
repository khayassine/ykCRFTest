/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { YkCrfTestModule } from '../../../test.module';
import { RegleValidationUpdateComponent } from 'app/entities/regle-validation/regle-validation-update.component';
import { RegleValidationService } from 'app/entities/regle-validation/regle-validation.service';
import { RegleValidation } from 'app/shared/model/regle-validation.model';

describe('Component Tests', () => {
    describe('RegleValidation Management Update Component', () => {
        let comp: RegleValidationUpdateComponent;
        let fixture: ComponentFixture<RegleValidationUpdateComponent>;
        let service: RegleValidationService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YkCrfTestModule],
                declarations: [RegleValidationUpdateComponent]
            })
                .overrideTemplate(RegleValidationUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RegleValidationUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RegleValidationService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new RegleValidation(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.regleValidation = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new RegleValidation();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.regleValidation = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
