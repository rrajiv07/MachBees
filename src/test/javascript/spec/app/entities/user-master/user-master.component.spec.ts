import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { MachBeesTestModule } from '../../../test.module';
import { UserMasterComponent } from 'app/entities/user-master/user-master.component';
import { UserMasterService } from 'app/entities/user-master/user-master.service';
import { UserMaster } from 'app/shared/model/user-master.model';

describe('Component Tests', () => {
  describe('UserMaster Management Component', () => {
    let comp: UserMasterComponent;
    let fixture: ComponentFixture<UserMasterComponent>;
    let service: UserMasterService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MachBeesTestModule],
        declarations: [UserMasterComponent],
      })
        .overrideTemplate(UserMasterComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(UserMasterComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(UserMasterService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new UserMaster(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.userMasters && comp.userMasters[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
