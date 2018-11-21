import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRequette } from 'app/shared/model/requette.model';

type EntityResponseType = HttpResponse<IRequette>;
type EntityArrayResponseType = HttpResponse<IRequette[]>;

@Injectable({ providedIn: 'root' })
export class RequetteService {
    public resourceUrl = SERVER_API_URL + 'api/requettes';

    constructor(private http: HttpClient) {}

    create(requette: IRequette): Observable<EntityResponseType> {
        return this.http.post<IRequette>(this.resourceUrl, requette, { observe: 'response' });
    }

    update(requette: IRequette): Observable<EntityResponseType> {
        return this.http.put<IRequette>(this.resourceUrl, requette, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IRequette>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IRequette[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
