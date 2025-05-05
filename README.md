# Transaction API
### Purpose
The Transaction API contains all requests associated with the Transactions within the system. Similarly to the AccountsAPI it currently manages the state of the Accounts with internal memory, but I have built with DB implementation in mind.
##### Why is this a separate service?
For the purposes of this tech task, I could have easily been build in the one API which was responsible for managing the state of the Accounts and the Transactions. However, I thought that it would be interesting to build a system that more accurately mimicked what a real world micro-service's environment would look like. Therefore, the AccountsAPI is only responsible for Account's data and the TransactionsAPI is only responsible for the Transactions data.
### Resources
The resources in this section are designed to be called from the AccountsAPI and not from an end user, but I've listed them here for testing purposes
- `GET: /hello`
    - Returns Hello World. Good to check it's working as intended
- `GET: /accounts/:accountId/transactions`
    - Returns all transactions associated with an accountId
- `POST: /transactions/move-money`
    - Moves an amount of money from one accountId to another accountId. Example of the POST body can be found below
```json
{
  "senderAccountId": "1",
  "receiverAccountId": "2",
  "amount": "100"
}
```
- `POST: /transactions/reverse`
    - Reverses a list of given transactionIds. This is so that if the transactions are created but then the balance updates fail, the system can reverse the created transactions. It's also useful for back end services to be able to reverse erroneous transactions. Example of the POST body can be found below
    - It's worth noting that this doesn't erase the transaction. It simply creates a transaction with the opposite amount of the erroneous transaction, to the account. This way a full history of transactions is maintained, even if they fail
```json
[
  {
    "transactionId": "1"
  },
  {
    "transactionId": "2"
  }
]
```