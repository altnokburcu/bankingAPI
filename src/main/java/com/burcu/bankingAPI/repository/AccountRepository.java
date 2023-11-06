package com.burcu.bankingAPI.repository;

import com.burcu.bankingAPI.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    //
    @Query(value = "SELECT * from account where uuid=?1",nativeQuery = true)
    Account getAccountByUuid(Long uuid);

    @Query(value = "SELECT * from account where flag=1", nativeQuery = true)
    List<Account> getAccountByFlag();


}
