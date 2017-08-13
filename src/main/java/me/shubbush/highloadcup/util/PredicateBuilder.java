package me.shubbush.highloadcup.util;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * @author shubanev.a
 */
public class PredicateBuilder<T> {

    private Predicate<T> predicate;

    public PredicateBuilder() {
        this.predicate = t -> true;
    }

    public PredicateBuilder<T> optionalAnd(Object value, Predicate<T> rightPredicate) {
        if (Objects.nonNull(value))
            predicate = predicate.and(rightPredicate);

        return this;
    }

    public Predicate<T> build() {
        return predicate;
    }

}
