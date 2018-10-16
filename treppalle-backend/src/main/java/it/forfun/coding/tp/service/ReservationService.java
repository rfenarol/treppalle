package it.forfun.coding.tp.service;


import it.forfun.coding.tp.exception.ReservationConflictException;
import it.forfun.coding.tp.exception.ReservationNotFoundException;
import it.forfun.coding.tp.model.Reservation;
import it.forfun.coding.tp.repository.ReservationRepository;
import it.forfun.coding.tp.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ReservationService implements IReservationService{

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    @Transactional(readOnly = false)
    public void save(Reservation reservation) throws ReservationConflictException {
        Date reservatioEndDateTime = DateUtil.addHoursToJavaUtilDate(reservation.getReservationDateTime(), reservation.getDuration());
        Reservation res = reservationRepository.findByReservationDateTime(reservation.getReservationDateTime());
        List<Reservation> reservations = reservationRepository.findByReservationDateTimeBetween(reservation.getReservationDateTime(), reservatioEndDateTime);
        if (res != null || !reservations.isEmpty())
            throw new ReservationConflictException(reservation.getEmail());
        reservation.setReservationEndDateTime(reservatioEndDateTime);
        reservationRepository.save(reservation);

    }

    @Override
    @Transactional(readOnly = false)
    public void update(Reservation reservation) throws ReservationNotFoundException {
        Reservation res = reservationRepository.findByReservationDateTime(reservation.getReservationDateTime());
        if (res == null)
            throw new ReservationNotFoundException(reservation.getEmail());
        reservationRepository.save(reservation);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reservation> findAll() {
        return reservationRepository.findByReservationDateTimeGreaterThanEqual(new Date());
    }


    @Override
    @Transactional(readOnly = true)
    public List<Reservation> findByEmailOrLastNameOrFirstName(String email, String lastName, String firstName) throws ReservationNotFoundException {
        List<Reservation> reservations = reservationRepository.findByEmailOrLastNameOrFirstName(email, lastName, firstName);
        if(reservations.isEmpty())
            throw new ReservationNotFoundException(email);
        return reservations;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reservation> findByReservationDate(Date reservationDate) throws ReservationNotFoundException {
        List<Reservation> reservations = reservationRepository.findByReservationDate(reservationDate);
        if(reservations.isEmpty())
            throw new ReservationNotFoundException();
        return reservations;
    }

    @Override
    @Transactional(readOnly = true)
    public Reservation findByReservationDateTime(Date reservationDateTime) throws ReservationNotFoundException {
        Reservation reservation = reservationRepository.findByReservationDateTime(reservationDateTime);
        if(reservation == null)
            throw new ReservationNotFoundException(reservation.getEmail());
        return reservation;
    }


    @Override
    @Transactional(readOnly = false)
    public void deleteByReservationDateTime(Date reservationDateTime) throws ReservationNotFoundException {
        Long deletedReservation = this.reservationRepository.deleteByReservationDateTime(reservationDateTime);
        if(deletedReservation == null)
            throw new ReservationNotFoundException();
    }

}
