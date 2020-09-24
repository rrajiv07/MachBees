import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { MachBeesTestModule } from '../../../test.module';
import { ProjectRoleDtlComponent } from 'app/entities/project-role-dtl/project-role-dtl.component';
import { ProjectRoleDtlService } from 'app/entities/project-role-dtl/project-role-dtl.service';
import { ProjectRoleDtl } from 'app/shared/model/project-role-dtl.model';

describe('Component Tests', () => {
  describe('ProjectRoleDtl Management Component', () => {
    let comp: ProjectRoleDtlComponent;
    let fixture: ComponentFixture<ProjectRoleDtlComponent>;
    let service: ProjectRoleDtlService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MachBeesTestModule],
        declarations: [ProjectRoleDtlComponent],
      })
        .overrideTemplate(ProjectRoleDtlComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProjectRoleDtlComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProjectRoleDtlService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ProjectRoleDtl(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.projectRoleDtls && comp.projectRoleDtls[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
