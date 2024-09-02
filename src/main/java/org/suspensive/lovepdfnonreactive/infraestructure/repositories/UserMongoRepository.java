package org.suspensive.lovepdfnonreactive.infraestructure.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.suspensive.lovepdfnonreactive.infraestructure.documents.UserDocument;

import java.util.Optional;

public interface UserMongoRepository extends MongoRepository<UserDocument, String> {
    Optional<UserDocument> findByEmail(String email);
}
