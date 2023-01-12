package org.voting;

import org.springframework.test.web.servlet.ResultMatcher;
import org.voting.entity.Restaurant;

import java.util.Arrays;

import static org.voting.MatcherTestData.assertMatch;
import static org.voting.TestUtil.readFromJsonMvcResult;
import static org.voting.TestUtil.readListFromJsonMvcResult;
import static org.voting.entity.abstractEntity.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {

    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class, "meals");
    public static final int CENTRAL_ID = START_SEQ + 4;
    public static final int BONBON_ID = START_SEQ + 5;
    public static final int ATELIER_CRENN_ID = START_SEQ + 6;
    public static final int NOT_FOUND = 200;

    public static final Restaurant central = new Restaurant(CENTRAL_ID, "Central, Lima");
    public static final Restaurant bonBon = new Restaurant(BONBON_ID, "Bon-Bon, Brussels");
    public static final Restaurant atelierCrenn = new Restaurant(ATELIER_CRENN_ID, "Atelier Crenn, San Francisco");

    public static Restaurant getNew() {
        return new Restaurant(null, "New");
    }

    public static Restaurant getUpdated() {
        Restaurant updated = new Restaurant(central);
        updated.setName("UpdatedName");
        return updated;
    }

    public static ResultMatcher contentJson(Restaurant... expected) {
        return result -> assertMatch(readListFromJsonMvcResult(result, Restaurant.class), Arrays.asList(expected));
    }

    public static ResultMatcher contentJson(Restaurant expected) {
        return result -> assertMatch(readFromJsonMvcResult(result, Restaurant.class), expected);
    }
}
