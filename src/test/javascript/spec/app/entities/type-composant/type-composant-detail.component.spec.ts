/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { YkCrfTestModule } from '../../../test.module';
import { TypeComposantDetailComponent } from 'app/entities/type-composant/type-composant-detail.component';
import { TypeComposant } from 'app/shared/model/type-composant.model';

describe('Component Tests', () => {
    describe('TypeComposant Management Detail Component', () => {
        let comp: TypeComposantDetailComponent;
        let fixture: ComponentFixture<TypeComposantDetailComponent>;
        const route = ({ data: of({ typeComposant: new TypeComposant(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YkCrfTestModule],
                declarations: [TypeComposantDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(TypeComposantDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TypeComposantDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.typeComposant).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
