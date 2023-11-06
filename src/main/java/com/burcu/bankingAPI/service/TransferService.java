package com.burcu.bankingAPI.service;

import com.burcu.bankingAPI.entity.Account;
import com.burcu.bankingAPI.entity.Transfer;
import com.burcu.bankingAPI.repository.AccountRepository;
import com.burcu.bankingAPI.repository.TransferRepository;
import com.burcu.bankingAPI.util.UUIDGeneratorUtil;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransferService {

    public TransferService(AccountRepository accountRepository,TransferRepository transferRepository) {
        this.accountRepository = accountRepository;
        this.transferRepository = transferRepository;
    }

    private final AccountRepository accountRepository;
    private final TransferRepository transferRepository;

@Transactional
    public ResponseEntity transferMoney(Long amount, Long to_account_uuid, Long from_account_uuid) {
        if((accountRepository.getAccountByUuid(to_account_uuid) == null) || (accountRepository.getAccountByUuid(from_account_uuid) == null)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please check account ids!");

        }
        Account sender = accountRepository.getAccountByUuid(from_account_uuid);
        Account receiver = accountRepository.getAccountByUuid(to_account_uuid);

        Transfer transfer = new Transfer();
        Long uuid = UUIDGeneratorUtil.generateUUID();
        transfer.setUuid(uuid);
        transfer.setAmount(amount);
        transfer.setTo_account_id(to_account_uuid);
        transfer.setFrom_account_id(from_account_uuid);
        transfer.setFlag(true);

        if(sender.getCurrency().equals(receiver.getCurrency())){
            if(sender.getBalance()<amount || amount <0){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient amount!");

            }
            sender.setBalance(sender.getBalance()-amount);
            receiver.setBalance(receiver.getBalance()+amount);
            sender.setFlag(true);
            receiver.setFlag(true);
            accountRepository.save(sender);
            accountRepository.save(receiver);
            transferRepository.save(transfer);
            return ResponseEntity.status(HttpStatus.OK).body("Transaction completed successfully!");

        }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Account currencies are different!");
 }


}
