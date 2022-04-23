package com.bestreviewer.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import com.bestreviewer.tripservicekata.exception.UserNotLoggedInException;
import com.bestreviewer.tripservicekata.user.User;
import com.bestreviewer.tripservicekata.user.UserSession;

public class TripService {

	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
		User loggedUser = getLoggedUser();
		return _getTripsByUser(user, loggedUser);
	}

	public List<Trip> getTripsByUser(User user, User loggedUser) throws UserNotLoggedInException {
		return _getTripsByUser(user, loggedUser);
	}

	private List<Trip> _getTripsByUser(User user, User loggedUser) {
		List<Trip> tripList = new ArrayList<Trip>();
		boolean isFriend = false;
		if (loggedUser != null) {
			for (User friend : user.getFriends()) {
				if (friend.equals(loggedUser)) {
					isFriend = true;
					break;
				}
			}
			if (isFriend) {
				tripList = TripBy(user);
			}
			return tripList;
		} else {
			throw new UserNotLoggedInException();
		}
	}

	protected User getLoggedUser() {
		return UserSession.getInstance().getLoggedUser();
	}

	protected List<Trip> TripBy(User user) {
		return TripDAO.findTripsByUser(user);
	}

}
