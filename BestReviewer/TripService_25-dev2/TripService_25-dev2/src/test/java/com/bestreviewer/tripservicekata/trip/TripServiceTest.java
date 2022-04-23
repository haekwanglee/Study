package com.bestreviewer.tripservicekata.trip;

import com.bestreviewer.tripservicekata.exception.UserNotLoggedInException;
import com.bestreviewer.tripservicekata.user.User;
import com.bestreviewer.tripservicekata.user.UserSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TripServiceTest {
    MockedStatic<UserSession> userSessionMockedStatic;
    UserSession userSessionMock;

    MockedStatic<TripDAO> tripDAOMockedStatic;
    TripDAO tripDAOMock;

    @BeforeEach
    public void setup(){
        userSessionMockedStatic = mockStatic(UserSession.class);
        userSessionMock = mock(UserSession.class);
        when(UserSession.getInstance()).thenReturn(userSessionMock);

        tripDAOMockedStatic = mockStatic(TripDAO.class);
        tripDAOMock = mock(TripDAO.class);
    }

    @AfterEach
    public void tearDown(){
        tripDAOMockedStatic.close();
        userSessionMockedStatic.close();
    }

    @Test
    @DisplayName("로그인안한 경우")
    void execptionNotLogin() {
        MockitoAnnotations.openMocks(this);

        User testUser = new User();

        when(userSessionMock.getLoggedUser()).thenReturn(null);
        when(tripDAOMock.findTripsByUser(testUser)).thenReturn(Arrays.asList(new Trip()));

        TripService tripService = new TripService();
        assertThrows(UserNotLoggedInException.class, () -> {
            tripService.getTripsByUser(testUser);
        });
    }

    @Test
    @DisplayName("로그인했는데 친구아닌 경우")
    void emplylistLoginNotFriend(){
        MockitoAnnotations.openMocks(this);

        User userWithNoFriend = new User();
        User loginUser = new User();

        when(userSessionMock.getLoggedUser()).thenReturn(loginUser);
        when(tripDAOMock.findTripsByUser(userWithNoFriend)).thenReturn(Arrays.asList(new Trip()));

        TripService tripService = new TripService();
        List<Trip> trips = tripService.getTripsByUser(userWithNoFriend);

        assertEquals(0, trips.size());
    }

    @Test
    @DisplayName("로그인했고 친구인 경우")
    void SomeListLoginWithFriend() {
        MockitoAnnotations.openMocks(this);

        User userWithFriends = new User();
        User loginUser = new User();
        userWithFriends.addFriend(loginUser);

        when(userSessionMock.getLoggedUser()).thenReturn(loginUser);
        when(tripDAOMock.findTripsByUser(userWithFriends)).thenReturn(Arrays.asList(new Trip()));

        TripService tripService = new TripService();
        List<Trip> trips = tripService.getTripsByUser(userWithFriends);

        assertEquals(1, trips.size());


    }
}
