import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {BrowserModule} from '@angular/platform-browser';
import {HttpClientModule} from "@angular/common/http";
import {ReservationService} from "./models/reservation.service";

@NgModule( {
    bootstrap: [AppComponent],
    declarations: [
        AppComponent
    ],
    imports: [
        BrowserModule,
        HttpClientModule,
        // FormsModule,
        // TreeviewModule.forRoot(),
        // TabsModule.forRoot(),
        // ProgressbarModule.forRoot(),
        // CollapseModule.forRoot(),
        // PopoverModule.forRoot(),
        // BsDropdownModule.forRoot(),
        // PaginationModule.forRoot(),
        // ToastrModule.forRoot(), // ToastrModule added
        // TypeaheadModule.forRoot(),
        // ReactiveFormsModule,
        // TooltipModule.forRoot(),
        // ModalModule.forRoot(),
        // NgProgressModule,
        // TagInputModule, BrowserAnimationsModule, NgxChartsModule, NgxDatatableModule,
        // AppRoutingModule,
    ],
    // for each service created a singleton instance, available to all components of the app
    providers: [
        // { provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi:true},
        ReservationService,
    ],
} )
export class AppModule { }


