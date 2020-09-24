import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { MachBeesTestModule } from '../../../test.module';
import { ProjectAttachmentDtlComponent } from 'app/entities/project-attachment-dtl/project-attachment-dtl.component';
import { ProjectAttachmentDtlService } from 'app/entities/project-attachment-dtl/project-attachment-dtl.service';
import { ProjectAttachmentDtl } from 'app/shared/model/project-attachment-dtl.model';

describe('Component Tests', () => {
  describe('ProjectAttachmentDtl Management Component', () => {
    let comp: ProjectAttachmentDtlComponent;
    let fixture: ComponentFixture<ProjectAttachmentDtlComponent>;
    let service: ProjectAttachmentDtlService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MachBeesTestModule],
        declarations: [ProjectAttachmentDtlComponent],
      })
        .overrideTemplate(ProjectAttachmentDtlComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProjectAttachmentDtlComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProjectAttachmentDtlService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ProjectAttachmentDtl(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.projectAttachmentDtls && comp.projectAttachmentDtls[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
