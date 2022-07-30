package com.coindirect.recruitment.repository;

import com.coindirect.recruitment.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRespository extends JpaRepository<Booking, String> {
    Booking findByRowAndColumn(String row, String column);
    Booking findByNameAndRowAndColumn(String name, String row, String column);
    boolean existsByRowAndColumn(String row, String column);
}
