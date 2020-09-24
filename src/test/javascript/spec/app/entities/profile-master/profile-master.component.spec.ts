import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { MachBeesTestModule } from '../../../test.module';
import { ProfileMasterComponent } from 'app/entities/profile-master/profile-master.component';
import { ProfileMasterService } from 'app/entities/profile-master/profile-master.service';
import { ProfileMaster } from 'app/shared/model/profile-master.model';

describe('Component Tests', () => {
  describe('ProfileMaster Management Component', () => {
    let comp: ProfileMasterComponent;
    let fixture: ComponentFixture<ProfileMasterComponent>;
    let service: ProfileMasterService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MachBeesTestModule],
        declarations: [ProfileMasterComponent],
      })
        .overrideTemplate(ProfileMasterComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProfileMasterComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProfileMasterService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ProfileMaster(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.profileMasters && comp.profileMasters[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
