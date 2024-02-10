package org.fasttrackIT.curs18homework.controller;

import lombok.RequiredArgsConstructor;
import org.fasttrackIT.curs18homework.model.Transaction;
import org.fasttrackIT.curs18homework.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor

@RequestMapping("transactions")
public class TransactionsController {
    private final TransactionService service;

    @GetMapping
    public List<Transaction> getAllTransactions(String product, Transaction.TransactionType type, Double minAmount, Double maxAmount) {
        if (product != null) {
            return service.getTransactionsByProduct(product);
        }
        if (type != null) {
            return service.getTransactionsByType(type);
        }
        if (minAmount != null) {
            return service.getAllTransactionsBiggerThan(minAmount);
        } else if (maxAmount != null) {
            return service.getAllTransactionsSmallerThan(maxAmount);
        }
        return service.getAllTransactions();
    }

    @GetMapping("{id}")
    public Transaction getTransactionWithId(@PathVariable String id) {
        return service.getTransactionById(id);
    }

    @PostMapping
    public Transaction addTransaction(@RequestBody Transaction newTransaction) {
        return service.addTransaction(newTransaction);
    }

    @PutMapping("{id}")
    public Transaction replaceTransaction(@PathVariable String id, @RequestBody Transaction replaceTransaction) {
        return service.replaceTransaction(id, replaceTransaction);
    }

    @DeleteMapping("{id}")
    public Transaction deleteTransaction(@PathVariable String id) {
        return service.deleteById(id);
    }

    @GetMapping("/typeToList")
    public Map<Transaction.TransactionType, List<Transaction>> typeToListTransactions() {
        return service.getTypeToListTransactions();

    }

    @GetMapping("/productToList")
    public Map<String, List<Transaction>> productToListTransactions() {
        return service.getProductToListTransactions();

    }


}
