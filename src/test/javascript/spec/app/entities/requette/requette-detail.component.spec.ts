/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { YkCrfTestModule } from '../../../test.module';
import { RequetteDetailComponent } from 'app/entities/requette/requette-detail.component';
import { Requette } from 'app/shared/model/requette.model';

describe('Component Tests', () => {
    describe('Requette Management Detail Component', () => {
        let comp: RequetteDetailComponent;
        let fixture: ComponentFixture<RequetteDetailComponent>;
        const route = ({ data: of({ requette: new Requette(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YkCrfTestModule],
                declarations: [RequetteDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(RequetteDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RequetteDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.requette).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
