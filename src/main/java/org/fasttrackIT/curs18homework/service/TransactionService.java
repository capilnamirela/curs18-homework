package org.fasttrackIT.curs18homework.service;

import org.fasttrackIT.curs18homework.exceptions.TransactionNotFoundException;
import org.fasttrackIT.curs18homework.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TransactionService {
    private final List<Transaction> transactions = new ArrayList<>();

    public List<Transaction> getAllTransactions() {
        return transactions;
    }

    public List<Transaction> getTransactionsByProduct(String product) {
        return transactions.stream()
                .filter(transaction -> transaction.product().equals(product))
                .toList();
    }

    public List<Transaction> getTransactionsByType(Transaction.TransactionType type) {
        return transactions.stream()
                .filter(transaction -> transaction.type().equals(type))
                .toList();
    }


    public List<Transaction> getAllTransactionsSmallerThan(Double amount) {
        return transactions.stream()
                .filter(transaction -> transaction.amount() < amount)
                .toList();
    }

    public List<Transaction> getAllTransactionsBiggerThan(Double amount) {
        return transactions.stream()
                .filter(transaction -> transaction.amount() > amount)
                .toList();
    }

    public Transaction getTransactionById(String id) {
        return transactions.stream()
                .filter(transaction -> transaction.id().equals(id))
                .findFirst()
                .orElseThrow(() -> new TransactionNotFoundException("Transaction with id %s was not fount".formatted(id)));

    }

    public Transaction addTransaction(Transaction newTransaction) {
        String newId = UUID.randomUUID().toString();
        transactions.add(newTransaction.withId(newId));
        return getTransactionById(newId);
    }

    public Transaction deleteById(String id) {
        Transaction transactionToBeDeleted = getTransactionById(id);
        transactions.remove(transactionToBeDeleted);
        return transactionToBeDeleted;
    }

    public Transaction replaceTransaction(String id, Transaction replaceTransaction) {
        Transaction oldTransaction = getTransactionById(id);
        deleteById(id);
        Transaction updatedTransaction = Transaction.builder()
                .id(oldTransaction.id())
                .product(replaceTransaction.product())
                .type(replaceTransaction.type())
                .amount(replaceTransaction.amount())
                .build();
        transactions.add(updatedTransaction);
        return updatedTransaction;
    }

    public List<Transaction> transactionListType(Transaction.TransactionType type) {
        List<Transaction> transactionList = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.type().equals(type)) {
                transactionList.add(transaction);
            }
        }
        return transactionList;
    }

    public Map<Transaction.TransactionType, List<Transaction>> getTypeToListTransactions() {
        Map<Transaction.TransactionType, List<Transaction>> typeToListTransactions = new HashMap<>();

        for (Transaction transaction : transactions) {
            if (!typeToListTransactions.containsKey(transaction.type())) {
                typeToListTransactions.put(transaction.type(), transactionListType(transaction.type()));
            }
        }
        return typeToListTransactions;

    }

    public List<Transaction> transactionListProduct(String product) {
        List<Transaction> transactionList = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.product().equals(product)) {
                transactionList.add(transaction);
            }
        }
        return transactionList;
    }

    public Map<String, List<Transaction>> getProductToListTransactions() {
        Map<String, List<Transaction>> productToListTransactions = new HashMap<>();

        for (Transaction transaction : transactions) {
            if (!productToListTransactions.containsKey(transaction.product())) {
                productToListTransactions.put(transaction.product(), transactionListProduct(transaction.product()));
            }
        }
        return productToListTransactions;

    }


}
