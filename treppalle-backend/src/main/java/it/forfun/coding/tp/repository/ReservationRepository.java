package it.forfun.coding.tp.repository;

import it.forfun.coding.tp.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {


    public List<Reservation> findByEmailOrLastNameOrFirstName(String email, String lastName, String firstName);

    //USED TO FIND ALL THE RESERVATIONS STARTING FROM TODAY
    List<Reservation> findByReservationDateTimeGreaterThan(Date yesterday);

    List<Reservation> findByReservationDateTime(Date reservationDateTime);

    Reservation findByReservationDateTimeAndCourt(Date reservationDateTime, Integer court);

    Long deleteByReservationDateTimeAndCourt(Date reservationDateTime, Integer court);

    List<Reservation> findByReservationDateTimeAfterAndReservationEndDateTimeBeforeAndCourt(Date reservationTime, Date reservationEndTime, Integer court);

    List<Reservation> findByReservationDateTimeBeforeAndReservationEndDateTimeAfterAndCourt(Date reservationDateTime, Date reservationEndDateTime, Integer court);

    List<Reservation> findByReservationDateTimeGreaterThanEqualAndReservationDateTimeBeforeAndCourt(Date reservationDateTime, Date reservationEndDateTime, Integer court);

    List<Reservation> findByReservationEndDateTimeAfterAndReservationEndDateTimeLessThanEqualAndCourt(Date reservationDateTime, Date reservationEndDateTime, Integer court);

}
