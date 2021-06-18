export class PersonalInfoVO {
  constructor(public email: string,
              public fullName: string,
              public birthDate: string | null,
              public title: string) {
  }
}
