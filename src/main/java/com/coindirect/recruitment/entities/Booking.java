package com.coindirect.recruitment.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @Column(name = "bookingId")
    private String bookingId;
    @Column(name = "rows")
    private String row;
    @Column(name = "column")
    private String column;
    @Column(name = "name")
    private String name;
    @Column(name = "lastupdate")
    private Date lastUpdate;
    @Column(name = "bookingdate")
    private Date bookingDate;
}
