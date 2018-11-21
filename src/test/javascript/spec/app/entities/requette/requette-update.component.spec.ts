/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { YkCrfTestModule } from '../../../test.module';
import { RequetteUpdateComponent } from 'app/entities/requette/requette-update.component';
import { RequetteService } from 'app/entities/requette/requette.service';
import { Requette } from 'app/shared/model/requette.model';

describe('Component Tests', () => {
    describe('Requette Management Update Component', () => {
        let comp: RequetteUpdateComponent;
        let fixture: ComponentFixture<RequetteUpdateComponent>;
        let service: RequetteService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YkCrfTestModule],
                declarations: [RequetteUpdateComponent]
            })
                .overrideTemplate(RequetteUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RequetteUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RequetteService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Requette(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.requette = entity;
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
                    const entity = new Requette();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.requette = entity;
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
