/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { YkCrfTestModule } from '../../../test.module';
import { EtudeUpdateComponent } from 'app/entities/etude/etude-update.component';
import { EtudeService } from 'app/entities/etude/etude.service';
import { Etude } from 'app/shared/model/etude.model';

describe('Component Tests', () => {
    describe('Etude Management Update Component', () => {
        let comp: EtudeUpdateComponent;
        let fixture: ComponentFixture<EtudeUpdateComponent>;
        let service: EtudeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YkCrfTestModule],
                declarations: [EtudeUpdateComponent]
            })
                .overrideTemplate(EtudeUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EtudeUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EtudeService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Etude(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.etude = entity;
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
                    const entity = new Etude();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.etude = entity;
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
