package com.burcu.bankingAPI.controller;

import com.burcu.bankingAPI.entity.Account;
import com.burcu.bankingAPI.exception.AccountDeletionException;
import com.burcu.bankingAPI.service.AccountService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create")
    public Account createAccount(@RequestBody Account accounts) {
        return accountService.createAccount(accounts);
    }

    @GetMapping("/all")
    public Page<Account> listAccounts(
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "5", name = "size") int size){
        return accountService.listAccounts(page, size);
    }

    @GetMapping("/{uuid}")
    public Account getAccountByUuid(@PathVariable Long uuid){
        return accountService.getAccountByUuid(uuid);
    }

    @DeleteMapping("/{uuid}")
    public String deleteAccount(@PathVariable Long uuid) throws AccountDeletionException {
       return accountService.deleteAccount(uuid);

    }
    @PostMapping("/add/{amount}/{uuid}")
    public String depositMoney(@PathVariable Long amount, @PathVariable Long uuid){
        return accountService.depositMoney(amount,uuid);
    }
    @PostMapping("/withdraw/{amount}/{uuid}")
    public String withdrawMoney(@PathVariable Long amount, @PathVariable Long uuid) throws JsonProcessingException {
        return accountService.withdrawMoney(amount,uuid);
    }


}
