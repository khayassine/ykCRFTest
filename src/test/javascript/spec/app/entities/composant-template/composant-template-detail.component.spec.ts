/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { YkCrfTestModule } from '../../../test.module';
import { ComposantTemplateDetailComponent } from 'app/entities/composant-template/composant-template-detail.component';
import { ComposantTemplate } from 'app/shared/model/composant-template.model';

describe('Component Tests', () => {
    describe('ComposantTemplate Management Detail Component', () => {
        let comp: ComposantTemplateDetailComponent;
        let fixture: ComponentFixture<ComposantTemplateDetailComponent>;
        const route = ({ data: of({ composantTemplate: new ComposantTemplate(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YkCrfTestModule],
                declarations: [ComposantTemplateDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ComposantTemplateDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ComposantTemplateDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.composantTemplate).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
