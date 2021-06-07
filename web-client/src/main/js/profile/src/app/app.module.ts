import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {NavbarComponent} from './component/navbar/navbar.component';
import {WelcomeComponent} from './component/welcome/welcome.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {RestCallsInterceptor} from "./interceptor/rest-calls.interceptor";
import { LoginComponent } from './component/login/login.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { SignUpComponent } from './component/sign-up/sign-up.component';
import {CommonsModule} from "./commons/commons.module";

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    WelcomeComponent,
    LoginComponent,
    SignUpComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        FormsModule,
        ReactiveFormsModule,
        CommonsModule
    ],
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: RestCallsInterceptor, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule {
}
