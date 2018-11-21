import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IComposantTemplate } from 'app/shared/model/composant-template.model';

type EntityResponseType = HttpResponse<IComposantTemplate>;
type EntityArrayResponseType = HttpResponse<IComposantTemplate[]>;

@Injectable({ providedIn: 'root' })
export class ComposantTemplateService {
    public resourceUrl = SERVER_API_URL + 'api/composant-templates';

    constructor(private http: HttpClient) {}

    create(composantTemplate: IComposantTemplate): Observable<EntityResponseType> {
        return this.http.post<IComposantTemplate>(this.resourceUrl, composantTemplate, { observe: 'response' });
    }

    update(composantTemplate: IComposantTemplate): Observable<EntityResponseType> {
        return this.http.put<IComposantTemplate>(this.resourceUrl, composantTemplate, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IComposantTemplate>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IComposantTemplate[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
