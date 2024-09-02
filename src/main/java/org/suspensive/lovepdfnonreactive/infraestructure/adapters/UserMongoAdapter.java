package org.suspensive.lovepdfnonreactive.infraestructure.adapters;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.suspensive.lovepdfnonreactive.domain.models.User;
import org.suspensive.lovepdfnonreactive.domain.models.exceptions.UserNotFoundException;
import org.suspensive.lovepdfnonreactive.domain.ports.output.UserPersistencePort;
import org.suspensive.lovepdfnonreactive.infraestructure.mappers.UserMapper;
import org.suspensive.lovepdfnonreactive.infraestructure.repositories.UserMongoRepository;

@Service
@Transactional
public class UserMongoAdapter implements UserPersistencePort {

    private final UserMongoRepository userRepository;

    public UserMongoAdapter(UserMongoRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findUserByEmail(String email) throws UserNotFoundException {
        return UserMapper.mapToModel(userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with email: " + email+"does not exist")));
    }

    @Override
    public User saveUser(User user) {
        return UserMapper.mapToModel(userRepository.save(UserMapper.mapToDocument(user)));
    }
}
