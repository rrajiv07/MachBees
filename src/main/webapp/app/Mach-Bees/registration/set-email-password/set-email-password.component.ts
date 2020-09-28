import { Component, OnInit, AfterViewInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { SetEmailPasswordService } from './set-email-password.service';
import { Router } from '@angular/router';
import swal from 'sweetalert2';
@Component({
  selector: 'jhi-set-email-password',
  templateUrl: './set-email-password.component.html',
  styles: [],
})
export class SetEmailPasswordComponent implements OnInit, AfterViewInit {
  doNotMatch = false;
  error = false;
  errorEmailExists = false;
  errorUserExists = false;
  success = false;

  registerForm = this.fb.group({
    emailId: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(40), Validators.email]],
    password: ['', [Validators.required, Validators.minLength(4), Validators.maxLength(40)]],
    confirmPassword: ['', [Validators.required, Validators.minLength(4), Validators.maxLength(40)]],
  });
  constructor(private fb: FormBuilder, private service: SetEmailPasswordService, private route: Router) {}

  ngOnInit(): void {}
  register(): void {
    this.doNotMatch = false;
    this.error = false;
    this.errorEmailExists = false;
    this.errorUserExists = false;
    const password = this.registerForm.get(['password'])!.value;
    if (password !== this.registerForm.get(['confirmPassword'])!.value) {
      this.doNotMatch = true;
    } else {
      const emailId = this.registerForm.get(['emailId'])!.value;
      const serviceInput = {};
      serviceInput['emailId'] = emailId;
      serviceInput['password'] = password;

      this.service.save(JSON.stringify(serviceInput)).subscribe(response => {
        console.log('response>>>>>>>>>', response);
        if (response != undefined) {
          if (response['responseStatus'] == 'failure') {
            this.processError(response);
          } else {
            this.route.navigateByUrl('registration/ProfileType/' + response['userId']);
          }
        }
      });
    }
  }
  ngAfterViewInit(): void {
    const serviceInput = {};
    serviceInput['username'] = 'admin';
    serviceInput['password'] = 'admin';
    serviceInput['rememberMe'] = false;

    this.service.AuthTokenGeneration(JSON.stringify(serviceInput)).subscribe(response => {
      if (response != undefined) {
        localStorage.setItem('JWT_TOKEN', response['id_token']);
      }
    });
  }
  private processError(response: any): void {
    swal.fire({
      type: 'error',
      title: response['message'],
      showConfirmButton: true,
      customClass: 'swal-popupmsg',
    });
  }
}
