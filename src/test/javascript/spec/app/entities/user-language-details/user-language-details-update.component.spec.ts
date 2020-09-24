import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { MachBeesTestModule } from '../../../test.module';
import { UserLanguageDetailsUpdateComponent } from 'app/entities/user-language-details/user-language-details-update.component';
import { UserLanguageDetailsService } from 'app/entities/user-language-details/user-language-details.service';
import { UserLanguageDetails } from 'app/shared/model/user-language-details.model';

describe('Component Tests', () => {
  describe('UserLanguageDetails Management Update Component', () => {
    let comp: UserLanguageDetailsUpdateComponent;
    let fixture: ComponentFixture<UserLanguageDetailsUpdateComponent>;
    let service: UserLanguageDetailsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MachBeesTestModule],
        declarations: [UserLanguageDetailsUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(UserLanguageDetailsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(UserLanguageDetailsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(UserLanguageDetailsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new UserLanguageDetails(123);
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
        const entity = new UserLanguageDetails();
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
