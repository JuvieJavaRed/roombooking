package com.coindirect.recruitment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestBookingDto {
    String name;
    String row;
    String column;
}
