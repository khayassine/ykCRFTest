/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { YkCrfTestModule } from '../../../test.module';
import { ComposantValeurComponent } from 'app/entities/composant-valeur/composant-valeur.component';
import { ComposantValeurService } from 'app/entities/composant-valeur/composant-valeur.service';
import { ComposantValeur } from 'app/shared/model/composant-valeur.model';

describe('Component Tests', () => {
    describe('ComposantValeur Management Component', () => {
        let comp: ComposantValeurComponent;
        let fixture: ComponentFixture<ComposantValeurComponent>;
        let service: ComposantValeurService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YkCrfTestModule],
                declarations: [ComposantValeurComponent],
                providers: []
            })
                .overrideTemplate(ComposantValeurComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ComposantValeurComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ComposantValeurService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new ComposantValeur(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.composantValeurs[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
