import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { MachBeesTestModule } from '../../../test.module';
import { UserPersonalDetailsUpdateComponent } from 'app/entities/user-personal-details/user-personal-details-update.component';
import { UserPersonalDetailsService } from 'app/entities/user-personal-details/user-personal-details.service';
import { UserPersonalDetails } from 'app/shared/model/user-personal-details.model';

describe('Component Tests', () => {
  describe('UserPersonalDetails Management Update Component', () => {
    let comp: UserPersonalDetailsUpdateComponent;
    let fixture: ComponentFixture<UserPersonalDetailsUpdateComponent>;
    let service: UserPersonalDetailsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MachBeesTestModule],
        declarations: [UserPersonalDetailsUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(UserPersonalDetailsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(UserPersonalDetailsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(UserPersonalDetailsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new UserPersonalDetails(123);
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
        const entity = new UserPersonalDetails();
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
