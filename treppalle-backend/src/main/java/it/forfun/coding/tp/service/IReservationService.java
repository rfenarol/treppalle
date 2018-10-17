package it.forfun.coding.tp.service;

import it.forfun.coding.tp.exception.ReservationConflictException;
import it.forfun.coding.tp.exception.ReservationNotFoundException;
import it.forfun.coding.tp.model.Reservation;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface IReservationService {

    public List<Reservation> findByEmailOrLastNameOrFirstName(String email, String lastName, String firstName) throws ReservationNotFoundException;

    public List<Reservation> findByReservationDate(Date reservationDate) throws ReservationNotFoundException;

    public List<Reservation> findByReservationDateTime(Date reservationDateTime) throws ReservationNotFoundException;

    public List<Reservation> findAll();

    public void save(Reservation reservation) throws ReservationConflictException;

    public void update(Reservation reservation) throws ReservationNotFoundException;

    public void deleteByReservationDateTimeAndCourt(Date reservationDateTime, Integer court) throws ReservationNotFoundException;

    public Reservation findByReservationDateTimeAndCourt(Date reservationDateTime, Integer court) throws ReservationNotFoundException;
}
