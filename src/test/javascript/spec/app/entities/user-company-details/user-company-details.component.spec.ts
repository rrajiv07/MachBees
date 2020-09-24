import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { MachBeesTestModule } from '../../../test.module';
import { UserCompanyDetailsComponent } from 'app/entities/user-company-details/user-company-details.component';
import { UserCompanyDetailsService } from 'app/entities/user-company-details/user-company-details.service';
import { UserCompanyDetails } from 'app/shared/model/user-company-details.model';

describe('Component Tests', () => {
  describe('UserCompanyDetails Management Component', () => {
    let comp: UserCompanyDetailsComponent;
    let fixture: ComponentFixture<UserCompanyDetailsComponent>;
    let service: UserCompanyDetailsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MachBeesTestModule],
        declarations: [UserCompanyDetailsComponent],
      })
        .overrideTemplate(UserCompanyDetailsComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(UserCompanyDetailsComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(UserCompanyDetailsService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new UserCompanyDetails(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.userCompanyDetails && comp.userCompanyDetails[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
