import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { MachBeesTestModule } from '../../../test.module';
import { ProjectTypeMasterComponent } from 'app/entities/project-type-master/project-type-master.component';
import { ProjectTypeMasterService } from 'app/entities/project-type-master/project-type-master.service';
import { ProjectTypeMaster } from 'app/shared/model/project-type-master.model';

describe('Component Tests', () => {
  describe('ProjectTypeMaster Management Component', () => {
    let comp: ProjectTypeMasterComponent;
    let fixture: ComponentFixture<ProjectTypeMasterComponent>;
    let service: ProjectTypeMasterService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MachBeesTestModule],
        declarations: [ProjectTypeMasterComponent],
      })
        .overrideTemplate(ProjectTypeMasterComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProjectTypeMasterComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProjectTypeMasterService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ProjectTypeMaster(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.projectTypeMasters && comp.projectTypeMasters[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
