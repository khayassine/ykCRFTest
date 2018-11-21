import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRegleValidation } from 'app/shared/model/regle-validation.model';

type EntityResponseType = HttpResponse<IRegleValidation>;
type EntityArrayResponseType = HttpResponse<IRegleValidation[]>;

@Injectable({ providedIn: 'root' })
export class RegleValidationService {
    public resourceUrl = SERVER_API_URL + 'api/regle-validations';

    constructor(private http: HttpClient) {}

    create(regleValidation: IRegleValidation): Observable<EntityResponseType> {
        return this.http.post<IRegleValidation>(this.resourceUrl, regleValidation, { observe: 'response' });
    }

    update(regleValidation: IRegleValidation): Observable<EntityResponseType> {
        return this.http.put<IRegleValidation>(this.resourceUrl, regleValidation, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IRegleValidation>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IRegleValidation[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
