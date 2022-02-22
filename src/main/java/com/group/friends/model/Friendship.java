package com.group.friends.model;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@ToString
public class Friendship {

    @Id
    @Column(name = "id")
    String id;

    @Column(name = "name1")
    String name1;

    @Column(name = "friendId1")
    String friendId1;

    @Column(name = "name2")
    String name2;

    @Column(name = "friendId2")
    String friendId2;

    @Column(name = "circle")
    int circle;
}
