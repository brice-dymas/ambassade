import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { Livre } from './livre.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<Livre>;

@Injectable()
export class LivreService {

    private resourceUrl =  SERVER_API_URL + 'api/livres';

    constructor(private http: HttpClient) { }

    create(livre: Livre): Observable<EntityResponseType> {
        const copy = this.convert(livre);
        return this.http.post<Livre>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(livre: Livre): Observable<EntityResponseType> {
        const copy = this.convert(livre);
        return this.http.put<Livre>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Livre>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Livre[]>> {
        const options = createRequestOption(req);
        return this.http.get<Livre[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Livre[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Livre = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Livre[]>): HttpResponse<Livre[]> {
        const jsonResponse: Livre[] = res.body;
        const body: Livre[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Livre.
     */
    private convertItemFromServer(livre: Livre): Livre {
        const copy: Livre = Object.assign({}, livre);
        return copy;
    }

    /**
     * Convert a Livre to a JSON which can be sent to the server.
     */
    private convert(livre: Livre): Livre {
        const copy: Livre = Object.assign({}, livre);
        return copy;
    }
}
