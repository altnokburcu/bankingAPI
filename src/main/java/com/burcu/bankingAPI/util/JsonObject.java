package com.burcu.bankingAPI.util;

import com.burcu.bankingAPI.entity.Account;
import com.burcu.bankingAPI.entity.Entry;
import com.burcu.bankingAPI.entity.Transfer;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class JsonObject {
        @JsonProperty("entries")
        private List<Entry> entries;

        public void setEntries(List<Entry> entries) {
            this.entries = entries;
        }

        public void setAccounts(List<Account> accounts) {
            this.accounts = accounts;
        }

        public void setTransfers(List<Transfer> transfers) {
            this.transfers = transfers;
        }

        @JsonProperty("accounts")

        private List<Account> accounts;

        @JsonProperty("transfers")
        private List<Transfer> transfers;


        public List<Account> getAccountList() {
            return accounts;
        }

        public List<Entry> getEntryList() {
            return entries;
        }

        public List<Transfer> getTransferList() {
            return transfers;
        }

    }


