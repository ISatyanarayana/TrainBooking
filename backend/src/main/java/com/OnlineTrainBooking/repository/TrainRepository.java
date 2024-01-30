package com.OnlineTrainBooking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.OnlineTrainBooking.entity.Train;

public interface TrainRepository extends JpaRepository<Train, Integer> {
}
