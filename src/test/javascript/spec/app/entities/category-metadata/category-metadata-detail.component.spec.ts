import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MachBeesTestModule } from '../../../test.module';
import { CategoryMetadataDetailComponent } from 'app/entities/category-metadata/category-metadata-detail.component';
import { CategoryMetadata } from 'app/shared/model/category-metadata.model';

describe('Component Tests', () => {
  describe('CategoryMetadata Management Detail Component', () => {
    let comp: CategoryMetadataDetailComponent;
    let fixture: ComponentFixture<CategoryMetadataDetailComponent>;
    const route = ({ data: of({ categoryMetadata: new CategoryMetadata(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MachBeesTestModule],
        declarations: [CategoryMetadataDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(CategoryMetadataDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CategoryMetadataDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load categoryMetadata on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.categoryMetadata).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
