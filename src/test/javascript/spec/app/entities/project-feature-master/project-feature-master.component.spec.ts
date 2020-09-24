import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { MachBeesTestModule } from '../../../test.module';
import { ProjectFeatureMasterComponent } from 'app/entities/project-feature-master/project-feature-master.component';
import { ProjectFeatureMasterService } from 'app/entities/project-feature-master/project-feature-master.service';
import { ProjectFeatureMaster } from 'app/shared/model/project-feature-master.model';

describe('Component Tests', () => {
  describe('ProjectFeatureMaster Management Component', () => {
    let comp: ProjectFeatureMasterComponent;
    let fixture: ComponentFixture<ProjectFeatureMasterComponent>;
    let service: ProjectFeatureMasterService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MachBeesTestModule],
        declarations: [ProjectFeatureMasterComponent],
      })
        .overrideTemplate(ProjectFeatureMasterComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProjectFeatureMasterComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProjectFeatureMasterService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ProjectFeatureMaster(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.projectFeatureMasters && comp.projectFeatureMasters[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
