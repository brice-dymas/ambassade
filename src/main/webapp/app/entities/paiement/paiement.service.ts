import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { Paiement } from './paiement.model';
import { createRequestOption } from '../../shared';
import {PaiementSearchModel} from './paiement-search.model';

export type EntityResponseType = HttpResponse<Paiement>;

@Injectable()
export class PaiementService {

    private resourceUrl =  SERVER_API_URL + 'api/paiements';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(paiement: Paiement): Observable<EntityResponseType> {
        const copy = this.convert(paiement);
        return this.http.post<Paiement>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(paiement: Paiement): Observable<EntityResponseType> {
        const copy = this.convert(paiement);
        return this.http.put<Paiement>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Paiement>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    search(paiement: PaiementSearchModel): Observable<HttpResponse<Paiement[]>> {
        const copy = this.convertSearch(paiement);
        const options = createRequestOption(copy);
        return this.http.get<Paiement[]>(this.resourceUrl, { params: options,  observe: 'response'})
            .map((res: HttpResponse<Paiement[]>) => this.convertArrayResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Paiement[]>> {
        const options = createRequestOption(req);
        return this.http.get<Paiement[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Paiement[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Paiement = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Paiement[]>): HttpResponse<Paiement[]> {
        const jsonResponse: Paiement[] = res.body;
        const body: Paiement[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Paiement.
     */
    private convertItemFromServer(paiement: Paiement): Paiement {
        const copy: Paiement = Object.assign({}, paiement);
        copy.datePaiement = this.dateUtils
            .convertLocalDateFromServer(paiement.datePaiement);
        copy.dateCreation = this.dateUtils
            .convertLocalDateFromServer(paiement.dateCreation);
        copy.dateModification = this.dateUtils
            .convertLocalDateFromServer(paiement.dateModification);
        return copy;
    }

    /**
     * Convert a Paiement to a JSON which can be sent to the server.
     */
    private convert(paiement: Paiement): Paiement {
        const copy: Paiement = Object.assign({}, paiement);
        copy.datePaiement = this.dateUtils
            .convertLocalDateToServer(paiement.datePaiement);
        copy.dateCreation = this.dateUtils
            .convertLocalDateToServer(paiement.dateCreation);
        copy.dateModification = this.dateUtils
            .convertLocalDateToServer(paiement.dateModification);
        return copy;
    }

    /**
     * Convert a Paiement to a JSON which can be sent to the server.
     */
    private convertSearch(paiement: PaiementSearchModel): PaiementSearchModel {
        const copy: PaiementSearchModel = Object.assign({}, paiement);
        copy.datePaiement = this.dateUtils
            .convertLocalDateToServer(paiement.datePaiement);
        copy.datePaiementFin = this.dateUtils
            .convertLocalDateToServer(paiement.datePaiementFin);
        return copy;
    }
}
