/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { YkCrfTestModule } from '../../../test.module';
import { SousRegionUpdateComponent } from 'app/entities/sous-region/sous-region-update.component';
import { SousRegionService } from 'app/entities/sous-region/sous-region.service';
import { SousRegion } from 'app/shared/model/sous-region.model';

describe('Component Tests', () => {
    describe('SousRegion Management Update Component', () => {
        let comp: SousRegionUpdateComponent;
        let fixture: ComponentFixture<SousRegionUpdateComponent>;
        let service: SousRegionService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YkCrfTestModule],
                declarations: [SousRegionUpdateComponent]
            })
                .overrideTemplate(SousRegionUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SousRegionUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SousRegionService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new SousRegion(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.sousRegion = entity;
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
                    const entity = new SousRegion();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.sousRegion = entity;
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
