import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { MachBeesTestModule } from '../../../test.module';
import { SkillCategoryMasterUpdateComponent } from 'app/entities/skill-category-master/skill-category-master-update.component';
import { SkillCategoryMasterService } from 'app/entities/skill-category-master/skill-category-master.service';
import { SkillCategoryMaster } from 'app/shared/model/skill-category-master.model';

describe('Component Tests', () => {
  describe('SkillCategoryMaster Management Update Component', () => {
    let comp: SkillCategoryMasterUpdateComponent;
    let fixture: ComponentFixture<SkillCategoryMasterUpdateComponent>;
    let service: SkillCategoryMasterService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MachBeesTestModule],
        declarations: [SkillCategoryMasterUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(SkillCategoryMasterUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SkillCategoryMasterUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SkillCategoryMasterService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new SkillCategoryMaster(123);
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
        const entity = new SkillCategoryMaster();
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
