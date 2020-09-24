import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { MachBeesTestModule } from '../../../test.module';
import { ProfileMasterUpdateComponent } from 'app/entities/profile-master/profile-master-update.component';
import { ProfileMasterService } from 'app/entities/profile-master/profile-master.service';
import { ProfileMaster } from 'app/shared/model/profile-master.model';

describe('Component Tests', () => {
  describe('ProfileMaster Management Update Component', () => {
    let comp: ProfileMasterUpdateComponent;
    let fixture: ComponentFixture<ProfileMasterUpdateComponent>;
    let service: ProfileMasterService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MachBeesTestModule],
        declarations: [ProfileMasterUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ProfileMasterUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProfileMasterUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProfileMasterService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProfileMaster(123);
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
        const entity = new ProfileMaster();
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
