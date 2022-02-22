package com.group.friends.repository;

import com.group.friends.model.Member;
import org.springframework.data.repository.CrudRepository;

public interface MembersRepository extends CrudRepository<Member, String> {
    Member findByName(String name);
    Member deleteByName(String name);
}
