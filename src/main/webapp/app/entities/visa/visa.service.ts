import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { Visa } from './visa.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<Visa>;

@Injectable()
export class VisaService {

    private resourceUrl =  SERVER_API_URL + 'api/visas';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(visa: Visa): Observable<EntityResponseType> {
        const copy = this.convert(visa);
        return this.http.post<Visa>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(visa: Visa): Observable<EntityResponseType> {
        const copy = this.convert(visa);
        return this.http.put<Visa>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Visa>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Visa[]>> {
        const options = createRequestOption(req);
        return this.http.get<Visa[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Visa[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Visa = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Visa[]>): HttpResponse<Visa[]> {
        const jsonResponse: Visa[] = res.body;
        const body: Visa[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Visa.
     */
    private convertItemFromServer(visa: Visa): Visa {
        const copy: Visa = Object.assign({}, visa);
        copy.dateEmission = this.dateUtils
            .convertLocalDateFromServer(visa.dateEmission);
        copy.dateExpiration = this.dateUtils
            .convertLocalDateFromServer(visa.dateExpiration);
        return copy;
    }

    /**
     * Convert a Visa to a JSON which can be sent to the server.
     */
    private convert(visa: Visa): Visa {
        const copy: Visa = Object.assign({}, visa);
        copy.dateEmission = this.dateUtils
            .convertLocalDateToServer(visa.dateEmission);
        copy.dateExpiration = this.dateUtils
            .convertLocalDateToServer(visa.dateExpiration);
        return copy;
    }
}
