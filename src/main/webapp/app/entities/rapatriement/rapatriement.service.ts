import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { Rapatriement } from './rapatriement.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<Rapatriement>;

@Injectable()
export class RapatriementService {

    private resourceUrl =  SERVER_API_URL + 'api/rapatriements';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(rapatriement: Rapatriement): Observable<EntityResponseType> {
        const copy = this.convert(rapatriement);
        return this.http.post<Rapatriement>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(rapatriement: Rapatriement): Observable<EntityResponseType> {
        const copy = this.convert(rapatriement);
        return this.http.put<Rapatriement>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Rapatriement>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Rapatriement[]>> {
        const options = createRequestOption(req);
        return this.http.get<Rapatriement[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Rapatriement[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Rapatriement = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Rapatriement[]>): HttpResponse<Rapatriement[]> {
        const jsonResponse: Rapatriement[] = res.body;
        const body: Rapatriement[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Rapatriement.
     */
    private convertItemFromServer(rapatriement: Rapatriement): Rapatriement {
        const copy: Rapatriement = Object.assign({}, rapatriement);
        copy.dateNaissance = this.dateUtils
            .convertDateTimeFromServer(rapatriement.dateNaissance);
        copy.dateRapatriement = this.dateUtils
            .convertDateTimeFromServer(rapatriement.dateRapatriement);
        return copy;
    }

    /**
     * Convert a Rapatriement to a JSON which can be sent to the server.
     */
    private convert(rapatriement: Rapatriement): Rapatriement {
        const copy: Rapatriement = Object.assign({}, rapatriement);

        copy.dateNaissance = this.dateUtils.toDate(rapatriement.dateNaissance);

        copy.dateRapatriement = this.dateUtils.toDate(rapatriement.dateRapatriement);
        return copy;
    }
}
