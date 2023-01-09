package org.voting.util;

import org.hibernate.proxy.HibernateProxy;
import org.hibernate.proxy.LazyInitializer;
import org.voting.entity.Restaurant;

public class RestaurantUtil {
    public static Restaurant deleteProxy(Restaurant restWithPr) {
        HibernateProxy hibernateProxy = (HibernateProxy) restWithPr;
        LazyInitializer initializer = hibernateProxy.getHibernateLazyInitializer();
        return (Restaurant) initializer.getImplementation();
    }
}
