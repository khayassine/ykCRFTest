import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IFichePatient } from 'app/shared/model/fiche-patient.model';

type EntityResponseType = HttpResponse<IFichePatient>;
type EntityArrayResponseType = HttpResponse<IFichePatient[]>;

@Injectable({ providedIn: 'root' })
export class FichePatientService {
    public resourceUrl = SERVER_API_URL + 'api/fiche-patients';

    constructor(private http: HttpClient) {}

    create(fichePatient: IFichePatient): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(fichePatient);
        return this.http
            .post<IFichePatient>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(fichePatient: IFichePatient): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(fichePatient);
        return this.http
            .put<IFichePatient>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IFichePatient>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IFichePatient[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(fichePatient: IFichePatient): IFichePatient {
        const copy: IFichePatient = Object.assign({}, fichePatient, {
            oneDate: fichePatient.oneDate != null && fichePatient.oneDate.isValid() ? fichePatient.oneDate.toJSON() : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.oneDate = res.body.oneDate != null ? moment(res.body.oneDate) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((fichePatient: IFichePatient) => {
                fichePatient.oneDate = fichePatient.oneDate != null ? moment(fichePatient.oneDate) : null;
            });
        }
        return res;
    }
}
