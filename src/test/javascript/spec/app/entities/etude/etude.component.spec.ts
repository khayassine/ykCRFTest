/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { YkCrfTestModule } from '../../../test.module';
import { EtudeComponent } from 'app/entities/etude/etude.component';
import { EtudeService } from 'app/entities/etude/etude.service';
import { Etude } from 'app/shared/model/etude.model';

describe('Component Tests', () => {
    describe('Etude Management Component', () => {
        let comp: EtudeComponent;
        let fixture: ComponentFixture<EtudeComponent>;
        let service: EtudeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YkCrfTestModule],
                declarations: [EtudeComponent],
                providers: []
            })
                .overrideTemplate(EtudeComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EtudeComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EtudeService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Etude(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.etudes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
