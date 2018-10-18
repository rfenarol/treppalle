import {Injectable} from "@angular/core";
import {AppSettings} from "../app.settings";
import {Reservation} from "./Reservation";
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable()
export class ReservationService{

    private tasksStartUrl = AppSettings.API_ENDPOINT + "/reservations";  // URL to retrieve reservations
    private headers = new HttpHeaders().set( 'Content-Type', 'application/json' );

    constructor( private http: HttpClient ) { }

    public listReservations(): Promise<any> {
        const url = `${this.tasksStartUrl}`;
        return this.http.get<any>(url)
            .toPromise()
            .then(response => response as Reservation[]);
    }


}