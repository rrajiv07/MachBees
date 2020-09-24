import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { MachBeesTestModule } from '../../../test.module';
import { CategoryMetadataComponent } from 'app/entities/category-metadata/category-metadata.component';
import { CategoryMetadataService } from 'app/entities/category-metadata/category-metadata.service';
import { CategoryMetadata } from 'app/shared/model/category-metadata.model';

describe('Component Tests', () => {
  describe('CategoryMetadata Management Component', () => {
    let comp: CategoryMetadataComponent;
    let fixture: ComponentFixture<CategoryMetadataComponent>;
    let service: CategoryMetadataService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MachBeesTestModule],
        declarations: [CategoryMetadataComponent],
      })
        .overrideTemplate(CategoryMetadataComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CategoryMetadataComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CategoryMetadataService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new CategoryMetadata(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.categoryMetadata && comp.categoryMetadata[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
