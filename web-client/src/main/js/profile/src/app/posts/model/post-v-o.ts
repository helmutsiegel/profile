export class PostVO {
  constructor(public id: number,
              public title: string,
              public content: string,
              public dateCreated: string,
              public tags: string) {
  }
}
