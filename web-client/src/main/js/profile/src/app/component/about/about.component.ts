import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.css']
})
export class AboutComponent implements OnInit {

  courses: Course[] = [{
    title: 'AWS Developer: Getting Started',
    author: 'Ryan Lewis',
    platform: 'Pluralsight',
    link: 'https://app.pluralsight.com/library/courses/aws-developer-getting-started/table-of-contents',
    status: 'Finished'
  }, {
    title: 'Angular Fundamentals',
    author: 'Jim Cooper, Joe Eames',
    platform: 'Pluralsight',
    link: 'https://app.pluralsight.com/library/courses/angular-fundamentals/table-of-contents',
    status: 'Finished'
  }, {
    title: 'Java Persistence API 2.2',
    author: 'Antonio Goncalves',
    platform: 'Pluralsight',
    link: 'https://app.pluralsight.com/library/courses/java-persistence-api-21/table-of-contents',
    status: 'Finished'
  }, {
    title: 'Context and Dependency Injection (CDI 1.1)',
    author: 'Antonio Goncalves',
    platform: 'Pluralsight',
    link: 'https://app.pluralsight.com/library/courses/context-dependency-injection-1-1/table-of-contents',
    status: 'Finished'
  }, {
    title: 'Getting Started Unit Testing with JUnit 5',
    author: 'Jim Weaver',
    platform: 'Pluralsight',
    link: 'https://app.pluralsight.com/library/courses/junit-5-unit-testing-getting-started/table-of-contents',
    status: 'Finished'
  }, {
    title: 'TDD with JUnit 5',
    author: 'Catalin Tudose',
    platform: 'Pluralsight',
    link: 'https://app.pluralsight.com/library/courses/tdd-junit5/table-of-contents',
    status: 'Finished'
  }, {
    title: 'RESTFul Services in Java using Jersey',
    author: 'Bryan Hansen',
    platform: 'Pluralsight',
    link: 'https://app.pluralsight.com/library/courses/restful-services-java-using-jersey/table-of-contents',
    status: 'Finished'
  }, {
    title: 'Bean Validation 1.1',
    author: 'Antonio Goncalves',
    platform: 'Pluralsight',
    link: 'https://app.pluralsight.com/library/courses/bean-validation/table-of-contents',
    status: 'Finished'
  }, {
    title: 'Unit Testing Legacy Code in Java',
    author: 'Jim Weaver',
    platform: 'Pluralsight',
    link: 'https://app.pluralsight.com/library/courses/java-unit-testing-legacy-code/table-of-contents',
    status: 'Finished'
  }, {
    title: 'Maven Fundamentals',
    author: 'Bryan Hansen',
    platform: 'Pluralsight',
    link: 'https://app.pluralsight.com/library/courses/maven-fundamentals/table-of-contents',
    status: 'Finished'
  }, {
    title: 'SOLID Software Design Principles in Java',
    author: 'Dan Geabunea',
    platform: 'Pluralsight',
    link: 'https://app.pluralsight.com/library/courses/solid-software-design-principles-java/table-of-contents',
    status: 'Finished'
  }, {
    title: 'Java: Writing Readable and Maintainable Code',
    author: 'Andrejs Doronins',
    platform: 'Pluralsight',
    link: 'https://app.pluralsight.com/library/courses/java-writing-readable-maintainable-code/table-of-contents',
    status: 'Finished'
  }, {
    title: 'Defensive Coding in Java',
    author: 'Andrejs Doronins',
    platform: 'Pluralsight',
    link: 'https://app.pluralsight.com/library/courses/defensive-programming-java/table-of-contents',
    status: 'Finished'
  }, {
    title: 'Introduction to Testing in Java',
    author: 'Richard Warburton',
    platform: 'Pluralsight',
    link: 'https://app.pluralsight.com/library/courses/java-testing-introduction/table-of-contents',
    status: 'Planned'
  }, {
    title: 'Getting Started with Jenkins',
    author: 'Wes Higbee',
    platform: 'Pluralsight',
    link: 'https://app.pluralsight.com/library/courses/getting-started-jenkins/table-of-contents',
    status: 'Planned'
  }, {
    title: 'Handling Exceptions in Java',
    author: 'Jim Wilson',
    platform: 'Pluralsight',
    link: 'https://app.pluralsight.com/library/courses/handling-exceptions-java/table-of-contents',
    status: 'Planned'
  }, {
    title: 'Java Core Libraries: Java Log System',
    author: 'Maaike van Putter',
    platform: 'Pluralsight',
    link: 'https://app.pluralsight.com/library/courses/java-core-libraries-log-system/table-of-contents',
    status: 'In progress'
  }, {
    title: 'Java Refactoring: Best Practices',
    author: 'Andrejs Doronins',
    platform: 'Pluralsight',
    link: 'https://app.pluralsight.com/library/courses/java-refactoring-best-practices/table-of-contents',
    status: 'Planned'
  }];

  constructor() {
  }

  ngOnInit(): void {
    this.courses = this.courses.sort((a, b) => {
      return a.status === 'In progress' ? -1 : b.status === 'In progress' ? 1 :
        a.status === 'Finished' ? -1 : b.status === 'Finished' ? 1 : 0;
    })
  }

  getClassForStatus(course: Course) {
    return course.status === 'Finished' ? 'bg-success' : course.status === 'In progress' ? 'bg-warning' : 'bg-secondary';
  }
}

interface Course {
  title: string;
  author: string;
  platform: string;
  link: string;
  status: 'In progress' | 'Finished' | 'Planned';
}
