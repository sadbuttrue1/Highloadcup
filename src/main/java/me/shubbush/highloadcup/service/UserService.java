package me.shubbush.highloadcup.service;

import lombok.AllArgsConstructor;
import me.shubbush.highloadcup.dao.LocationStorage;
import me.shubbush.highloadcup.dao.UserStorage;
import me.shubbush.highloadcup.dao.UserVisitStorage;
import me.shubbush.highloadcup.dao.VisitStorage;
import me.shubbush.highloadcup.dto.LocationVisit;
import me.shubbush.highloadcup.exception.EntityNotFoundException;
import me.shubbush.highloadcup.model.User;
import me.shubbush.highloadcup.model.Visit;
import me.shubbush.highloadcup.util.PredicateBuilder;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author shubanev.a
 */
public class UserService extends CrudService<Integer, User> {

    private UserVisitStorage userVisitStorage;
    private VisitStorage visitStorage;
    private LocationStorage locationStorage;

    public UserService(UserStorage userStorage, UserVisitStorage userVisitStorage, VisitStorage visitStorage, LocationStorage locationStorage) {
        this.storage = userStorage;
        this.userVisitStorage = userVisitStorage;
        this.visitStorage = visitStorage;
        this.locationStorage = locationStorage;
    }

    public List<LocationVisit> findUserVisits(int userId, Date fromDate, Date toDate,
                                              String country, Integer toDistanse) throws EntityNotFoundException {
        Predicate<Visit> mainPredicate = new PredicateBuilder<Visit>()
                .optionalAnd(fromDate, v -> v.getVisitedAt().after(fromDate))
                .optionalAnd(toDate, v -> v.getVisitedAt().before(toDate))
                .optionalAnd(toDistanse, v -> v.getLocation() < toDistanse)
                .build();

        Predicate<LocationVisit> countryPredicate = new PredicateBuilder<LocationVisit>()
                .optionalAnd(fromDate, v -> country.equals(v.getCountry()))
                .build();

        return visitStorage.findByIdIn(userVisitStorage.find(userId))
                .stream()
                .filter(mainPredicate)
                .map(v -> new LocationVisit(v, locationStorage.find(v.getLocation())))
                .filter(countryPredicate)
                .sorted(Comparator.comparing(LocationVisit::getVisitedAt))
                .collect(Collectors.toList());
    }

}
