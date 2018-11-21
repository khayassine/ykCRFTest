/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { YkCrfTestModule } from '../../../test.module';
import { ValidationComposantUpdateComponent } from 'app/entities/validation-composant/validation-composant-update.component';
import { ValidationComposantService } from 'app/entities/validation-composant/validation-composant.service';
import { ValidationComposant } from 'app/shared/model/validation-composant.model';

describe('Component Tests', () => {
    describe('ValidationComposant Management Update Component', () => {
        let comp: ValidationComposantUpdateComponent;
        let fixture: ComponentFixture<ValidationComposantUpdateComponent>;
        let service: ValidationComposantService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YkCrfTestModule],
                declarations: [ValidationComposantUpdateComponent]
            })
                .overrideTemplate(ValidationComposantUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ValidationComposantUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ValidationComposantService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ValidationComposant(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.validationComposant = entity;
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
                    const entity = new ValidationComposant();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.validationComposant = entity;
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
