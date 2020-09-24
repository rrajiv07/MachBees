import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { MachBeesTestModule } from '../../../test.module';
import { ProjectSpecificationMasterComponent } from 'app/entities/project-specification-master/project-specification-master.component';
import { ProjectSpecificationMasterService } from 'app/entities/project-specification-master/project-specification-master.service';
import { ProjectSpecificationMaster } from 'app/shared/model/project-specification-master.model';

describe('Component Tests', () => {
  describe('ProjectSpecificationMaster Management Component', () => {
    let comp: ProjectSpecificationMasterComponent;
    let fixture: ComponentFixture<ProjectSpecificationMasterComponent>;
    let service: ProjectSpecificationMasterService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MachBeesTestModule],
        declarations: [ProjectSpecificationMasterComponent],
      })
        .overrideTemplate(ProjectSpecificationMasterComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProjectSpecificationMasterComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProjectSpecificationMasterService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ProjectSpecificationMaster(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.projectSpecificationMasters && comp.projectSpecificationMasters[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
