package com.burcu.bankingAPI.service;

import com.burcu.bankingAPI.entity.Account;
import com.burcu.bankingAPI.entity.Entry;
import com.burcu.bankingAPI.entity.Transfer;
import com.burcu.bankingAPI.repository.AccountRepository;
import com.burcu.bankingAPI.repository.EntryRepository;
import com.burcu.bankingAPI.repository.TransferRepository;
import com.burcu.bankingAPI.util.JsonObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;

import java.util.*;


@Transactional
@Service
public class SyncService {

    @Autowired
    private AccountRepository sourceAccountRepository;


    @Autowired
    private EntryRepository sourceEntryRepository;


    @Autowired
    private TransferRepository sourceTransferRepository;


    @Value("${location}")
    private String location;

    @Value("${server.adress}")
    private String serverAdress;

    private AccountService accountService;

    private TransferService transferService;


    public SyncService(AccountService accountService,TransferService transferService) {
        this.accountService=accountService;
        this.transferService=transferService;
    }

    public void processData() throws JsonProcessingException {
        if (location.equals("CLIENT")) {
            Map<String, List> dataList = new HashMap();
            dataList.put("accounts", syncAccounts());
            dataList.put("entries", syncEntries());
            dataList.put("transfers", syncTransfers());
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = objectMapper.writeValueAsString(dataList);
            System.out.println("Json file has been created");
            System.out.println(jsonString);
            postData(jsonString);
        }
    }
    public void jsonParser(String json) throws JsonProcessingException {

            ObjectMapper objectMapper = new ObjectMapper();
            JsonObject jsonObject = objectMapper.readValue(json,JsonObject.class);
            List<Account> accounts = jsonObject.getAccountList();
            List<Entry> entries = jsonObject.getEntryList();
            List<Transfer> transfers = jsonObject.getTransferList();
            syncAccounts(accounts);
            syncEntries(entries);
            syncTransfers(transfers);
    }
    public void syncAccounts(List<Account> accounts){
        for (Account account:accounts){
           Account ac= accountService.getAccountByUuid(account.getUuid());
            if(ac == null){
                accountService.createAccount(account);
            }else{
                accountService.updateAccount(account);
            }
        }
    }
    public void syncEntries(List<Entry> entries){
        for (Entry entry:entries){
            if(entry.getFlag().equals(true)){
                accountService.createEntry(entry);
            }
        }
    }
    public void syncTransfers(List<Transfer> transfers){
        for(Transfer transfer:transfers){
            if(transfer.getFlag().equals(true)){
                accountService.createTransfer(transfer);
            }

        }
    }

    public void postData(String jsonData) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> httpEntity = new HttpEntity<>(jsonData,headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(serverAdress,
                httpEntity, String.class);

       // jsonParser(jsonData);
        if(responseEntity.getStatusCode().is2xxSuccessful()){
            System.out.println(responseEntity.getBody());
        } else{
            System.out.println(responseEntity.getStatusCode());
        }


    }
    public List<Account> syncAccounts()  {
        return sourceAccountRepository.getAccountByFlag();
    }

    public List<Entry> syncEntries() {
        return sourceEntryRepository.getEntriesByFlag();
    }

    public List<Transfer> syncTransfers() {
        return sourceTransferRepository.getTransferByFlag();

    }
}
