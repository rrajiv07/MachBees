import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ProjectCategoryMasterService } from 'app/entities/project-category-master/project-category-master.service';
import { IProjectCategoryMaster, ProjectCategoryMaster } from 'app/shared/model/project-category-master.model';

describe('Service Tests', () => {
  describe('ProjectCategoryMaster Service', () => {
    let injector: TestBed;
    let service: ProjectCategoryMasterService;
    let httpMock: HttpTestingController;
    let elemDefault: IProjectCategoryMaster;
    let expectedResult: IProjectCategoryMaster | IProjectCategoryMaster[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ProjectCategoryMasterService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new ProjectCategoryMaster(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a ProjectCategoryMaster', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new ProjectCategoryMaster()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a ProjectCategoryMaster', () => {
        const returnedFromService = Object.assign(
          {
            categoryCode: 'BBBBBB',
            categoryName: 'BBBBBB',
            categoryDescription: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of ProjectCategoryMaster', () => {
        const returnedFromService = Object.assign(
          {
            categoryCode: 'BBBBBB',
            categoryName: 'BBBBBB',
            categoryDescription: 'BBBBBB',
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

      it('should delete a ProjectCategoryMaster', () => {
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
