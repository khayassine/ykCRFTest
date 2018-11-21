import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISousRegion } from 'app/shared/model/sous-region.model';

type EntityResponseType = HttpResponse<ISousRegion>;
type EntityArrayResponseType = HttpResponse<ISousRegion[]>;

@Injectable({ providedIn: 'root' })
export class SousRegionService {
    public resourceUrl = SERVER_API_URL + 'api/sous-regions';

    constructor(private http: HttpClient) {}

    create(sousRegion: ISousRegion): Observable<EntityResponseType> {
        return this.http.post<ISousRegion>(this.resourceUrl, sousRegion, { observe: 'response' });
    }

    update(sousRegion: ISousRegion): Observable<EntityResponseType> {
        return this.http.put<ISousRegion>(this.resourceUrl, sousRegion, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ISousRegion>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ISousRegion[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
