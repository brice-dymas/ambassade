import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AmbassadeSharedModule } from '../../shared';
import {
    VisaService,
    VisaPopupService,
    VisaComponent,
    VisaDetailComponent,
    VisaDialogComponent,
    VisaPopupComponent,
    VisaDeletePopupComponent,
    VisaDeleteDialogComponent,
    visaRoute,
    visaPopupRoute,
    VisaResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...visaRoute,
    ...visaPopupRoute,
];

@NgModule({
    imports: [
        AmbassadeSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        VisaComponent,
        VisaDetailComponent,
        VisaDialogComponent,
        VisaDeleteDialogComponent,
        VisaPopupComponent,
        VisaDeletePopupComponent,
    ],
    entryComponents: [
        VisaComponent,
        VisaDialogComponent,
        VisaPopupComponent,
        VisaDeleteDialogComponent,
        VisaDeletePopupComponent,
    ],
    providers: [
        VisaService,
        VisaPopupService,
        VisaResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AmbassadeVisaModule {}
