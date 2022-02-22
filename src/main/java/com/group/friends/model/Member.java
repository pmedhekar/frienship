package com.group.friends.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;

@Getter
@Setter
@Entity
@ToString
public class Member {
    @Column(name = "name")
    String name;

    @Id
    @Column(name = "id")
    String id;
}
