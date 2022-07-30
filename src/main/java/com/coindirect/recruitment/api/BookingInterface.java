package com.coindirect.recruitment.api;

import com.coindirect.recruitment.dto.BookingDto;
import com.coindirect.recruitment.dto.RequestBookingDto;

/**
 * This is an interface to define services that are related to how we book rooms
 */
public interface BookingInterface {

    /**
     *  Method to check if a room/cell is vacant or not
     * @param row
     * @param column
     * @return true if it is vacant and false if it is not
     */
    public boolean isRoomAvailable(String row, String column);

    /**
     * Method to retrieve booking by a booking ID if the booking exists
     * @param bookingId
     * @return BookingDto
     */
    public BookingDto retrieveBookingById(String bookingId);

    /**
     * Method to create a booking
     * @param requestBookingDto
     * @return BookingDto
     */
    public BookingDto createBookingUsingRequest(RequestBookingDto requestBookingDto);

    /**
     * Method to retrieve a booking using the grid matrix
     * @param row
     * @param column
     * @return BookingDto
     */
    public BookingDto retrieveBookingByGrid(String row, String column);
}
