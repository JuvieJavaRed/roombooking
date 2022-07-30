package com.coindirect.recruitment.controller;

import com.coindirect.recruitment.api.BookingInterface;
import com.coindirect.recruitment.dto.BookingDto;
import com.coindirect.recruitment.dto.RequestBookingDto;
import com.coindirect.recruitment.exceptions.InvalidCombinationException;
import com.coindirect.recruitment.utility.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Controller for handling bookings for a bing hall.
 * The hall can be imagined as a grid of rows and columns
 */
@Slf4j
@RestController("orders")
@CrossOrigin(origins = "*")
@Tag(name = "OrderController", description = "Manage Your Bookings Controller")
public class OrderController {

    private final BookingInterface bookingInterface;

    public OrderController(BookingInterface bookingInterface){
        this.bookingInterface = bookingInterface;
    }

    /**
     * Creates a booking with the requested details.
     * If unavailable returns a 200 with error message.
     * @param requestBooking the requested booking details.
     * @return on success booking details. on failure error message.
     */
    @PostMapping("create")
    @Operation(summary = "Get a book by its id", description = "Creates a booking request using the fields inside the requestBookingDto.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the booking",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BookingDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content)})
    ResponseEntity<BookingDto> createBooking(@Valid @RequestBody RequestBookingDto requestBooking){
        log.info(Constants.LOG_PROCESSING_MESSAGE);
        return new ResponseEntity<BookingDto>(bookingInterface.createBookingUsingRequest(requestBooking), HttpStatus.OK);
    }

    /**
     * query a booking by grid position
     * @param row grid position row
     * @param column grid position column
     * @return the booking details. 400 if not found
     */
    @GetMapping("getByPosition/{row}/{column}")
    @Operation(summary = "Creates a booking", description = "Creates a booking request using the fields inside the requestBookingDto.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the booking",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BookingDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content)})
    ResponseEntity<BookingDto> getBookingByPosition(@PathVariable String row,@PathVariable String column){
        log.info(Constants.LOG_PROCESSING_MESSAGE);
        return new ResponseEntity<BookingDto>(bookingInterface.retrieveBookingByGrid(row, column), HttpStatus.OK);
    }

    /**
     * query by booking id
     * @param bookingId booking id
     * @return the booking details. 400 if not found
     */
    @GetMapping("getByBookingId/{bookingId}")
    @Operation(summary = "Get a booking by its id", description = "Retrieves a booking using its booking ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the booking",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BookingDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content)})
    ResponseEntity<BookingDto> getBookingById(@Parameter(name = "bookingId", description = "unique ID associated with a booking") @PathVariable String bookingId) {
        log.info(Constants.LOG_PROCESSING_MESSAGE);
        if(bookingId.isEmpty() || bookingId.length() == 0){
            throw new InvalidCombinationException(Constants.ERROR_LOG_FAILED_BOOKING_ID);
        }
        return new ResponseEntity<BookingDto>(bookingInterface.retrieveBookingById(bookingId), HttpStatus.OK);
    }

    /**
     * Query if a cell is available
     * @param row grid position row
     * @param column grid position column
     * @return true if cell is available. false if not
     */
    @GetMapping("isAvailable/{row}/{column}")
    @Operation(summary = "Is the room available", description = "End point to see if a specific room is available")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the booking",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BookingDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content)})
    ResponseEntity<Boolean> isAvailable(@Parameter(name = "row", description = "The row coordinate associated with the booked room") @PathVariable String row,@Parameter(name = "column", description = "The column coordinate associated with the booked room") @PathVariable String column){
        if(row.isEmpty()||row.length() == 0){
            throw new InvalidCombinationException(Constants.ERROR_LOG_FAILED_GRID_MATRIX);
        }
        return new ResponseEntity<>(bookingInterface.isRoomAvailable(row, column), HttpStatus.OK);
    }

}
