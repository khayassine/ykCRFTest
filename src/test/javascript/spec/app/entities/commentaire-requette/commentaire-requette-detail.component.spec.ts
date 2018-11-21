/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { YkCrfTestModule } from '../../../test.module';
import { CommentaireRequetteDetailComponent } from 'app/entities/commentaire-requette/commentaire-requette-detail.component';
import { CommentaireRequette } from 'app/shared/model/commentaire-requette.model';

describe('Component Tests', () => {
    describe('CommentaireRequette Management Detail Component', () => {
        let comp: CommentaireRequetteDetailComponent;
        let fixture: ComponentFixture<CommentaireRequetteDetailComponent>;
        const route = ({ data: of({ commentaireRequette: new CommentaireRequette(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YkCrfTestModule],
                declarations: [CommentaireRequetteDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(CommentaireRequetteDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CommentaireRequetteDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.commentaireRequette).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
