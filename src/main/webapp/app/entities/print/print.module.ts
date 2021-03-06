import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AmbassadeSharedModule } from '../../shared';
import { ExcelService} from '../../excel.services';
import {
    PrintComponent,
    printRoute,
} from './';
import {PrintDetailPasseportComponent} from './print-detail-passeport.component';
import {PrintPasseportComponent} from './print-passeport.component';
import {PrintPaiementComponent} from './print-paiement.component';
import {PrintRecuPaiementComponent} from './print-recu-paiement.component';
// import {printRoute} from "./print.route";

const ENTITY_STATES = [
    ...printRoute,
];

@NgModule({
    imports: [
        AmbassadeSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        PrintComponent,
        PrintPasseportComponent,
        PrintPaiementComponent,
        PrintRecuPaiementComponent,
        PrintDetailPasseportComponent
    ],
    entryComponents: [
        PrintComponent,
    ],
    providers: [
        ExcelService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AmbassadePrintModule {}
