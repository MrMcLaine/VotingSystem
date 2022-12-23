package org.voting.entity.history;

import org.voting.entity.abstractEntity.AbstractNamedEntity;

import java.time.LocalDate;

public class VotesHistory extends AbstractNamedEntity {
    private LocalDate date;
    private int votes;
}
