package com.burcu.bankingAPI.repository;

import com.burcu.bankingAPI.entity.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EntryRepository extends JpaRepository<Entry, Long> {
    @Query(value = "SELECT * from entries where flag=1", nativeQuery = true)
    List<Entry> getEntriesByFlag();

  /*  @Query(value = "SELECT * from entries where uuid=?1",nativeQuery = true)
    Entry getEntryByUuid(Long uuid);
*/
}
