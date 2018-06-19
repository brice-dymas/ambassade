import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { TypeService } from './type-service.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<TypeService>;

@Injectable()
export class TypeServiceService {

    private resourceUrl =  SERVER_API_URL + 'api/type-services';
    private resourcePasseportUrl =  SERVER_API_URL + 'api/type-services/passeport';
    private resourceVisaUrl =  SERVER_API_URL + 'api/type-services/visa';

    constructor(private http: HttpClient) { }

    create(typeService: TypeService): Observable<EntityResponseType> {
        const copy = this.convert(typeService);
        return this.http.post<TypeService>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(typeService: TypeService): Observable<EntityResponseType> {
        const copy = this.convert(typeService);
        return this.http.put<TypeService>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<TypeService>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<TypeService[]>> {
        const options = createRequestOption(req);
        return this.http.get<TypeService[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<TypeService[]>) => this.convertArrayResponse(res));
    }

    queryForPasseport(req?: any): Observable<HttpResponse<TypeService[]>> {
        const options = createRequestOption(req);
        return this.http.get<TypeService[]>(this.resourcePasseportUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<TypeService[]>) => this.convertArrayResponse(res));
    }

    queryForVisa(req?: any): Observable<HttpResponse<TypeService[]>> {
        const options = createRequestOption(req);
        return this.http.get<TypeService[]>(this.resourceVisaUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<TypeService[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: TypeService = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<TypeService[]>): HttpResponse<TypeService[]> {
        const jsonResponse: TypeService[] = res.body;
        const body: TypeService[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to TypeService.
     */
    private convertItemFromServer(typeService: TypeService): TypeService {
        const copy: TypeService = Object.assign({}, typeService);
        return copy;
    }

    /**
     * Convert a TypeService to a JSON which can be sent to the server.
     */
    private convert(typeService: TypeService): TypeService {
        const copy: TypeService = Object.assign({}, typeService);
        return copy;
    }
}
