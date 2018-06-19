import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AmbassadeSharedModule } from '../../shared';
import {
    TypeServiceService,
    TypeServicePopupService,
    TypeServiceComponent,
    TypeServiceDetailComponent,
    TypeServiceDialogComponent,
    TypeServicePopupComponent,
    TypeServiceDeletePopupComponent,
    TypeServiceDeleteDialogComponent,
    typeServiceRoute,
    typeServicePopupRoute,
    TypeServiceResolvePagingParams,
} from './';
import {TypeServicePasseportComponent} from './type-service-passeport.component';
import {TypeServiceVisaComponent} from './type-service-visa.component';

const ENTITY_STATES = [
    ...typeServiceRoute,
    ...typeServicePopupRoute,
];

@NgModule({
    imports: [
        AmbassadeSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        TypeServiceComponent,
        TypeServicePasseportComponent,
        TypeServiceVisaComponent,
        TypeServiceDetailComponent,
        TypeServiceDialogComponent,
        TypeServiceDeleteDialogComponent,
        TypeServicePopupComponent,
        TypeServiceDeletePopupComponent,
    ],
    entryComponents: [
        TypeServiceComponent,
        TypeServiceDialogComponent,
        TypeServicePopupComponent,
        TypeServiceDeleteDialogComponent,
        TypeServiceDeletePopupComponent,
    ],
    providers: [
        TypeServiceService,
        TypeServicePopupService,
        TypeServiceResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AmbassadeTypeServiceModule {}
