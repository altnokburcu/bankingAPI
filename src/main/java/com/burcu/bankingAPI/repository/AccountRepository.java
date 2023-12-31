package com.burcu.bankingAPI.repository;

import com.burcu.bankingAPI.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    //
    @Query(value = "SELECT * from account where uuid=?1",nativeQuery = true)
    Account getAccountByUuid(Long uuid);

    @Query(value = "SELECT * from account where flag=1", nativeQuery = true)
    List<Account> getAccountByFlag();

    @Modifying
    @Query(value = "DELETE from account where uuid=?1", nativeQuery = true)
    void deleteAccountByUuid(Long uuid);

    default Optional<Account> findAccountByUuid(Long uuid) {
        return Optional.ofNullable(getAccountByUuid(uuid));
    }
}

