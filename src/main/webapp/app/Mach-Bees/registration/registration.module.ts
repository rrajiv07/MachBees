import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TableModule } from 'primeng/table';
import { DropdownModule } from 'primeng/dropdown';
import { MachBeesSharedModule } from '../../../app/shared/shared.module';
import { PasswordStrengthBarComponent } from './password-strength-bar/password-strength-bar.component';
import { RegistrationRoutingModule } from './registration-routing.module';
import { SetEmailPasswordComponent } from './set-email-password/set-email-password.component';
import { ProfileTypeComponent } from './profile-type/profile-type.component';
import { SelectProfileComponent } from './select-profile/select-profile.component';
import { PersonalDetailsComponent } from './personal-details/personal-details.component';
import { CompanyDetailsComponent } from './company-details/company-details.component';
import { ChooseSubscriptionComponent } from './choose-subscription/choose-subscription.component';
import { PaymentMethodComponent } from './payment-method/payment-method.component';
import { ConfirmUserComponent } from './confirm-user/confirm-user.component';
import { NgSelectModule } from '@ng-select/ng-select';

@NgModule({
  declarations: [
    SetEmailPasswordComponent,
    PasswordStrengthBarComponent,
    ProfileTypeComponent,
    SelectProfileComponent,
    PersonalDetailsComponent,
    CompanyDetailsComponent,
    ChooseSubscriptionComponent,
    PaymentMethodComponent,
    ConfirmUserComponent,
  ],
  imports: [CommonModule, RegistrationRoutingModule, MachBeesSharedModule, TableModule, NgSelectModule, DropdownModule],
})
export class RegistrationModule {}
