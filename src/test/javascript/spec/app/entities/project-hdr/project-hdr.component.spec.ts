import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { MachBeesTestModule } from '../../../test.module';
import { ProjectHdrComponent } from 'app/entities/project-hdr/project-hdr.component';
import { ProjectHdrService } from 'app/entities/project-hdr/project-hdr.service';
import { ProjectHdr } from 'app/shared/model/project-hdr.model';

describe('Component Tests', () => {
  describe('ProjectHdr Management Component', () => {
    let comp: ProjectHdrComponent;
    let fixture: ComponentFixture<ProjectHdrComponent>;
    let service: ProjectHdrService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MachBeesTestModule],
        declarations: [ProjectHdrComponent],
      })
        .overrideTemplate(ProjectHdrComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProjectHdrComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProjectHdrService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ProjectHdr(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.projectHdrs && comp.projectHdrs[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
