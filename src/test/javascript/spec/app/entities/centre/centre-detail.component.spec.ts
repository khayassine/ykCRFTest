/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { YkCrfTestModule } from '../../../test.module';
import { CentreDetailComponent } from 'app/entities/centre/centre-detail.component';
import { Centre } from 'app/shared/model/centre.model';

describe('Component Tests', () => {
    describe('Centre Management Detail Component', () => {
        let comp: CentreDetailComponent;
        let fixture: ComponentFixture<CentreDetailComponent>;
        const route = ({ data: of({ centre: new Centre(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YkCrfTestModule],
                declarations: [CentreDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(CentreDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CentreDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.centre).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
