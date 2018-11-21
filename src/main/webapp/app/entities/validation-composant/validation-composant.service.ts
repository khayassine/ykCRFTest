import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IValidationComposant } from 'app/shared/model/validation-composant.model';

type EntityResponseType = HttpResponse<IValidationComposant>;
type EntityArrayResponseType = HttpResponse<IValidationComposant[]>;

@Injectable({ providedIn: 'root' })
export class ValidationComposantService {
    public resourceUrl = SERVER_API_URL + 'api/validation-composants';

    constructor(private http: HttpClient) {}

    create(validationComposant: IValidationComposant): Observable<EntityResponseType> {
        return this.http.post<IValidationComposant>(this.resourceUrl, validationComposant, { observe: 'response' });
    }

    update(validationComposant: IValidationComposant): Observable<EntityResponseType> {
        return this.http.put<IValidationComposant>(this.resourceUrl, validationComposant, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IValidationComposant>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IValidationComposant[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
