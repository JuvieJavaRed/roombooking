package com.coindirect.recruitment.controller;

import com.coindirect.recruitment.api.BookingInterface;
import com.coindirect.recruitment.dto.BookingDto;
import com.coindirect.recruitment.dto.RequestBookingDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class OrderControllerIntegrationTest {

    @Autowired
    private OrderController orderController;


    @MockBean
    private BookingInterface bookingInterface;


    @Test
    public void createBooking_successfullyBooking() throws Exception {
        BookingDto bookingDto = new BookingDto("uuid-123", "1", "2", "Mthokozisi");
        RequestBookingDto requestBookingDto = new RequestBookingDto("Mthokozisi", "1", "2");
        when(bookingInterface.createBookingUsingRequest(requestBookingDto)).thenReturn(bookingDto);
        ResponseEntity<BookingDto> savedbookingDto = orderController.createBooking(requestBookingDto);
        Assertions.assertEquals(savedbookingDto.getStatusCodeValue(), 200);
        Assertions.assertEquals(savedbookingDto.getBody().getBookingId(),bookingDto.getBookingId());
    }

    @Test
    public void getBookingByPosition_successful() throws Exception {
        BookingDto bookingDto = new BookingDto("uuid-123", "1", "2", "Mthokozisi");
        when(bookingInterface.retrieveBookingByGrid("1", "2")).thenReturn(bookingDto);
        ResponseEntity<BookingDto> returnedBookingDto = orderController.getBookingByPosition("1", "2");
        Assertions.assertEquals(returnedBookingDto.getStatusCodeValue(), 200);
        Assertions.assertEquals(returnedBookingDto.getBody().getBookingId(), bookingDto.getBookingId());
    }

    @Test
    public void getBookingById_successful(){
        BookingDto bookingDto = new BookingDto("uuid-123", "1", "2", "Mthokozisi");
        when(bookingInterface.retrieveBookingById("uuid-123")).thenReturn(bookingDto);
        ResponseEntity<BookingDto> returnedBookingDto = orderController.getBookingById("uuid-123");
        Assertions.assertEquals(returnedBookingDto.getStatusCodeValue(), 200);
        Assertions.assertEquals(returnedBookingDto.getBody().getBookingId(), bookingDto.getBookingId());
    }

    @Test
    public void isAvailable_successful(){
        BookingDto bookingDto = new BookingDto("uuid-123", "1", "2", "Mthokozisi");
        when(bookingInterface.isRoomAvailable("1", "3")).thenReturn(true);
        ResponseEntity<Boolean> returnedResponse = orderController.isAvailable("1", "3");
        Assertions.assertEquals(returnedResponse.getStatusCodeValue(), 200);
        Assertions.assertEquals(returnedResponse.getBody().booleanValue(), true);
    }
}
