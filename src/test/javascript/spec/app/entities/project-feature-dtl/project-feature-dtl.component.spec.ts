import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { MachBeesTestModule } from '../../../test.module';
import { ProjectFeatureDtlComponent } from 'app/entities/project-feature-dtl/project-feature-dtl.component';
import { ProjectFeatureDtlService } from 'app/entities/project-feature-dtl/project-feature-dtl.service';
import { ProjectFeatureDtl } from 'app/shared/model/project-feature-dtl.model';

describe('Component Tests', () => {
  describe('ProjectFeatureDtl Management Component', () => {
    let comp: ProjectFeatureDtlComponent;
    let fixture: ComponentFixture<ProjectFeatureDtlComponent>;
    let service: ProjectFeatureDtlService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MachBeesTestModule],
        declarations: [ProjectFeatureDtlComponent],
      })
        .overrideTemplate(ProjectFeatureDtlComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProjectFeatureDtlComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProjectFeatureDtlService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ProjectFeatureDtl(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.projectFeatureDtls && comp.projectFeatureDtls[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
