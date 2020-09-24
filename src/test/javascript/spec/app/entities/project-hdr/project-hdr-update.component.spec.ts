import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { MachBeesTestModule } from '../../../test.module';
import { ProjectHdrUpdateComponent } from 'app/entities/project-hdr/project-hdr-update.component';
import { ProjectHdrService } from 'app/entities/project-hdr/project-hdr.service';
import { ProjectHdr } from 'app/shared/model/project-hdr.model';

describe('Component Tests', () => {
  describe('ProjectHdr Management Update Component', () => {
    let comp: ProjectHdrUpdateComponent;
    let fixture: ComponentFixture<ProjectHdrUpdateComponent>;
    let service: ProjectHdrService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MachBeesTestModule],
        declarations: [ProjectHdrUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ProjectHdrUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProjectHdrUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProjectHdrService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProjectHdr(123);
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
        const entity = new ProjectHdr();
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
