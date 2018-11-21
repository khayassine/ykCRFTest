import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IFormulairePatient } from 'app/shared/model/formulaire-patient.model';

type EntityResponseType = HttpResponse<IFormulairePatient>;
type EntityArrayResponseType = HttpResponse<IFormulairePatient[]>;

@Injectable({ providedIn: 'root' })
export class FormulairePatientService {
    public resourceUrl = SERVER_API_URL + 'api/formulaire-patients';

    constructor(private http: HttpClient) {}

    create(formulairePatient: IFormulairePatient): Observable<EntityResponseType> {
        return this.http.post<IFormulairePatient>(this.resourceUrl, formulairePatient, { observe: 'response' });
    }

    update(formulairePatient: IFormulairePatient): Observable<EntityResponseType> {
        return this.http.put<IFormulairePatient>(this.resourceUrl, formulairePatient, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IFormulairePatient>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IFormulairePatient[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
