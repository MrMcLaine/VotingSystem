package org.voting.entity.history;

import org.voting.entity.Meal;
import org.voting.entity.abstractEntity.AbstractNamedEntity;

import java.time.LocalDate;

public class MenuHistory extends AbstractNamedEntity {
    private LocalDate date;
    private Meal meal;
}
