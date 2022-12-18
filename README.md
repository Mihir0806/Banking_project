# Banking_project

This project is part of assignments for full stack development.

Request URLs with their required request bodies:-
1. Customer creation :
  a. URL : {{base_url}}/customerOnboarding
  b. Request method : POST
  c. RequestBody : 
  {
    "custId" : "001",
    "firstName" : "firstNameValue",
    "lastName" : "lastNameValue",
    "contactNo" : "9999999999",
    "email" : "sample@gmail.com",
    "loginId" : "@1234",
    "password" : "pass@1234",
    "addressDetails" : {
        "address" : "Address line 1",
        "area" : "address line 2",
        "pinCode" : "pincode value",
        "country" : "Country value"
    },
    "customerAccountInfo" : [{
        "accountNumber" : "0001",
        "bankName" : "Bank 1",
        "branch" : "Gurgaon Branch",
        "ifsc" : "000001",
        "balance" : 200.00,
        "openingBalance" : 450000.00,
        "accountType" : "SAVINGS" // account type can only contin 2 values - > 'SAVINGS' and 'CURRENT'.
    }]
}

2. Add a new bank account for the customer.(one customer can have only 2 accounts currently).
  a. URL : {{base_url}}/updateCustomer/{{custId}}
          Path Variable : custId : customer Id with which the bank account is assosciated.
  b. Request Method : POST
  c. RequestBody :
  {
        "accountNumber" : "0002",
        "bankName" : "Bank 2",
        "branch" : "Delhi Branch",
        "ifsc" : "000002",
        "balance" : 200.00,
        "openingBalance" : 5500000.00,
        "accountType" : "CURRENT"
    }

3. Get all current active customers : 
  a. URL : {{base_url}}/allActiveUsers
  b. Request Method : GET

4. Get current balance from account number: 
  a. URL : {{base_url}}/getBalance/{{accountNumber}}
          Path variable : accountNumber : account number for which you want to get balance information.
  b. Request Method : GET      
          
5. Make a transaction :
  a. URL : {{base_url}}/transaction/{{transactionType}}
          Path Variable : transactionType : type of transaction you want to make 
            1. CREDIT
            2. DEBIT
            3. INQUIRY
  b. Request Method : POST
  c. Request Body :
  {
    "sourceAccount" : {
        "accountNumber" : "0002"
    },
    "targetAccount" : {
        "accountNumber" : "0001"
    },
    "amount" : 50
  }

6. Get Last N days transaction details : 
  a. URL : {{base_url}}/getLastNDays/{{NumberOfDays}}?accountNumber={{accountNumber}}
          Path variable : NumberOfDays : last N number of days you want the transaction details for.
          Request Params : accountNumber : The account number you wish to check the transaction details for.
  b. Request Method : GET
  
