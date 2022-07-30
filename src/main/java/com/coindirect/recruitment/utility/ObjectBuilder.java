package com.coindirect.recruitment.utility;

import com.coindirect.recruitment.dto.BookingDto;
import com.coindirect.recruitment.dto.RequestBookingDto;
import com.coindirect.recruitment.entities.Booking;

import java.util.Date;
import java.util.UUID;

public class ObjectBuilder {

    public static Booking buildANewBooking(RequestBookingDto requestBookingDto){
        return new Booking(UUID.randomUUID().toString(), requestBookingDto.getRow(), requestBookingDto.getColumn(), requestBookingDto.getName(), new Date(), new Date());
    }

    public static BookingDto buildANewBookingDto(Booking booking){
        return new BookingDto(booking.getBookingId(), booking.getRow(), booking.getColumn(), booking.getName());
    }

}
