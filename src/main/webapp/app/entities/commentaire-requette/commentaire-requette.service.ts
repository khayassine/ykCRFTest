import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICommentaireRequette } from 'app/shared/model/commentaire-requette.model';

type EntityResponseType = HttpResponse<ICommentaireRequette>;
type EntityArrayResponseType = HttpResponse<ICommentaireRequette[]>;

@Injectable({ providedIn: 'root' })
export class CommentaireRequetteService {
    public resourceUrl = SERVER_API_URL + 'api/commentaire-requettes';

    constructor(private http: HttpClient) {}

    create(commentaireRequette: ICommentaireRequette): Observable<EntityResponseType> {
        return this.http.post<ICommentaireRequette>(this.resourceUrl, commentaireRequette, { observe: 'response' });
    }

    update(commentaireRequette: ICommentaireRequette): Observable<EntityResponseType> {
        return this.http.put<ICommentaireRequette>(this.resourceUrl, commentaireRequette, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ICommentaireRequette>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ICommentaireRequette[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
