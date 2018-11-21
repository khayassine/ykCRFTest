/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { YkCrfTestModule } from '../../../test.module';
import { FichePatientUpdateComponent } from 'app/entities/fiche-patient/fiche-patient-update.component';
import { FichePatientService } from 'app/entities/fiche-patient/fiche-patient.service';
import { FichePatient } from 'app/shared/model/fiche-patient.model';

describe('Component Tests', () => {
    describe('FichePatient Management Update Component', () => {
        let comp: FichePatientUpdateComponent;
        let fixture: ComponentFixture<FichePatientUpdateComponent>;
        let service: FichePatientService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YkCrfTestModule],
                declarations: [FichePatientUpdateComponent]
            })
                .overrideTemplate(FichePatientUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(FichePatientUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FichePatientService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new FichePatient(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.fichePatient = entity;
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
                    const entity = new FichePatient();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.fichePatient = entity;
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
