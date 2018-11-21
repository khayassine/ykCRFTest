import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IFormulaireTemplate } from 'app/shared/model/formulaire-template.model';

type EntityResponseType = HttpResponse<IFormulaireTemplate>;
type EntityArrayResponseType = HttpResponse<IFormulaireTemplate[]>;

@Injectable({ providedIn: 'root' })
export class FormulaireTemplateService {
    public resourceUrl = SERVER_API_URL + 'api/formulaire-templates';

    constructor(private http: HttpClient) {}

    create(formulaireTemplate: IFormulaireTemplate): Observable<EntityResponseType> {
        return this.http.post<IFormulaireTemplate>(this.resourceUrl, formulaireTemplate, { observe: 'response' });
    }

    update(formulaireTemplate: IFormulaireTemplate): Observable<EntityResponseType> {
        return this.http.put<IFormulaireTemplate>(this.resourceUrl, formulaireTemplate, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IFormulaireTemplate>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IFormulaireTemplate[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
