### Persistence in Android

# Exercise 2

Welcome to the second exercise of the _Persistence in Android_ lecture. In this exercise you will persist structured data in an _SQLite_ database using _Room_.

## Prerequisites

You should have already set up this project in the `setup` branch.

If you have already set up the project, please pull the `assignment` branch to get the base code for this exercise.

If you have not, please pull the `assignment` branch directly. For troubleshooting please refer to the ReadMe in the `setup` branch.

## Provided Code

The provided code contains an example Customer-Relationship-Management (CRM) app. The app consists of two areas that can be accessed via a navigation drawer: _Customers_ and _Invoices_. Both of these areas show a list of customers or invoices respectively. The list items can be clicked to edit the customer/invoice.

However, persistence is only implemented for adding new customers and listing them ((C)reate and (R)ead in CRUD).

## Assignment

- Implement persistence for editing customers (that's the (U)pdate in CRUD). You can find the relevant code in `components/customers/CustomerDetails.kt` and `viewmodel/CustomerDetailsViewModel.kt`.
- Prepare persistence for invoices by creating an _Entity_ and a _DAO_ for invoices. Insert your code/files in places that make sense.
- Implement persistence to adding invoices (that's the (C)reate in CRUD). You can find the relevant code in `components/invoices/InvoiceDetails.kt` and `viewmodel/InvoiceDetailsViewModel.kt`.
- Make the list of invoices be loaded from the database. You can find the relevant code in `components/invoices/InvoicesOverview.kt` and `viewmodel/InvoicesOverviewViewModel.kt`.

## Grading

| Task                               | Points |
| ---------------------------------- | ------ |
| Persistence for updating customers | 1.0    |
| Invoices entity                    | 0.5    |
| Invoices DAO                       | 0.5    |
| Persistence for adding invoices    | 0.5    |
| Loading invoices from database     | 0.5    |
| _Total_                            | 3.0    |

## Submission

Submit your solution by zipping the project folder and uploading it under _"Aufgabe 9 Persistenz & Datenbankanbindung"_ in the Ilias mailbox as _"ex2.vorname.nachname.zip"_

The assignment is due on **Thursday, 16.01.2024 at 17:10**. Later submissions will not be graded and receive 0 points.
