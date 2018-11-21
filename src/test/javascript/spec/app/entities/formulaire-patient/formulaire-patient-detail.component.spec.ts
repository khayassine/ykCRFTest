/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { YkCrfTestModule } from '../../../test.module';
import { FormulairePatientDetailComponent } from 'app/entities/formulaire-patient/formulaire-patient-detail.component';
import { FormulairePatient } from 'app/shared/model/formulaire-patient.model';

describe('Component Tests', () => {
    describe('FormulairePatient Management Detail Component', () => {
        let comp: FormulairePatientDetailComponent;
        let fixture: ComponentFixture<FormulairePatientDetailComponent>;
        const route = ({ data: of({ formulairePatient: new FormulairePatient(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YkCrfTestModule],
                declarations: [FormulairePatientDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(FormulairePatientDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(FormulairePatientDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.formulairePatient).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
