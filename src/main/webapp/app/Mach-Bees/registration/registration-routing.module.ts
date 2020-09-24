import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { SetEmailPasswordComponent } from './set-email-password/set-email-password.component';
import { ProfileTypeComponent } from './profile-type/profile-type.component';
import { SelectProfileComponent } from './select-profile/select-profile.component';
import { PersonalDetailsComponent } from './personal-details/personal-details.component';
import { CompanyDetailsComponent } from './company-details/company-details.component';
import { ChooseSubscriptionComponent } from './choose-subscription/choose-subscription.component';
import { PaymentMethodComponent } from './payment-method/payment-method.component';
import { ConfirmUserComponent } from './confirm-user/confirm-user.component';
const routes: Routes = [
  { path: 'SetEmailPassword', component: SetEmailPasswordComponent },
  { path: 'ProfileType/:userId', component: ProfileTypeComponent },
  { path: 'SelectProfile/:userId', component: SelectProfileComponent },
  { path: 'PersonalDetails/:userId', component: PersonalDetailsComponent },
  { path: 'CompanyDetails', component: CompanyDetailsComponent },
  { path: 'ChooseSubscription', component: ChooseSubscriptionComponent },
  { path: 'PaymentMethod', component: PaymentMethodComponent },
  { path: 'ConfirmUser', component: ConfirmUserComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class RegistrationRoutingModule {}
