package org.suspensive.lovepdfnonreactive.domain.ports.output;

import org.suspensive.lovepdfnonreactive.domain.models.User;
import org.suspensive.lovepdfnonreactive.domain.models.exceptions.UserNotFoundException;

public interface UserPersistencePort {
    User findUserByEmail(String email) throws UserNotFoundException;
    User saveUser(User user);
}
