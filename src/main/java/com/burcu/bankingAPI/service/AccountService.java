package com.burcu.bankingAPI.service;

import com.burcu.bankingAPI.entity.Account;
import com.burcu.bankingAPI.entity.Entry;
import com.burcu.bankingAPI.entity.Transfer;
import com.burcu.bankingAPI.repository.AccountRepository;
import com.burcu.bankingAPI.repository.EntryRepository;

import com.burcu.bankingAPI.repository.TransferRepository;
import com.burcu.bankingAPI.util.UUIDGeneratorUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AccountService {
    private final AccountRepository accountRepository;
    private final  EntryRepository entryRepository;

    private final TransferRepository transferRepository;

    public AccountService(AccountRepository accountRepository, EntryRepository entryRepository,TransferRepository transferRepository) {
        this.accountRepository = accountRepository;
        this.entryRepository = entryRepository;
        this.transferRepository = transferRepository;
    }


    public Account getAccountByUuid(Long uuid){
        return accountRepository.getAccountByUuid(uuid);
    }

    public Account createAccount(Account account) {
        Long uuid = UUIDGeneratorUtil.generateUUID();
        if(account.getUuid() == null) {
            account.setUuid(uuid);
        }
        account.setFlag(true);
        return accountRepository.save(account);
    }
    public void createEntry(Entry entry){
        entry.setFlag(false);
        entryRepository.save(entry);
    }
    public void createTransfer(Transfer transfer){
        transfer.setFlag(false);
        transferRepository.save(transfer);
    }
    public void updateAccount(Account account){
        Account tempAccount = accountRepository.getAccountByUuid(account.getUuid());
        tempAccount.setOwner(account.getOwner());
        tempAccount.setBalance(account.getBalance());
        tempAccount.setFlag(false);
        accountRepository.save(tempAccount);

    }

    public Page<Account> listAccounts(int page, int size) {
        return accountRepository.findAll(PageRequest.of(page, size));
    }


    public String deleteAccount(Long uuid) {
        if (accountRepository.getAccountByUuid(uuid).getBalance() == 0) {
            accountRepository.deleteById(uuid);
        } else {
            return "The balance must be 0 for the account to be deleted.";
        }
        return "Account has been deleted.";
    }

    @Transactional
    public String depositMoney(Long amount, Long uuid) {
        Account account = accountRepository.getAccountByUuid(uuid);
        if (account == null) {
            return "Wrong account id!";
        }
        account.setBalance(account.getBalance() + amount);
        account.setFlag(true);
        accountRepository.save(account);

        Long entryId = UUIDGeneratorUtil.generateUUID();
        Entry entry = new Entry();
        entry.setUuid(entryId);
        entry.setAccount_id(uuid);
        entry.setAmount(amount);
        entry.setFlag(true);
        entryRepository.save(entry);
        return "Amount has been added successfully!";
    }


    @Transactional
    public String withdrawMoney(Long amount, Long uuid) throws JsonProcessingException {
        Account account = accountRepository.getAccountByUuid(uuid);
        if (account == null) {
            return "Wrong account id!";
        }

        if(account.getBalance()<amount){
            return "Insufficient balance!";
        }
        account.setBalance(account.getBalance()-amount);
        account.setFlag(true);
        accountRepository.save(account);


        Long entryId = UUIDGeneratorUtil.generateUUID();
        Entry entry = new Entry();
        entry.setUuid(entryId);
        entry.setAccount_id(uuid);
        entry.setAmount(amount);
        entry.setFlag(true);
        entryRepository.save(entry);

        return "Amount has been deducted successfully!";
    }
}
