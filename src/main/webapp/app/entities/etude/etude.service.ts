import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEtude } from 'app/shared/model/etude.model';

type EntityResponseType = HttpResponse<IEtude>;
type EntityArrayResponseType = HttpResponse<IEtude[]>;

@Injectable({ providedIn: 'root' })
export class EtudeService {
    public resourceUrl = SERVER_API_URL + 'api/etudes';

    constructor(private http: HttpClient) {}

    create(etude: IEtude): Observable<EntityResponseType> {
        return this.http.post<IEtude>(this.resourceUrl, etude, { observe: 'response' });
    }

    update(etude: IEtude): Observable<EntityResponseType> {
        return this.http.put<IEtude>(this.resourceUrl, etude, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IEtude>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IEtude[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
