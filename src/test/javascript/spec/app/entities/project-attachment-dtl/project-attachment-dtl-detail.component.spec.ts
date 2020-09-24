import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MachBeesTestModule } from '../../../test.module';
import { ProjectAttachmentDtlDetailComponent } from 'app/entities/project-attachment-dtl/project-attachment-dtl-detail.component';
import { ProjectAttachmentDtl } from 'app/shared/model/project-attachment-dtl.model';

describe('Component Tests', () => {
  describe('ProjectAttachmentDtl Management Detail Component', () => {
    let comp: ProjectAttachmentDtlDetailComponent;
    let fixture: ComponentFixture<ProjectAttachmentDtlDetailComponent>;
    const route = ({ data: of({ projectAttachmentDtl: new ProjectAttachmentDtl(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MachBeesTestModule],
        declarations: [ProjectAttachmentDtlDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ProjectAttachmentDtlDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProjectAttachmentDtlDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load projectAttachmentDtl on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.projectAttachmentDtl).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
