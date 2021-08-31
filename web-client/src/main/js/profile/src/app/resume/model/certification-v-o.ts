export class CertificationVO {
  constructor(public name: string,
              public issuedBy: string,
              public date: string,
              public expirationDate: string | null) {

  }
}
