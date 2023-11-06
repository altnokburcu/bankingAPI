package com.burcu.bankingAPI.controller;

import com.burcu.bankingAPI.service.TransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transfer")
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PutMapping("/{amount}/{to_account_uuid}/{from_account_uuid}")
    public ResponseEntity transfer(@PathVariable Long amount, @PathVariable Long to_account_uuid, @PathVariable Long from_account_uuid){
        return transferService.transferMoney(amount,to_account_uuid,from_account_uuid);
    }


}
