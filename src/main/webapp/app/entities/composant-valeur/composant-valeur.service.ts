import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IComposantValeur } from 'app/shared/model/composant-valeur.model';

type EntityResponseType = HttpResponse<IComposantValeur>;
type EntityArrayResponseType = HttpResponse<IComposantValeur[]>;

@Injectable({ providedIn: 'root' })
export class ComposantValeurService {
    public resourceUrl = SERVER_API_URL + 'api/composant-valeurs';

    constructor(private http: HttpClient) {}

    create(composantValeur: IComposantValeur): Observable<EntityResponseType> {
        return this.http.post<IComposantValeur>(this.resourceUrl, composantValeur, { observe: 'response' });
    }

    update(composantValeur: IComposantValeur): Observable<EntityResponseType> {
        return this.http.put<IComposantValeur>(this.resourceUrl, composantValeur, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IComposantValeur>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IComposantValeur[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
