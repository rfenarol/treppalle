package it.forfun.coding.tp.controller;

import it.forfun.coding.tp.exception.ReservationConflictException;
import it.forfun.coding.tp.exception.ReservationNotFoundException;
import it.forfun.coding.tp.model.Reservation;
import it.forfun.coding.tp.service.IReservationService;
import it.forfun.coding.tp.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/reservations", produces = {"application/json"})
public class ReservationController {

    Logger logger = LoggerFactory.getLogger(ReservationController.class);

    @Autowired
    private IReservationService reservationService;


    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.OK)
    public List<Reservation> findReservations(HttpServletRequest request,
                                              @RequestParam(value = "email", required=false) String email,
                                              @RequestParam(value = "lastName", required=false) String lastName,
                                              @RequestParam(value = "firstName", required=false) String firstName,
                                              @RequestParam(value = "reservationDate", required=false) Date reservationDate) throws ReservationNotFoundException{
        logger.info("GET Reservations");
        if(email != null && lastName != null && firstName != null)
            return reservationService.findByEmailOrLastNameOrFirstName(email, lastName, firstName);
        else if(reservationDate != null)
            return reservationService.findByReservationDate(reservationDate);

        return reservationService.findAll();
    }

    @RequestMapping(value = "/{reservationDateTime}", method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.OK)
    public Reservation findByReservationDateTime(HttpServletRequest request,
                                                 @PathVariable String reservationDateTime) throws ReservationNotFoundException, ParseException {
        logger.info("GET Reservations");
        return reservationService.findByReservationDateTime(DateUtil.stringToDateTime(reservationDateTime));
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(HttpServletRequest request,
                       @RequestParam(value = "reservationDate", required=true) String reservationDateTime) throws ReservationNotFoundException, ParseException {
        this.logger.info("Deleting reservation with date and time: {}", reservationDateTime);
        this.reservationService.deleteByReservationDateTime(DateUtil.stringToDateTime(reservationDateTime));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(HttpServletRequest request, @RequestBody @Valid Reservation reservation, BindingResult result) throws ReservationConflictException {
        ValidatorUtil.raiseFirstError(result);
        reservationService.save(reservation);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{reservationDateTime}").buildAndExpand(reservation.getReservationDateTime()).toUri());
        return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);

    }


}
