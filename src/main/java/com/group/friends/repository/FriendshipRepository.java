package com.group.friends.repository;

import com.group.friends.model.Friendship;
import org.springframework.data.repository.CrudRepository;

public interface FriendshipRepository extends CrudRepository<Friendship,String> {
}
