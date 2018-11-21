/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { YkCrfTestModule } from '../../../test.module';
import { RegleValidationDetailComponent } from 'app/entities/regle-validation/regle-validation-detail.component';
import { RegleValidation } from 'app/shared/model/regle-validation.model';

describe('Component Tests', () => {
    describe('RegleValidation Management Detail Component', () => {
        let comp: RegleValidationDetailComponent;
        let fixture: ComponentFixture<RegleValidationDetailComponent>;
        const route = ({ data: of({ regleValidation: new RegleValidation(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YkCrfTestModule],
                declarations: [RegleValidationDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(RegleValidationDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RegleValidationDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.regleValidation).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
