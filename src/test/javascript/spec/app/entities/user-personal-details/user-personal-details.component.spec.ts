import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { MachBeesTestModule } from '../../../test.module';
import { UserPersonalDetailsComponent } from 'app/entities/user-personal-details/user-personal-details.component';
import { UserPersonalDetailsService } from 'app/entities/user-personal-details/user-personal-details.service';
import { UserPersonalDetails } from 'app/shared/model/user-personal-details.model';

describe('Component Tests', () => {
  describe('UserPersonalDetails Management Component', () => {
    let comp: UserPersonalDetailsComponent;
    let fixture: ComponentFixture<UserPersonalDetailsComponent>;
    let service: UserPersonalDetailsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MachBeesTestModule],
        declarations: [UserPersonalDetailsComponent],
      })
        .overrideTemplate(UserPersonalDetailsComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(UserPersonalDetailsComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(UserPersonalDetailsService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new UserPersonalDetails(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.userPersonalDetails && comp.userPersonalDetails[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
