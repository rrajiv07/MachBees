import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { MachBeesTestModule } from '../../../test.module';
import { ProjectAttachmentDtlUpdateComponent } from 'app/entities/project-attachment-dtl/project-attachment-dtl-update.component';
import { ProjectAttachmentDtlService } from 'app/entities/project-attachment-dtl/project-attachment-dtl.service';
import { ProjectAttachmentDtl } from 'app/shared/model/project-attachment-dtl.model';

describe('Component Tests', () => {
  describe('ProjectAttachmentDtl Management Update Component', () => {
    let comp: ProjectAttachmentDtlUpdateComponent;
    let fixture: ComponentFixture<ProjectAttachmentDtlUpdateComponent>;
    let service: ProjectAttachmentDtlService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MachBeesTestModule],
        declarations: [ProjectAttachmentDtlUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ProjectAttachmentDtlUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProjectAttachmentDtlUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProjectAttachmentDtlService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProjectAttachmentDtl(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProjectAttachmentDtl();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
