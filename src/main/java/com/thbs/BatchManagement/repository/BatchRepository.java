package com.thbs.BatchManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thbs.BatchManagement.entity.Batch;

@Repository
public interface BatchRepository  extends JpaRepository<Batch, Long>{

	Batch findByBatchName(String string);
}
