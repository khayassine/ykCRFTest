/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { YkCrfTestModule } from '../../../test.module';
import { TypeComposantUpdateComponent } from 'app/entities/type-composant/type-composant-update.component';
import { TypeComposantService } from 'app/entities/type-composant/type-composant.service';
import { TypeComposant } from 'app/shared/model/type-composant.model';

describe('Component Tests', () => {
    describe('TypeComposant Management Update Component', () => {
        let comp: TypeComposantUpdateComponent;
        let fixture: ComponentFixture<TypeComposantUpdateComponent>;
        let service: TypeComposantService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YkCrfTestModule],
                declarations: [TypeComposantUpdateComponent]
            })
                .overrideTemplate(TypeComposantUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TypeComposantUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TypeComposantService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new TypeComposant(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.typeComposant = entity;
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
                    const entity = new TypeComposant();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.typeComposant = entity;
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
