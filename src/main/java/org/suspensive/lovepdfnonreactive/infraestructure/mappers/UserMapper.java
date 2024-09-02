package org.suspensive.lovepdfnonreactive.infraestructure.mappers;

import org.suspensive.lovepdfnonreactive.domain.models.User;
import org.suspensive.lovepdfnonreactive.infraestructure.documents.UserDocument;

public class UserMapper {

    private UserMapper(){
        throw new IllegalStateException("Utility class");
    }

    public static UserDocument mapToDocument(User user){
        return new UserDocument(user.getId(), user.getUsername(), user.getPassword(), user.getEmail(), user.getRoles(), user.isStatus());
    }

    public static User mapToModel(UserDocument userDocument){
        return new User(userDocument.getId(), userDocument.getUsername(), userDocument.getPassword(), userDocument.getEmail(), userDocument.getRoles(), userDocument.isStatus());
    }
}
