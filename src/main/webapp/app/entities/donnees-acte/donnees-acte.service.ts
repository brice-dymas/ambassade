import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { DonneesActe } from './donnees-acte.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<DonneesActe>;

@Injectable()
export class DonneesActeService {

    private resourceUrl =  SERVER_API_URL + 'api/donnees-actes';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(donneesActe: DonneesActe): Observable<EntityResponseType> {
        const copy = this.convert(donneesActe);
        return this.http.post<DonneesActe>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(donneesActe: DonneesActe): Observable<EntityResponseType> {
        const copy = this.convert(donneesActe);
        return this.http.put<DonneesActe>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<DonneesActe>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<DonneesActe[]>> {
        const options = createRequestOption(req);
        return this.http.get<DonneesActe[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<DonneesActe[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: DonneesActe = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<DonneesActe[]>): HttpResponse<DonneesActe[]> {
        const jsonResponse: DonneesActe[] = res.body;
        const body: DonneesActe[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to DonneesActe.
     */
    private convertItemFromServer(donneesActe: DonneesActe): DonneesActe {
        const copy: DonneesActe = Object.assign({}, donneesActe);
        copy.dateDuJourChiffre = this.dateUtils
            .convertLocalDateFromServer(donneesActe.dateDuJourChiffre);
        copy.dateNaissanceChiffre = this.dateUtils
            .convertLocalDateFromServer(donneesActe.dateNaissanceChiffre);
        return copy;
    }

    /**
     * Convert a DonneesActe to a JSON which can be sent to the server.
     */
    private convert(donneesActe: DonneesActe): DonneesActe {
        const copy: DonneesActe = Object.assign({}, donneesActe);
        copy.dateDuJourChiffre = this.dateUtils
            .convertLocalDateToServer(donneesActe.dateDuJourChiffre);
        copy.dateNaissanceChiffre = this.dateUtils
            .convertLocalDateToServer(donneesActe.dateNaissanceChiffre);
        return copy;
    }
}
