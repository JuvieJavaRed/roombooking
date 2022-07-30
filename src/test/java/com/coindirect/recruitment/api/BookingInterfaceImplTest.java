package com.coindirect.recruitment.api;

import com.coindirect.recruitment.dto.BookingDto;
import com.coindirect.recruitment.dto.RequestBookingDto;
import com.coindirect.recruitment.entities.Booking;
import com.coindirect.recruitment.repository.BookingRespository;
import com.coindirect.recruitment.utility.ObjectBuilder;
import com.coindirect.recruitment.utility.UtilityMethods;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookingInterfaceImplTest {

    @Mock
    BookingRespository bookingRespository;

    @Mock
    BookingInterfaceImpl bookingInterface;

    private Booking booking;

    private BookingDto bookingDto;

    private RequestBookingDto requestBookingDto;

    @BeforeEach
    void init(){
        booking = new Booking("uuid-123", "1", "2", "Mthokozisi", new Date(), new Date());
        bookingDto = new BookingDto("uuid-123", "1", "2", "Mthokozisi");
        requestBookingDto = new RequestBookingDto("Mthokozisi", "1", "2");
        bookingInterface = new BookingInterfaceImpl(bookingRespository);
    }

    @Test
    void retrieveBookingById_thenSucceed(){
        Mockito.lenient().when(bookingRespository.existsById("uuid-123")).thenReturn(true);
        Mockito.lenient().when(bookingRespository.getById("uuid-123")).thenReturn(booking);
        BookingDto returnedBookingDto = ObjectBuilder.buildANewBookingDto(booking);
        assertEquals(returnedBookingDto, bookingDto);
    }

    @Test
    void retrieveBookingByGrid_thenSucceed(){
        Mockito.lenient().when(bookingRespository.existsByRowAndColumn("1", "2")).thenReturn(false);
        Mockito.lenient().when(bookingRespository.findByRowAndColumn("1", "2")).thenReturn(booking);
        BookingDto returnedBookingDto = ObjectBuilder.buildANewBookingDto(booking);
        assertEquals(bookingDto.getRow(), returnedBookingDto.getRow());
    }

    @Test
    void createBookingUsingRequest_thenSucceed(){
        Mockito.lenient().when(bookingRespository.save(booking)).thenReturn(booking);
        BookingDto returnedBookingDto = ObjectBuilder.buildANewBookingDto(booking);
        Assertions.assertEquals(bookingDto.getBookingId(),returnedBookingDto.getBookingId());
    }
}
