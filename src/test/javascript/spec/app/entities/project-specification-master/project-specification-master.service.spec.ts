import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ProjectSpecificationMasterService } from 'app/entities/project-specification-master/project-specification-master.service';
import { IProjectSpecificationMaster, ProjectSpecificationMaster } from 'app/shared/model/project-specification-master.model';

describe('Service Tests', () => {
  describe('ProjectSpecificationMaster Service', () => {
    let injector: TestBed;
    let service: ProjectSpecificationMasterService;
    let httpMock: HttpTestingController;
    let elemDefault: IProjectSpecificationMaster;
    let expectedResult: IProjectSpecificationMaster | IProjectSpecificationMaster[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ProjectSpecificationMasterService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new ProjectSpecificationMaster(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a ProjectSpecificationMaster', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new ProjectSpecificationMaster()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a ProjectSpecificationMaster', () => {
        const returnedFromService = Object.assign(
          {
            specificationCode: 'BBBBBB',
            specificationName: 'BBBBBB',
            specificationDescription: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of ProjectSpecificationMaster', () => {
        const returnedFromService = Object.assign(
          {
            specificationCode: 'BBBBBB',
            specificationName: 'BBBBBB',
            specificationDescription: 'BBBBBB',
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

      it('should delete a ProjectSpecificationMaster', () => {
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
