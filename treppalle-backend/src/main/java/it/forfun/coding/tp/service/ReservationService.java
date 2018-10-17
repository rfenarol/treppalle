package it.forfun.coding.tp.service;


import it.forfun.coding.tp.exception.ReservationConflictException;
import it.forfun.coding.tp.exception.ReservationNotFoundException;
import it.forfun.coding.tp.model.Reservation;
import it.forfun.coding.tp.repository.ReservationRepository;
import it.forfun.coding.tp.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ReservationService implements IReservationService{

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    @Transactional(readOnly = false)
    public void save(Reservation reservation) throws ReservationConflictException {
        Date reservationEndDateTime = DateUtil.addHoursToJavaUtilDate(reservation.getReservationDateTime(), reservation.getDuration());
        List<Reservation> reservationsIncludedInNew = reservationRepository.findByReservationDateTimeAfterAndReservationEndDateTimeBeforeAndCourt(
                reservation.getReservationDateTime(), reservationEndDateTime, reservation.getCourt());
        List<Reservation> reservationsIncludingNew = reservationRepository.findByReservationDateTimeBeforeAndReservationEndDateTimeAfterAndCourt(
                reservation.getReservationDateTime(), reservationEndDateTime, reservation.getCourt());
        List<Reservation> reservationsStartInTheMiddle = reservationRepository.findByReservationDateTimeGreaterThanEqualAndReservationDateTimeBeforeAndCourt(
                reservation.getReservationDateTime(), reservationEndDateTime, reservation.getCourt());
        List<Reservation> reservationsEndInTheMiddle = reservationRepository.findByReservationEndDateTimeAfterAndReservationEndDateTimeLessThanEqualAndCourt(
                reservation.getReservationDateTime(), reservationEndDateTime, reservation.getCourt());

        if (!reservationsIncludingNew.isEmpty() ||
                !reservationsIncludedInNew.isEmpty() ||
                !reservationsStartInTheMiddle.isEmpty() ||
                !reservationsEndInTheMiddle.isEmpty())
            throw new ReservationConflictException(reservation.getEmail());

        reservation.setReservationEndDateTime(reservationEndDateTime);
        reservationRepository.save(reservation);

    }

    @Override
    @Transactional(readOnly = false)
    public void update(Reservation reservation) throws ReservationNotFoundException {
        Reservation res = reservationRepository.findByReservationDateTimeAndCourt(reservation.getReservationDateTime(), reservation.getCourt());
        if (res == null)
            throw new ReservationNotFoundException(reservation.getEmail());
        reservationRepository.save(reservation);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reservation> findAll() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return reservationRepository.findByReservationDateTimeGreaterThan(cal.getTime());
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
        List<Reservation> reservations = reservationRepository.findByReservationDateTime(reservationDate);
        if(reservations.isEmpty())
            throw new ReservationNotFoundException(DateUtil.dateToString(reservationDate));
        return reservations;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reservation> findByReservationDateTime(Date reservationDateTime) throws ReservationNotFoundException {
        List<Reservation> reservations = reservationRepository.findByReservationDateTime(reservationDateTime);
        if(reservations.isEmpty())
            throw new ReservationNotFoundException(DateUtil.dateToString(reservationDateTime));
        return reservations;
    }


    @Override
    @Transactional(readOnly = false)
    public void deleteByReservationDateTimeAndCourt(Date reservationDateTime, Integer court) throws ReservationNotFoundException {
        Long deletedReservation = this.reservationRepository.deleteByReservationDateTimeAndCourt(reservationDateTime, court);
        if(deletedReservation == null)
            throw new ReservationNotFoundException();
    }

    @Override
    public Reservation findByReservationDateTimeAndCourt(Date reservationDateTime, Integer court) throws ReservationNotFoundException {
        Reservation res = reservationRepository.findByReservationDateTimeAndCourt(reservationDateTime, court);
        if (res == null)
            throw new ReservationNotFoundException("Date: " + DateUtil.dateToString(reservationDateTime) + " court: " + court);
        return res;
    }

}
