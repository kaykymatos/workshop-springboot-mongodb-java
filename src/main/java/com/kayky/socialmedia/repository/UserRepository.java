package com.kayky.socialmedia.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.kayky.socialmedia.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
