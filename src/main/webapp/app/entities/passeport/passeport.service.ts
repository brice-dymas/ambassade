import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { Passeport } from './passeport.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<Passeport>;

@Injectable()
export class PasseportService {

    private resourceUrl =  SERVER_API_URL + 'api/passeports';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(passeport: Passeport): Observable<EntityResponseType> {
        const copy = this.convert(passeport);
        return this.http.post<Passeport>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(passeport: Passeport): Observable<EntityResponseType> {
        const copy = this.convert(passeport);
        return this.http.put<Passeport>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Passeport>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Passeport[]>> {
        const options = createRequestOption(req);
        return this.http.get<Passeport[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Passeport[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Passeport = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Passeport[]>): HttpResponse<Passeport[]> {
        const jsonResponse: Passeport[] = res.body;
        const body: Passeport[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Passeport.
     */
    private convertItemFromServer(passeport: Passeport): Passeport {
        const copy: Passeport = Object.assign({}, passeport);
        copy.soumisLe = this.dateUtils
            .convertDateTimeFromServer(passeport.soumisLe);
        copy.delivreLe = this.dateUtils
            .convertDateTimeFromServer(passeport.delivreLe);
        copy.dateEmission = this.dateUtils
            .convertDateTimeFromServer(passeport.dateEmission);
        copy.dateExpiration = this.dateUtils
            .convertDateTimeFromServer(passeport.dateExpiration);
        return copy;
    }

    /**
     * Convert a Passeport to a JSON which can be sent to the server.
     */
    private convert(passeport: Passeport): Passeport {
        const copy: Passeport = Object.assign({}, passeport);

        copy.soumisLe = this.dateUtils.toDate(passeport.soumisLe);

        copy.delivreLe = this.dateUtils.toDate(passeport.delivreLe);

        copy.dateEmission = this.dateUtils.toDate(passeport.dateEmission);

        copy.dateExpiration = this.dateUtils.toDate(passeport.dateExpiration);
        return copy;
    }
}
