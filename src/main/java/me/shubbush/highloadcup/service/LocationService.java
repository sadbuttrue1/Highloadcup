package me.shubbush.highloadcup.service;

import me.shubbush.highloadcup.dao.LocationStorage;
import me.shubbush.highloadcup.dao.LocationVisitStorage;
import me.shubbush.highloadcup.dao.UserStorage;
import me.shubbush.highloadcup.dao.VisitStorage;
import me.shubbush.highloadcup.dto.LocationAvg;
import me.shubbush.highloadcup.dto.UserVisit;
import me.shubbush.highloadcup.exception.EntityNotFoundException;
import me.shubbush.highloadcup.model.Location;
import me.shubbush.highloadcup.util.PredicateBuilder;

import java.util.Date;
import java.util.OptionalDouble;
import java.util.function.Predicate;

/**
 * @author shubanev.a
 */
public class LocationService extends CrudService<Integer, Location> {

    private LocationVisitStorage locationVisitStorage;
    private VisitStorage visitStorage;
    private UserStorage userStorage;

    public LocationService(LocationStorage storage, LocationVisitStorage locationVisitStorage,
                           VisitStorage visitStorage, UserStorage userStorage) {
        this.storage = storage;
        this.locationVisitStorage = locationVisitStorage;
        this.visitStorage = visitStorage;
        this.userStorage = userStorage;
    }

    public LocationAvg calculateAvg(int locationId, Date fromDate, Date toDate,
                                    int fromAge, int toAge, String gender) throws EntityNotFoundException {

        Predicate<UserVisit> predicate = new PredicateBuilder<UserVisit>()
                .optionalAnd(fromDate, uv -> uv.getVisitedAt().after(fromDate))
                .optionalAnd(toDate, uv -> uv.getVisitedAt().after(fromDate))
                .optionalAnd(fromAge, uv -> uv.getAge() > fromAge)
                .optionalAnd(toAge, uv -> uv.getAge() < toAge)
                .optionalAnd(gender, uv -> uv.getGender().equals(gender))
                .build();

        OptionalDouble average = visitStorage.findByIdIn(locationVisitStorage.find(locationId))
                .stream()
                .map(v -> new UserVisit(userStorage.find(v.getUser()), v))
                .filter(predicate)
                .mapToInt(UserVisit::getMark)
                .average();

        LocationAvg avg = new LocationAvg();
        average.ifPresent(avg::setAvg);

        return avg;
    }
}
