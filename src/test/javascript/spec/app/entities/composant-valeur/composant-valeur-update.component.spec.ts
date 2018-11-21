/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { YkCrfTestModule } from '../../../test.module';
import { ComposantValeurUpdateComponent } from 'app/entities/composant-valeur/composant-valeur-update.component';
import { ComposantValeurService } from 'app/entities/composant-valeur/composant-valeur.service';
import { ComposantValeur } from 'app/shared/model/composant-valeur.model';

describe('Component Tests', () => {
    describe('ComposantValeur Management Update Component', () => {
        let comp: ComposantValeurUpdateComponent;
        let fixture: ComponentFixture<ComposantValeurUpdateComponent>;
        let service: ComposantValeurService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YkCrfTestModule],
                declarations: [ComposantValeurUpdateComponent]
            })
                .overrideTemplate(ComposantValeurUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ComposantValeurUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ComposantValeurService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ComposantValeur(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.composantValeur = entity;
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
                    const entity = new ComposantValeur();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.composantValeur = entity;
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
