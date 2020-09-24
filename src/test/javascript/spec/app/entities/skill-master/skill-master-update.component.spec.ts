import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { MachBeesTestModule } from '../../../test.module';
import { SkillMasterUpdateComponent } from 'app/entities/skill-master/skill-master-update.component';
import { SkillMasterService } from 'app/entities/skill-master/skill-master.service';
import { SkillMaster } from 'app/shared/model/skill-master.model';

describe('Component Tests', () => {
  describe('SkillMaster Management Update Component', () => {
    let comp: SkillMasterUpdateComponent;
    let fixture: ComponentFixture<SkillMasterUpdateComponent>;
    let service: SkillMasterService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MachBeesTestModule],
        declarations: [SkillMasterUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(SkillMasterUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SkillMasterUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SkillMasterService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new SkillMaster(123);
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
        const entity = new SkillMaster();
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
