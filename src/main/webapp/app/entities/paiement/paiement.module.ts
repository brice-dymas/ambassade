import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AmbassadeSharedModule } from '../../shared';
import { AmbassadeAdminModule } from '../../admin/admin.module';
import {
    PaiementService,
    PaiementPopupService,
    PaiementComponent,
    PaiementDetailComponent,
    PaiementDialogComponent,
    PaiementPopupComponent,
    PaiementDeletePopupComponent,
    PaiementDeleteDialogComponent,
    paiementRoute,
    paiementPopupRoute,
    PaiementResolvePagingParams,
} from './';
import {PaiementSearchComponent} from './paiement-search.component';

const ENTITY_STATES = [
    ...paiementRoute,
    ...paiementPopupRoute,
];

@NgModule({
    imports: [
        AmbassadeSharedModule,
        AmbassadeAdminModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        PaiementComponent,
        PaiementDetailComponent,
        PaiementDialogComponent,
        PaiementDeleteDialogComponent,
        PaiementPopupComponent,
        PaiementDeletePopupComponent,
        PaiementSearchComponent,
    ],
    entryComponents: [
        PaiementComponent,
        PaiementDialogComponent,
        PaiementPopupComponent,
        PaiementDeleteDialogComponent,
        PaiementDeletePopupComponent,
    ],
    providers: [
        PaiementService,
        PaiementPopupService,
        PaiementResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AmbassadePaiementModule {}
