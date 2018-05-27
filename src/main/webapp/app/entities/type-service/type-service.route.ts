import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { TypeServiceComponent } from './type-service.component';
import { TypeServiceDetailComponent } from './type-service-detail.component';
import { TypeServicePopupComponent } from './type-service-dialog.component';
import { TypeServiceDeletePopupComponent } from './type-service-delete-dialog.component';

@Injectable()
export class TypeServiceResolvePagingParams implements Resolve<any> {

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

export const typeServiceRoute: Routes = [
    {
        path: 'type-service',
        component: TypeServiceComponent,
        resolve: {
            'pagingParams': TypeServiceResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ambassadeApp.typeService.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'type-service/:id',
        component: TypeServiceDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ambassadeApp.typeService.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const typeServicePopupRoute: Routes = [
    {
        path: 'type-service-new',
        component: TypeServicePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ambassadeApp.typeService.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'type-service/:id/edit',
        component: TypeServicePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ambassadeApp.typeService.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'type-service/:id/delete',
        component: TypeServiceDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ambassadeApp.typeService.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
