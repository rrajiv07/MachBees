import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { MachBeesTestModule } from '../../../test.module';
import { CategoryMetadataUpdateComponent } from 'app/entities/category-metadata/category-metadata-update.component';
import { CategoryMetadataService } from 'app/entities/category-metadata/category-metadata.service';
import { CategoryMetadata } from 'app/shared/model/category-metadata.model';

describe('Component Tests', () => {
  describe('CategoryMetadata Management Update Component', () => {
    let comp: CategoryMetadataUpdateComponent;
    let fixture: ComponentFixture<CategoryMetadataUpdateComponent>;
    let service: CategoryMetadataService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MachBeesTestModule],
        declarations: [CategoryMetadataUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(CategoryMetadataUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CategoryMetadataUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CategoryMetadataService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CategoryMetadata(123);
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
        const entity = new CategoryMetadata();
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
