import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AmbassadeSharedModule } from '../../shared';
import {
    LivreService,
    LivrePopupService,
    LivreComponent,
    LivreDetailComponent,
    LivreDialogComponent,
    LivrePopupComponent,
    LivreDeletePopupComponent,
    LivreDeleteDialogComponent,
    livreRoute,
    livrePopupRoute,
    LivreResolvePagingParams,
} from './';
import {LivreSearchComponent} from './livre-search.component';

const ENTITY_STATES = [
    ...livreRoute,
    ...livrePopupRoute,
];

@NgModule({
    imports: [
        AmbassadeSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        LivreComponent,
        LivreDetailComponent,
        LivreDialogComponent,
        LivreDeleteDialogComponent,
        LivrePopupComponent,
        LivreDeletePopupComponent,
        LivreSearchComponent,
    ],
    entryComponents: [
        LivreComponent,
        LivreDialogComponent,
        LivrePopupComponent,
        LivreDeleteDialogComponent,
        LivreDeletePopupComponent,
    ],
    providers: [
        LivreService,
        LivrePopupService,
        LivreResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AmbassadeLivreModule {}
