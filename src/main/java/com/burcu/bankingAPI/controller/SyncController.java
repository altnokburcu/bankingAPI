package com.burcu.bankingAPI.controller;

import com.burcu.bankingAPI.service.SyncService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sync")
public class SyncController {

    public SyncController(SyncService syncService) {
        this.syncService = syncService;
    }

    private SyncService syncService;
    @GetMapping("/sync")
    public ResponseEntity<String> sync() throws JsonProcessingException {
        try {
            syncService.processData();
            return ResponseEntity.ok("Sync data to be sent has been received");
        } catch (JsonProcessingException jsonProcessingException){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Json processing failed:" + jsonProcessingException.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Sync failed: " + e.getMessage());
        }
    }

    @PostMapping("/sync")
    public ResponseEntity<String> syncPost(@RequestBody String requestBody){
        try {
            syncService.jsonParser(requestBody);

            return ResponseEntity.ok("DB synchronized successfully");
        }
        catch (JsonProcessingException jsonProcessingException){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Json processing failed:" + jsonProcessingException.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Sync failed: " + e.getMessage());
        }

    }

}
