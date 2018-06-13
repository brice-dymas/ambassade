import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AmbassadeSharedModule } from '../../shared';
import {
    UniteOrganisationelleService,
    UniteOrganisationellePopupService,
    UniteOrganisationelleComponent,
    UniteOrganisationelleDetailComponent,
    UniteOrganisationelleDialogComponent,
    UniteOrganisationellePopupComponent,
    UniteOrganisationelleDeletePopupComponent,
    UniteOrganisationelleDeleteDialogComponent,
    uniteOrganisationelleRoute,
    uniteOrganisationellePopupRoute,
    UniteOrganisationelleResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...uniteOrganisationelleRoute,
    ...uniteOrganisationellePopupRoute,
];

@NgModule({
    imports: [
        AmbassadeSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        UniteOrganisationelleComponent,
        UniteOrganisationelleDetailComponent,
        UniteOrganisationelleDialogComponent,
        UniteOrganisationelleDeleteDialogComponent,
        UniteOrganisationellePopupComponent,
        UniteOrganisationelleDeletePopupComponent,
    ],
    entryComponents: [
        UniteOrganisationelleComponent,
        UniteOrganisationelleDialogComponent,
        UniteOrganisationellePopupComponent,
        UniteOrganisationelleDeleteDialogComponent,
        UniteOrganisationelleDeletePopupComponent,
    ],
    providers: [
        UniteOrganisationelleService,
        UniteOrganisationellePopupService,
        UniteOrganisationelleResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AmbassadeUniteOrganisationelleModule {}
