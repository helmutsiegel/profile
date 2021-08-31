export class CertificationVO {
  constructor(public id: number | null,
              public name: string,
              public issuedBy: string,
              public date: string,
              public expirationDate: string | null) {
  }
}
