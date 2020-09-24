import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import './vendor';
import { MachBeesSharedModule } from 'app/shared/shared.module';
import { MachBeesCoreModule } from 'app/core/core.module';
import { MachBeesAppRoutingModule } from './app-routing.module';
import { MachBeesHomeModule } from './home/home.module';
import { MachBeesEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ErrorComponent } from './layouts/error/error.component';
import { RegistrationModule } from './Mach-Bees/registration/registration.module';

@NgModule({
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MachBeesSharedModule,
    MachBeesCoreModule,
    MachBeesHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    MachBeesEntityModule,
    MachBeesAppRoutingModule,
    RegistrationModule,
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, FooterComponent],
  bootstrap: [MainComponent],
})
export class MachBeesAppModule {}
