package it.forfun.coding.tp.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Reservation implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    @JsonIgnore
    private Long id;

    @Enumerated(EnumType.STRING)
    private ReservationState state = ReservationState.NEW;

    @Column
    @NotNull
    @Range(min = 0, max = 2)
    private Integer court;

    @Column
    @NotNull
    private String firstName;

    @Column
    @NotNull
    private String lastName;

    @Column
    @Email
    private String email;

    @Column
    private String phoneNumber;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="CET")
    private Date reservationDateTime;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="CET")
    private Date reservationEndDateTime;

    @Column
    @Range(min = 1, max = 6)
    private Integer duration;

    @Column(length = 250)
    private String comments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCourt() {
        return court;
    }

    public void setCourt(Integer court) {
        this.court = court;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public ReservationState getStafte() {
        return state;
    }

    public void setState(ReservationState state) {
        this.state = state;
    }

    public Date getReservationDateTime() {
        return reservationDateTime;
    }

    public void setReservationDateTime(Date reservationDateTime) {
        this.reservationDateTime = reservationDateTime;
    }

    public Date getReservationEndDateTime() {
        return reservationEndDateTime;
    }

    public void setReservationEndDateTime(Date reservationEndDateTime) {
        this.reservationEndDateTime = reservationEndDateTime;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", state=" + state +
                ", court=" + court +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", reservationDateTime=" + reservationDateTime +
                ", reservationEndDateTime=" + reservationEndDateTime +
                ", duration=" + duration +
                ", comments='" + comments + '\'' +
                '}';
    }
}
