import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { MachBeesTestModule } from '../../../test.module';
import { ProjectSkillDtlComponent } from 'app/entities/project-skill-dtl/project-skill-dtl.component';
import { ProjectSkillDtlService } from 'app/entities/project-skill-dtl/project-skill-dtl.service';
import { ProjectSkillDtl } from 'app/shared/model/project-skill-dtl.model';

describe('Component Tests', () => {
  describe('ProjectSkillDtl Management Component', () => {
    let comp: ProjectSkillDtlComponent;
    let fixture: ComponentFixture<ProjectSkillDtlComponent>;
    let service: ProjectSkillDtlService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MachBeesTestModule],
        declarations: [ProjectSkillDtlComponent],
      })
        .overrideTemplate(ProjectSkillDtlComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProjectSkillDtlComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProjectSkillDtlService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ProjectSkillDtl(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.projectSkillDtls && comp.projectSkillDtls[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
