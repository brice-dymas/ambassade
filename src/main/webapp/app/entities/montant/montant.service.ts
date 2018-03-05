import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { Montant } from './montant.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<Montant>;

@Injectable()
export class MontantService {

    private resourceUrl =  SERVER_API_URL + 'api/montants';

    constructor(private http: HttpClient) { }

    create(montant: Montant): Observable<EntityResponseType> {
        const copy = this.convert(montant);
        return this.http.post<Montant>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(montant: Montant): Observable<EntityResponseType> {
        const copy = this.convert(montant);
        return this.http.put<Montant>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Montant>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    search(montant: Montant): Observable<HttpResponse<Montant[]>> {
        const options = createRequestOption(montant);
        return this.http.get<Montant[]>(this.resourceUrl, { params: options,  observe: 'response'})
            .map((res: HttpResponse<Montant[]>) => this.convertArrayResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Montant[]>> {
        const options = createRequestOption(req);
        return this.http.get<Montant[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Montant[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Montant = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Montant[]>): HttpResponse<Montant[]> {
        const jsonResponse: Montant[] = res.body;
        const body: Montant[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Montant.
     */
    private convertItemFromServer(montant: Montant): Montant {
        const copy: Montant = Object.assign({}, montant);
        return copy;
    }

    /**
     * Convert a Montant to a JSON which can be sent to the server.
     */
    private convert(montant: Montant): Montant {
        const copy: Montant = Object.assign({}, montant);
        return copy;
    }
}
