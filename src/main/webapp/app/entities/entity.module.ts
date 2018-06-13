import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { AmbassadePasseportModule } from './passeport/passeport.module';
import { AmbassadeDonneesActeModule } from './donnees-acte/donnees-acte.module';
import { AmbassadeLivreModule } from './livre/livre.module';
import { AmbassadeCategorieModule } from './categorie/categorie.module';
import { AmbassadeRapatriementModule } from './rapatriement/rapatriement.module';
import { AmbassadeCaisseModule } from './caisse/caisse.module';
import { AmbassadeMonnaieModule } from './monnaie/monnaie.module';
import { AmbassadeMontantModule } from './montant/montant.module';
import { AmbassadeProduitModule } from './produit/produit.module';
import { AmbassadeVisaModule } from './visa/visa.module';
import {AmbassadePrintModule} from './print/print.module';
// import { AmbassadePrint } ;
import { AmbassadeTypeServiceModule } from './type-service/type-service.module';
import { AmbassadePaiementModule } from './paiement/paiement.module';
import { AmbassadeUniteOrganisationelleModule } from './unite-organisationelle/unite-organisationelle.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        AmbassadePasseportModule,
        AmbassadeDonneesActeModule,
        AmbassadeLivreModule,
        AmbassadeCategorieModule,
        AmbassadeRapatriementModule,
        AmbassadeCaisseModule,
        AmbassadeMonnaieModule,
        AmbassadeMontantModule,
        AmbassadeProduitModule,
        AmbassadeVisaModule,
        AmbassadePrintModule,
        AmbassadeTypeServiceModule,
        AmbassadePaiementModule,
        AmbassadeUniteOrganisationelleModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AmbassadeEntityModule {}
