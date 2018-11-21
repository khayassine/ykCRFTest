/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { YkCrfTestModule } from '../../../test.module';
import { SousRegionDetailComponent } from 'app/entities/sous-region/sous-region-detail.component';
import { SousRegion } from 'app/shared/model/sous-region.model';

describe('Component Tests', () => {
    describe('SousRegion Management Detail Component', () => {
        let comp: SousRegionDetailComponent;
        let fixture: ComponentFixture<SousRegionDetailComponent>;
        const route = ({ data: of({ sousRegion: new SousRegion(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YkCrfTestModule],
                declarations: [SousRegionDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SousRegionDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SousRegionDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.sousRegion).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
