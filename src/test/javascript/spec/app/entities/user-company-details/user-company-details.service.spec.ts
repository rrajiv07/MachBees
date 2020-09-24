import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { UserCompanyDetailsService } from 'app/entities/user-company-details/user-company-details.service';
import { IUserCompanyDetails, UserCompanyDetails } from 'app/shared/model/user-company-details.model';

describe('Service Tests', () => {
  describe('UserCompanyDetails Service', () => {
    let injector: TestBed;
    let service: UserCompanyDetailsService;
    let httpMock: HttpTestingController;
    let elemDefault: IUserCompanyDetails;
    let expectedResult: IUserCompanyDetails | IUserCompanyDetails[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(UserCompanyDetailsService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new UserCompanyDetails(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a UserCompanyDetails', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new UserCompanyDetails()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a UserCompanyDetails', () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            website: 'BBBBBB',
            description: 'BBBBBB',
            address: 'BBBBBB',
            vat: 'BBBBBB',
            mobile: 'BBBBBB',
            linkedin: 'BBBBBB',
            twitter: 'BBBBBB',
            skype: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of UserCompanyDetails', () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            website: 'BBBBBB',
            description: 'BBBBBB',
            address: 'BBBBBB',
            vat: 'BBBBBB',
            mobile: 'BBBBBB',
            linkedin: 'BBBBBB',
            twitter: 'BBBBBB',
            skype: 'BBBBBB',
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

      it('should delete a UserCompanyDetails', () => {
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
