/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { YkCrfTestModule } from '../../../test.module';
import { ComposantValeurDetailComponent } from 'app/entities/composant-valeur/composant-valeur-detail.component';
import { ComposantValeur } from 'app/shared/model/composant-valeur.model';

describe('Component Tests', () => {
    describe('ComposantValeur Management Detail Component', () => {
        let comp: ComposantValeurDetailComponent;
        let fixture: ComponentFixture<ComposantValeurDetailComponent>;
        const route = ({ data: of({ composantValeur: new ComposantValeur(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YkCrfTestModule],
                declarations: [ComposantValeurDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ComposantValeurDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ComposantValeurDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.composantValeur).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
