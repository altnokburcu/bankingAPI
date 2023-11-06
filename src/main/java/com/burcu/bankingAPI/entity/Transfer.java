package com.burcu.bankingAPI.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "transfers")
public class Transfer {
    @Id
    private Long id;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTo_account_id() {
        return to_account_id;
    }

    public void setTo_account_id(Long to_account_id) {
        this.to_account_id = to_account_id;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getFrom_account_id() {
        return from_account_id;
    }

    public void setFrom_account_id(Long from_account_id) {
        this.from_account_id = from_account_id;
    }

    private Long from_account_id;
    private Long to_account_id;
    private Long amount;
    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    private Boolean flag;
}
