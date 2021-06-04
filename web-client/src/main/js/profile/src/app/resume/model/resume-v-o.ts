export class ResumeVO {
  constructor(public username: string,
              public fullName: string,
              public birthDate: string | null,
              public title: string,
              public about: string) {
  }
}
