import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PrintComponent } from './print.component';
import {PrintDetailPasseportComponent} from './print-detail-passeport.component';
import {PrintPasseportComponent} from './print-passeport.component';
import {PrintPaiementComponent} from './print-paiement.component';
import {PrintRecuPaiementComponent} from './print-recu-paiement.component';

export const printRoute: Routes = [
    {
        path: 'print',
        component: PrintComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ambassadeApp.categorie.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'print-recu-paiement',
        component: PrintRecuPaiementComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ambassadeApp.categorie.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'print/detail-passeport',
        component: PrintDetailPasseportComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ambassadeApp.passeport.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'print/passeport',
        component: PrintPasseportComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ambassadeApp.passeport.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'print/paiement',
        component: PrintPaiementComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ambassadeApp.paiement.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];
