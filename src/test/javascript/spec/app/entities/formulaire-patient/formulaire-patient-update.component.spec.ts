/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { YkCrfTestModule } from '../../../test.module';
import { FormulairePatientUpdateComponent } from 'app/entities/formulaire-patient/formulaire-patient-update.component';
import { FormulairePatientService } from 'app/entities/formulaire-patient/formulaire-patient.service';
import { FormulairePatient } from 'app/shared/model/formulaire-patient.model';

describe('Component Tests', () => {
    describe('FormulairePatient Management Update Component', () => {
        let comp: FormulairePatientUpdateComponent;
        let fixture: ComponentFixture<FormulairePatientUpdateComponent>;
        let service: FormulairePatientService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YkCrfTestModule],
                declarations: [FormulairePatientUpdateComponent]
            })
                .overrideTemplate(FormulairePatientUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(FormulairePatientUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FormulairePatientService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new FormulairePatient(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.formulairePatient = entity;
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
                    const entity = new FormulairePatient();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.formulairePatient = entity;
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
