import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { Monnaie } from './monnaie.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<Monnaie>;

@Injectable()
export class MonnaieService {

    private resourceUrl =  SERVER_API_URL + 'api/monnaies';

    constructor(private http: HttpClient) { }

    create(monnaie: Monnaie): Observable<EntityResponseType> {
        const copy = this.convert(monnaie);
        return this.http.post<Monnaie>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(monnaie: Monnaie): Observable<EntityResponseType> {
        const copy = this.convert(monnaie);
        return this.http.put<Monnaie>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Monnaie>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Monnaie[]>> {
        const options = createRequestOption(req);
        return this.http.get<Monnaie[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Monnaie[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Monnaie = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Monnaie[]>): HttpResponse<Monnaie[]> {
        const jsonResponse: Monnaie[] = res.body;
        const body: Monnaie[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Monnaie.
     */
    private convertItemFromServer(monnaie: Monnaie): Monnaie {
        const copy: Monnaie = Object.assign({}, monnaie);
        return copy;
    }

    /**
     * Convert a Monnaie to a JSON which can be sent to the server.
     */
    private convert(monnaie: Monnaie): Monnaie {
        const copy: Monnaie = Object.assign({}, monnaie);
        return copy;
    }
}
