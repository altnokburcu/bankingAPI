package com.burcu.bankingAPI.repository;

import com.burcu.bankingAPI.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
    @Query(value = "SELECT * from transfers where flag=1", nativeQuery = true)
    List<Transfer> getTransferByFlag();
}
