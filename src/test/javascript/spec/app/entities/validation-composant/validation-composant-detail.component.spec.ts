/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { YkCrfTestModule } from '../../../test.module';
import { ValidationComposantDetailComponent } from 'app/entities/validation-composant/validation-composant-detail.component';
import { ValidationComposant } from 'app/shared/model/validation-composant.model';

describe('Component Tests', () => {
    describe('ValidationComposant Management Detail Component', () => {
        let comp: ValidationComposantDetailComponent;
        let fixture: ComponentFixture<ValidationComposantDetailComponent>;
        const route = ({ data: of({ validationComposant: new ValidationComposant(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YkCrfTestModule],
                declarations: [ValidationComposantDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ValidationComposantDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ValidationComposantDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.validationComposant).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
