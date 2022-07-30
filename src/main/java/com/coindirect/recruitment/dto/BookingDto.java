package com.coindirect.recruitment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookingDto {
    String bookingId;
    String row;
    String column;
    String name;
}
