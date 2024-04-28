package com.example.course.repo;

import com.example.course.models.Booking;
import org.springframework.data.repository.CrudRepository;

public interface BookingRepository extends CrudRepository <Booking, Long> {
}
