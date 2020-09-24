import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { MachBeesTestModule } from '../../../test.module';
import { UserCompanyDetailsUpdateComponent } from 'app/entities/user-company-details/user-company-details-update.component';
import { UserCompanyDetailsService } from 'app/entities/user-company-details/user-company-details.service';
import { UserCompanyDetails } from 'app/shared/model/user-company-details.model';

describe('Component Tests', () => {
  describe('UserCompanyDetails Management Update Component', () => {
    let comp: UserCompanyDetailsUpdateComponent;
    let fixture: ComponentFixture<UserCompanyDetailsUpdateComponent>;
    let service: UserCompanyDetailsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MachBeesTestModule],
        declarations: [UserCompanyDetailsUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(UserCompanyDetailsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(UserCompanyDetailsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(UserCompanyDetailsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new UserCompanyDetails(123);
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
        const entity = new UserCompanyDetails();
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
