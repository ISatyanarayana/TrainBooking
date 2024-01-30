package com.OnlineTrainBooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.OnlineTrainBooking.entity.TrainMaster;

@Repository
public interface TrainMasterRepository extends JpaRepository<TrainMaster, Integer> {
			
}
