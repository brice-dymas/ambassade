import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { PasseportComponent } from './passeport.component';
import { PasseportDetailComponent } from './passeport-detail.component';
import { PasseportPopupComponent } from './passeport-dialog.component';
import { PasseportDeletePopupComponent } from './passeport-delete-dialog.component';

@Injectable()
export class PasseportResolvePagingParams implements Resolve<any> {

    constructor(private paginationUtil: JhiPaginationUtil) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const page = route.queryParams['page'] ? route.queryParams['page'] : '1';
        const sort = route.queryParams['sort'] ? route.queryParams['sort'] : 'id,asc';
        return {
            page: this.paginationUtil.parsePage(page),
            predicate: this.paginationUtil.parsePredicate(sort),
            ascending: this.paginationUtil.parseAscending(sort)
      };
    }
}

export const passeportRoute: Routes = [
    {
        path: 'passeport',
        component: PasseportComponent,
        resolve: {
            'pagingParams': PasseportResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ambassadeApp.passeport.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'passeport/:id',
        component: PasseportDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ambassadeApp.passeport.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const passeportPopupRoute: Routes = [
    {
        path: 'passeport-new',
        component: PasseportPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ambassadeApp.passeport.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'passeport/:id/edit',
        component: PasseportPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ambassadeApp.passeport.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'passeport/:id/delete',
        component: PasseportDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ambassadeApp.passeport.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];