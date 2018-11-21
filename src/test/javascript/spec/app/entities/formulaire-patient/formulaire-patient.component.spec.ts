/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { YkCrfTestModule } from '../../../test.module';
import { FormulairePatientComponent } from 'app/entities/formulaire-patient/formulaire-patient.component';
import { FormulairePatientService } from 'app/entities/formulaire-patient/formulaire-patient.service';
import { FormulairePatient } from 'app/shared/model/formulaire-patient.model';

describe('Component Tests', () => {
    describe('FormulairePatient Management Component', () => {
        let comp: FormulairePatientComponent;
        let fixture: ComponentFixture<FormulairePatientComponent>;
        let service: FormulairePatientService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YkCrfTestModule],
                declarations: [FormulairePatientComponent],
                providers: []
            })
                .overrideTemplate(FormulairePatientComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(FormulairePatientComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FormulairePatientService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new FormulairePatient(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.formulairePatients[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
