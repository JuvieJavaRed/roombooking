package com.coindirect.recruitment.api;

import com.coindirect.recruitment.dto.BookingDto;
import com.coindirect.recruitment.dto.RequestBookingDto;
import com.coindirect.recruitment.exceptions.InvalidCombinationException;
import com.coindirect.recruitment.exceptions.UnavailabilityException;
import com.coindirect.recruitment.repository.BookingRespository;
import com.coindirect.recruitment.utility.Constants;
import com.coindirect.recruitment.utility.ObjectBuilder;
import com.coindirect.recruitment.utility.UtilityMethods;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BookingInterfaceImpl implements BookingInterface{

    private final BookingRespository bookingRespository;

    public BookingInterfaceImpl(BookingRespository bookingRespository){
        this.bookingRespository = bookingRespository;
    }
    /**
     * Method to check if a room/cell is vacant or not
     *
     * @param row
     * @param column
     * @return true if it is vacant and false if it is not
     */
    @Override
    public boolean isRoomAvailable(String row, String column) {
        if(!UtilityMethods.validatedInput(row, column)){
            throw new UnavailabilityException(Constants.ERROR_LOG_FAILED_GRID_MATRIX);
        }
        boolean isVacant = bookingRespository.existsByRowAndColumn(row, column);
        return !isVacant;
    }

    /**
     * Method to retrieve booking by a booking ID if the booking exists
     *
     * @param bookingId
     * @return BookingDto
     */
    @Override
    public BookingDto retrieveBookingById(String bookingId) {
        if(!bookingRespository.existsById(bookingId)){
            throw new InvalidCombinationException(Constants.ERROR_LOG_FAILED_BOOKING_ID);
        }
        return ObjectBuilder.buildANewBookingDto(bookingRespository.getById(bookingId));
    }

    /**
     * Method to create a booking
     *
     * @param requestBookingDto
     * @return BookingDto
     */
    @Override
    public BookingDto createBookingUsingRequest(RequestBookingDto requestBookingDto) {
        return ObjectBuilder.buildANewBookingDto(bookingRespository.save(ObjectBuilder.buildANewBooking(requestBookingDto)));
    }

    /**
     * Method to retrieve a booking using the grid matrix
     *
     * @param row
     * @param column
     * @return BookingDto
     */
    @Override
    public BookingDto retrieveBookingByGrid(String row, String column) {
        if(bookingRespository.existsByRowAndColumn(row, column)){
            throw new UnavailabilityException(Constants.ERROR_LOG_FAILED_GRID_MATRIX);
        }
        return ObjectBuilder.buildANewBookingDto(bookingRespository.findByRowAndColumn(row, column));
    }
}
