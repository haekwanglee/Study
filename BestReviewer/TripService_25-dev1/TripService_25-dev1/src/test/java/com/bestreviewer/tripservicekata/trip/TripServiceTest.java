package com.bestreviewer.tripservicekata.trip;

import com.bestreviewer.tripservicekata.exception.UserNotLoggedInException;
import com.bestreviewer.tripservicekata.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TripServiceTest {
    private User loginUser;
    private List<Trip> trips;

    @Test
    @DisplayName("로그인안한 경우")
    void testExceptionNotLoggedIn() {
        TripService tripService = new TestableTripService();
        assertThrows(UserNotLoggedInException.class, ()-> {
            tripService.getTripsByUser(new User());
        });
    }

    @Test
    @DisplayName("로그인했는데 친구아닌 경우")
    void testExceptionLoggedInNotFriend() {
        loginUser = new User();
        trips = Arrays.asList(new Trip());

        User userWithNoFriend = new User();

        TripService tripService = new TestableTripService();
        List<Trip> trips = tripService.getTripsByUser(userWithNoFriend);

        assertEquals(0, trips.size());
    }

    @Test
    @DisplayName("로그인했고 친구인 경우")
    void testPassLoggedInFriend() {
        loginUser = new User();
        trips = Arrays.asList(new Trip());

        User userWithFriends = new User();
        userWithFriends.addFriend(loginUser);

        TripService tripService = new TestableTripService();
        List<Trip> trips = tripService.getTripsByUser(userWithFriends);

        assertEquals(1, trips.size());
    }

    class TestableTripService extends TripService {
        @Override
        protected User getLoggedUser() {
            return loginUser;
        }

        @Override
        protected List<Trip> TripBy(User user) {
            return trips;
        }
    }
}


