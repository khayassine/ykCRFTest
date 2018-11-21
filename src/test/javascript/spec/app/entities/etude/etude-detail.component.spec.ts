/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { YkCrfTestModule } from '../../../test.module';
import { EtudeDetailComponent } from 'app/entities/etude/etude-detail.component';
import { Etude } from 'app/shared/model/etude.model';

describe('Component Tests', () => {
    describe('Etude Management Detail Component', () => {
        let comp: EtudeDetailComponent;
        let fixture: ComponentFixture<EtudeDetailComponent>;
        const route = ({ data: of({ etude: new Etude(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YkCrfTestModule],
                declarations: [EtudeDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(EtudeDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EtudeDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.etude).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
