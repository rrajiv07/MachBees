import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { MachBeesTestModule } from '../../../test.module';
import { ProjectCategoryMasterComponent } from 'app/entities/project-category-master/project-category-master.component';
import { ProjectCategoryMasterService } from 'app/entities/project-category-master/project-category-master.service';
import { ProjectCategoryMaster } from 'app/shared/model/project-category-master.model';

describe('Component Tests', () => {
  describe('ProjectCategoryMaster Management Component', () => {
    let comp: ProjectCategoryMasterComponent;
    let fixture: ComponentFixture<ProjectCategoryMasterComponent>;
    let service: ProjectCategoryMasterService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MachBeesTestModule],
        declarations: [ProjectCategoryMasterComponent],
      })
        .overrideTemplate(ProjectCategoryMasterComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProjectCategoryMasterComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProjectCategoryMasterService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ProjectCategoryMaster(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.projectCategoryMasters && comp.projectCategoryMasters[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
