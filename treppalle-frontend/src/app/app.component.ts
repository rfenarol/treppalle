import {Component} from "@angular/core";
// import {ToastrService} from "ngx-toastr";
import "./assets/styles/main.css";
import {ReservationService} from "./models/reservation.service";
import {Reservation} from "./models/Reservation";

// import {HeaderComponent} from "./header/header.component";

@Component({
    selector: 'my-app',
    styleUrls: ["./app.component.css"],
    templateUrl: "./app.component.html",
})
export class AppComponent {

    protected reservations = [] as Reservation[];


    // @HostBinding("class.menubar-pin")
    // @HostBinding("class.menubar-visible") private menubar: boolean;
    private alive: boolean = true;
    // @ViewChild(HeaderComponent)
    // private headerComponent: HeaderComponent;
    private title = "Treppalle:";

    constructor(private reservationService: ReservationService/*private toastr: ToastrService*/) {
        console.log("Getting ALL Reservations")
        this.reservationService.listReservations().then((reservations) => this.reservations = reservations/*.slice(1, 5)*/);
    }
    // public mouseOverEvent() {
    //     if(this.headerComponent)
    //         this.headerComponent.collapseSearchTextArea();
    // }


    private handleError( error: any ): Promise<any> {
        console.error( 'An error occurred', error ); // for demo purposes only
        return Promise.reject( error.message || error );
    }


    public ngOnDestroy() {
       this.alive = false;
    }
}
