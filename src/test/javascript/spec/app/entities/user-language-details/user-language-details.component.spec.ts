import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { MachBeesTestModule } from '../../../test.module';
import { UserLanguageDetailsComponent } from 'app/entities/user-language-details/user-language-details.component';
import { UserLanguageDetailsService } from 'app/entities/user-language-details/user-language-details.service';
import { UserLanguageDetails } from 'app/shared/model/user-language-details.model';

describe('Component Tests', () => {
  describe('UserLanguageDetails Management Component', () => {
    let comp: UserLanguageDetailsComponent;
    let fixture: ComponentFixture<UserLanguageDetailsComponent>;
    let service: UserLanguageDetailsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MachBeesTestModule],
        declarations: [UserLanguageDetailsComponent],
      })
        .overrideTemplate(UserLanguageDetailsComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(UserLanguageDetailsComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(UserLanguageDetailsService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new UserLanguageDetails(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.userLanguageDetails && comp.userLanguageDetails[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
