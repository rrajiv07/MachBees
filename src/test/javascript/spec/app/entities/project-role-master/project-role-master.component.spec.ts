import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { MachBeesTestModule } from '../../../test.module';
import { ProjectRoleMasterComponent } from 'app/entities/project-role-master/project-role-master.component';
import { ProjectRoleMasterService } from 'app/entities/project-role-master/project-role-master.service';
import { ProjectRoleMaster } from 'app/shared/model/project-role-master.model';

describe('Component Tests', () => {
  describe('ProjectRoleMaster Management Component', () => {
    let comp: ProjectRoleMasterComponent;
    let fixture: ComponentFixture<ProjectRoleMasterComponent>;
    let service: ProjectRoleMasterService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MachBeesTestModule],
        declarations: [ProjectRoleMasterComponent],
      })
        .overrideTemplate(ProjectRoleMasterComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProjectRoleMasterComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProjectRoleMasterService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ProjectRoleMaster(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.projectRoleMasters && comp.projectRoleMasters[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
