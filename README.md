# bankingAPI
BankingAPI provides backend services for banking transactions. 
It also includes synchronization operations that compare database entries on client and host machines and update or add records if necessary.

# Endpoint
https://documenter.getpostman.com/view/2859908/2s9YXh4N8s

# POST
## Create Account
localhost:8080/accounts/create
﻿

### Body
raw (json)
json
```{
  "owner": "Zeliş Altınok",
  "balance": "1222020",
  "currency": "TL"
}
```

# GET
## List All Accounts
localhost:8080/accounts/all
﻿

# GET
## List Account By UUID
localhost:8080/accounts/3113748762064733353
﻿

# DELETE
## Delete Account
localhost:8080/accounts/6744435196416576774
﻿

# PUT
## Withdraw Money
localhost:8080/accounts/withdraw/400/3113748762064733353
﻿

# PUT
## Deposit Money
localhost:8080/accounts/add/90000/7896152962668054412
﻿

# PUT
## Transfer
localhost:8080/transfer/20/2601026642077633134/5999554568058063943
﻿

# GET
## Get Sync Data
localhost:8080/sync/sync
﻿

# POST
## Sync
localhost:8080/sync/sync
﻿

### Body
raw (json)

json
``` {
    "entries": [
        {
            "id": 8,
            "account_id": 7896152962668054412,
            "amount": 90000,
            "uuid": 7610480889055170147,
            "flag": true
        }
    ],
    "transfers": [
        {
            "id": 770759415770858969,
            "from_account_id": 7896152962668054412,
            "to_account_id": 7896152962668054411,
            "amount": 300,
            "flag": true
        },
        {
            "id": 1415116692925726721,
            "from_account_id": 7896152962668054412,
            "to_account_id": 7896152962668054411,
            "amount": 100,
            "flag": true
        },
        {
            "id": 3883499470985055601,
            "from_account_id": 5999554568058063943,
            "to_account_id": 2601026642077633134,
            "amount": 20,
            "flag": true
        },
        {
            "id": 6889773569023035792,
            "from_account_id": 13,
            "to_account_id": 9,
            "amount": 200,
            "flag": true
        }
    ],
    "accounts": [
        {
            "id": 11,
            "owner": "Zeliş Altınok",
            "uuid": 3113748762064733353,
            "balance": 200,
            "currency": "TL",
            "flag": true
        },
        {
            "id": 18,
            "owner": "Burcu Altınok",
            "uuid": 7896152962668054412,
            "balance": 90200,
            "currency": "TL",
            "flag": true
        },
        {
            "id": 21,
            "owner": "Burcu Altınok",
            "uuid": 5999554568058063943,
            "balance": 580,
            "currency": "TL",
            "flag": true
        },
        {
            "id": 22,
            "owner": "Ali Ahmet",
            "uuid": 2601026642077633134,
            "balance": 320,
            "currency": "TL",
            "flag": true
        }
    ]
}
```

