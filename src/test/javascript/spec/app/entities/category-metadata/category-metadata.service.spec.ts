import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { CategoryMetadataService } from 'app/entities/category-metadata/category-metadata.service';
import { ICategoryMetadata, CategoryMetadata } from 'app/shared/model/category-metadata.model';

describe('Service Tests', () => {
  describe('CategoryMetadata Service', () => {
    let injector: TestBed;
    let service: CategoryMetadataService;
    let httpMock: HttpTestingController;
    let elemDefault: ICategoryMetadata;
    let expectedResult: ICategoryMetadata | ICategoryMetadata[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(CategoryMetadataService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new CategoryMetadata(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a CategoryMetadata', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new CategoryMetadata()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a CategoryMetadata', () => {
        const returnedFromService = Object.assign(
          {
            categoryCode: 'BBBBBB',
            categoryName: 'BBBBBB',
            categoryDescription: 'BBBBBB',
            sequenceNumber: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of CategoryMetadata', () => {
        const returnedFromService = Object.assign(
          {
            categoryCode: 'BBBBBB',
            categoryName: 'BBBBBB',
            categoryDescription: 'BBBBBB',
            sequenceNumber: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a CategoryMetadata', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
