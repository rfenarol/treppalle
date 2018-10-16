package it.forfun.coding.tp.repository;

import it.forfun.coding.tp.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {


    public List<Reservation> findByEmailOrLastNameOrFirstName(String email, String lastName, String firstName);

    public List<Reservation> findByReservationDate(Date reservationDateTime);

    //USED TO FIND ALL THE RESERVATIONS STARTING FROM TODAY
    public List<Reservation> findByReservationDateTimeGreaterThanEqual(Date today);

    public Reservation findByReservationDateTime(Date reservationDateTime);

    public Long deleteByReservationDateTime(Date reservationDateTime);

    public List<Reservation> findByReservationDateTimeBetween(Date reservationTime, Date reservationEndTime);

}
