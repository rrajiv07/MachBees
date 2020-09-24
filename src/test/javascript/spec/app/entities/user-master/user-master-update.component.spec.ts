import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { MachBeesTestModule } from '../../../test.module';
import { UserMasterUpdateComponent } from 'app/entities/user-master/user-master-update.component';
import { UserMasterService } from 'app/entities/user-master/user-master.service';
import { UserMaster } from 'app/shared/model/user-master.model';

describe('Component Tests', () => {
  describe('UserMaster Management Update Component', () => {
    let comp: UserMasterUpdateComponent;
    let fixture: ComponentFixture<UserMasterUpdateComponent>;
    let service: UserMasterService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MachBeesTestModule],
        declarations: [UserMasterUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(UserMasterUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(UserMasterUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(UserMasterService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new UserMaster(123);
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
        const entity = new UserMaster();
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
