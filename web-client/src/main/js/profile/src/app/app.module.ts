import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {ProjectsListComponent} from "./projects/components/projects-list/projects-list.component";
import {ProjectThumbnailComponent} from "./projects/components/project-thumbnail/project-thumbnail.component";
import {NavbarComponent} from './components/navbar/navbar.component';
import { WelcomeComponent } from './components/welcome/welcome.component';

@NgModule({
  declarations: [
    AppComponent,
    ProjectsListComponent,
    ProjectThumbnailComponent,
    NavbarComponent,
    WelcomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
