import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PrintComponent } from './print.component';
import {PrintDetailPasseportComponent} from './print-detail-passeport.component';

export const printRoute: Routes = [
    {
        path: 'print',
        component: PrintComponent,
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
    }
];
