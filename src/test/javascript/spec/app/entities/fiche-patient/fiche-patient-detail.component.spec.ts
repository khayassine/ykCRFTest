/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { YkCrfTestModule } from '../../../test.module';
import { FichePatientDetailComponent } from 'app/entities/fiche-patient/fiche-patient-detail.component';
import { FichePatient } from 'app/shared/model/fiche-patient.model';

describe('Component Tests', () => {
    describe('FichePatient Management Detail Component', () => {
        let comp: FichePatientDetailComponent;
        let fixture: ComponentFixture<FichePatientDetailComponent>;
        const route = ({ data: of({ fichePatient: new FichePatient(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YkCrfTestModule],
                declarations: [FichePatientDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(FichePatientDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(FichePatientDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.fichePatient).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
