import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { MachBeesTestModule } from '../../../test.module';
import { ProjectFeatureDtlUpdateComponent } from 'app/entities/project-feature-dtl/project-feature-dtl-update.component';
import { ProjectFeatureDtlService } from 'app/entities/project-feature-dtl/project-feature-dtl.service';
import { ProjectFeatureDtl } from 'app/shared/model/project-feature-dtl.model';

describe('Component Tests', () => {
  describe('ProjectFeatureDtl Management Update Component', () => {
    let comp: ProjectFeatureDtlUpdateComponent;
    let fixture: ComponentFixture<ProjectFeatureDtlUpdateComponent>;
    let service: ProjectFeatureDtlService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MachBeesTestModule],
        declarations: [ProjectFeatureDtlUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ProjectFeatureDtlUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProjectFeatureDtlUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProjectFeatureDtlService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProjectFeatureDtl(123);
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
        const entity = new ProjectFeatureDtl();
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
