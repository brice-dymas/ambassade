import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AmbassadeSharedModule } from '../../shared';
import {
    DonneesActeService,
    DonneesActePopupService,
    DonneesActeComponent,
    DonneesActeDetailComponent,
    DonneesActeDialogComponent,
    DonneesActePopupComponent,
    DonneesActeDeletePopupComponent,
    DonneesActeDeleteDialogComponent,
    donneesActeRoute,
    donneesActePopupRoute,
    DonneesActeResolvePagingParams,
} from './';

import {DonneesActeSearchComponent} from './donnees-acte-search.component';

const ENTITY_STATES = [
    ...donneesActeRoute,
    ...donneesActePopupRoute,
];

@NgModule({
    imports: [
        AmbassadeSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        DonneesActeComponent,
        DonneesActeDetailComponent,
        DonneesActeDialogComponent,
        DonneesActeDeleteDialogComponent,
        DonneesActePopupComponent,
        DonneesActeDeletePopupComponent,
        DonneesActeSearchComponent,
    ],
    entryComponents: [
        DonneesActeComponent,
        DonneesActeDialogComponent,
        DonneesActePopupComponent,
        DonneesActeDeleteDialogComponent,
        DonneesActeDeletePopupComponent,
    ],
    providers: [
        DonneesActeService,
        DonneesActePopupService,
        DonneesActeResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AmbassadeDonneesActeModule {}
