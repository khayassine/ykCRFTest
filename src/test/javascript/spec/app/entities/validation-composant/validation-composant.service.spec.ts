/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { ValidationComposantService } from 'app/entities/validation-composant/validation-composant.service';
import { IValidationComposant, ValidationComposant, NiveauValidation } from 'app/shared/model/validation-composant.model';

describe('Service Tests', () => {
    describe('ValidationComposant Service', () => {
        let injector: TestBed;
        let service: ValidationComposantService;
        let httpMock: HttpTestingController;
        let elemDefault: IValidationComposant;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(ValidationComposantService);
            httpMock = injector.get(HttpTestingController);

            elemDefault = new ValidationComposant(
                0,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                NiveauValidation.WARNING
            );
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign({}, elemDefault);
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a ValidationComposant', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .create(new ValidationComposant(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a ValidationComposant', async () => {
                const returnedFromService = Object.assign(
                    {
                        code: 'BBBBBB',
                        titre: 'BBBBBB',
                        regexValidation: 'BBBBBB',
                        signeComparaison: 'BBBBBB',
                        valeurComparaison: 'BBBBBB',
                        message: 'BBBBBB',
                        niveauValidation: 'BBBBBB'
                    },
                    elemDefault
                );

                const expected = Object.assign({}, returnedFromService);
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of ValidationComposant', async () => {
                const returnedFromService = Object.assign(
                    {
                        code: 'BBBBBB',
                        titre: 'BBBBBB',
                        regexValidation: 'BBBBBB',
                        signeComparaison: 'BBBBBB',
                        valeurComparaison: 'BBBBBB',
                        message: 'BBBBBB',
                        niveauValidation: 'BBBBBB'
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .query(expected)
                    .pipe(
                        take(1),
                        map(resp => resp.body)
                    )
                    .subscribe(body => expect(body).toContainEqual(expected));
                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify([returnedFromService]));
                httpMock.verify();
            });

            it('should delete a ValidationComposant', async () => {
                const rxPromise = service.delete(123).subscribe(resp => expect(resp.ok));

                const req = httpMock.expectOne({ method: 'DELETE' });
                req.flush({ status: 200 });
            });
        });

        afterEach(() => {
            httpMock.verify();
        });
    });
});
