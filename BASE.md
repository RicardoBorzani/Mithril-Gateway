AUTHENTICATION
POST /auth/login
POST /auth/logout

USERS
GET /users
GET /users/:id
POST /users
PUT /users/:id
DELETE /users/:id

CONFIGURATIONS
GET /config
GET /config/:id
POST /config
PUT /config/:id
DELETE /config/:id

TRANSACTIONS
GET /transactions
GET /transactions/:id
POST /transactions
PUT /transactions/:id
DELETE /transactions/:id

CALCULATIONS
POST /calculations

REPORTS
GET /reports
GET /reports/:id
GET /reports/:id/print
POST /reports
PUT /reports/:id
DELETE /reports/:id

IMPORT
POST /import
GET /import/status
GET /import/result

LOGS
GET /logs/login
GET /logs/access
GET /logs/config

FINANCIAL
GROUP /finance
INVOICES
GET /invoices
POST /invoices
GET /invoices/:id
PUT /invoices/:id
DELETE /invoices/:id

PAYMENTS
GET /payments
POST /payments
GET /payments/:id
PUT /payments/:id
DELETE /payments/:id

ACCOUNTS
GET /accounts
POST /accounts
GET /accounts/:id
PUT /accounts/:id
DELETE /accounts/:id

TRANSACTIONS
GET /account-transactions
POST /account-transactions
GET /account-transactions/:id
PUT /account-transactions/:id
DELETE /account-transactions/:id

CLOSINGS
GET /closings
POST /closings
GET /closings/:id
PUT /closings/:id
DELETE /closings/:id

TRANSFERS
GET /transfers
POST /transfers
GET /transfers/:id
PUT /transfers/:id
DELETE /transfers/:id
