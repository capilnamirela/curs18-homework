package org.fasttrackIT.curs18homework.model;

import lombok.Builder;
import lombok.With;


@With
@Builder
public record Transaction(
        String id,
        String product,
        TransactionType type,
        Double amount

) {
    public enum TransactionType {
        SELL,
        BUY;

    }
}
