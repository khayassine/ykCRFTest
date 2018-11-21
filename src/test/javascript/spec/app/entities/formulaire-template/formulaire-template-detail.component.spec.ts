/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { YkCrfTestModule } from '../../../test.module';
import { FormulaireTemplateDetailComponent } from 'app/entities/formulaire-template/formulaire-template-detail.component';
import { FormulaireTemplate } from 'app/shared/model/formulaire-template.model';

describe('Component Tests', () => {
    describe('FormulaireTemplate Management Detail Component', () => {
        let comp: FormulaireTemplateDetailComponent;
        let fixture: ComponentFixture<FormulaireTemplateDetailComponent>;
        const route = ({ data: of({ formulaireTemplate: new FormulaireTemplate(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YkCrfTestModule],
                declarations: [FormulaireTemplateDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(FormulaireTemplateDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(FormulaireTemplateDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.formulaireTemplate).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
