/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { YkCrfTestModule } from '../../../test.module';
import { ComposantTemplateUpdateComponent } from 'app/entities/composant-template/composant-template-update.component';
import { ComposantTemplateService } from 'app/entities/composant-template/composant-template.service';
import { ComposantTemplate } from 'app/shared/model/composant-template.model';

describe('Component Tests', () => {
    describe('ComposantTemplate Management Update Component', () => {
        let comp: ComposantTemplateUpdateComponent;
        let fixture: ComponentFixture<ComposantTemplateUpdateComponent>;
        let service: ComposantTemplateService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YkCrfTestModule],
                declarations: [ComposantTemplateUpdateComponent]
            })
                .overrideTemplate(ComposantTemplateUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ComposantTemplateUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ComposantTemplateService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ComposantTemplate(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.composantTemplate = entity;
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
                    const entity = new ComposantTemplate();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.composantTemplate = entity;
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
