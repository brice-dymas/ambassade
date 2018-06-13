import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { TypeEntree } from './type-entree.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<TypeEntree>;

@Injectable()
export class TypeEntreeService {

    private resourceUrl =  SERVER_API_URL + 'api/type-entrees';

    constructor(private http: HttpClient) { }

    create(typeEntree: TypeEntree): Observable<EntityResponseType> {
        const copy = this.convert(typeEntree);
        return this.http.post<TypeEntree>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(typeEntree: TypeEntree): Observable<EntityResponseType> {
        const copy = this.convert(typeEntree);
        return this.http.put<TypeEntree>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<TypeEntree>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<TypeEntree[]>> {
        const options = createRequestOption(req);
        return this.http.get<TypeEntree[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<TypeEntree[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: TypeEntree = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<TypeEntree[]>): HttpResponse<TypeEntree[]> {
        const jsonResponse: TypeEntree[] = res.body;
        const body: TypeEntree[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to TypeEntree.
     */
    private convertItemFromServer(typeEntree: TypeEntree): TypeEntree {
        const copy: TypeEntree = Object.assign({}, typeEntree);
        return copy;
    }

    /**
     * Convert a TypeEntree to a JSON which can be sent to the server.
     */
    private convert(typeEntree: TypeEntree): TypeEntree {
        const copy: TypeEntree = Object.assign({}, typeEntree);
        return copy;
    }
}
