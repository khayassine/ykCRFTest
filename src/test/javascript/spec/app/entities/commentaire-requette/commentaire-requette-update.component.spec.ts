/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { YkCrfTestModule } from '../../../test.module';
import { CommentaireRequetteUpdateComponent } from 'app/entities/commentaire-requette/commentaire-requette-update.component';
import { CommentaireRequetteService } from 'app/entities/commentaire-requette/commentaire-requette.service';
import { CommentaireRequette } from 'app/shared/model/commentaire-requette.model';

describe('Component Tests', () => {
    describe('CommentaireRequette Management Update Component', () => {
        let comp: CommentaireRequetteUpdateComponent;
        let fixture: ComponentFixture<CommentaireRequetteUpdateComponent>;
        let service: CommentaireRequetteService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YkCrfTestModule],
                declarations: [CommentaireRequetteUpdateComponent]
            })
                .overrideTemplate(CommentaireRequetteUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CommentaireRequetteUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CommentaireRequetteService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new CommentaireRequette(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.commentaireRequette = entity;
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
                    const entity = new CommentaireRequette();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.commentaireRequette = entity;
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
