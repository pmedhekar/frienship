package com.group.friends.controller;

import com.group.friends.model.Friendship;
import com.group.friends.model.Member;
import com.group.friends.repository.FriendshipRepository;
import com.group.friends.repository.MembersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class FriendShipController {

    private List<String> members = new ArrayList<>(Arrays.asList("Prathamesh","Priyanka","Amir","Yaron"));

    private  Map<String, Map<Integer,List<String>>> friendShipMap = new HashMap<>();

    @Autowired
    private FriendshipRepository friendshipRepository;

    @Autowired
    private MembersRepository membersRepository;

    @PostMapping("/makeFriendship")
    public ResponseEntity makeAFriendShip(@RequestBody Friendship friendship) {
        /*if(!StringUtils.hasText(friendship.getFriendId1())
                || !StringUtils.hasText(friendship.getFriendId2())) {
            return ResponseEntity.badRequest().body("Friendship not created, as is 2 unique Ids");
        }
        if(friendship.getFriendId1().equals(friendship.getFriendId2())) {
            return ResponseEntity.badRequest().body("Friendship not created, as both Ids are same");
        }
        Optional<Member> member1 = membersRepository.findById(friendship.getFriendId1().toString());
        Optional<Member> member2 = membersRepository.findById(friendship.getFriendId2().toString());

        if(!member1.isPresent() || !member2.isPresent()) {
            return ResponseEntity.unprocessableEntity().body("Friendship not created, as either are not members");
        }*/

        createFriendShip("Prathamesh", "Priyanka");
        createFriendShip("Prathamesh", "Amir");
        return  ResponseEntity.ok().body("New FriendShip Created Between");
    }


    private void createFriendShip(String friend1, String friend2) {
        if(!members.contains(friend1) || !members.contains(friend2))
            return;

            if(friendShipMap.containsKey(friend1)) {
                Map<Integer,List<String>> level1Friends = friendShipMap.get(friend1);
                List<String> friends = level1Friends.get(1);
                friends.add(friend2);
                if(friendShipMap.get(friend2) == null) {
                   friendShipMap.put(friend2,createFriendshipForFriend1(1,friend1));
                }
                updateFriendShip(2,friend2, friends);
            } else {
                Map<Integer,List<String>> levelOfFriends = createFriendshipForFriend1(1,friend2);
                friendShipMap.put(friend1,levelOfFriends);
            }

            if(friendShipMap.containsKey(friend2)) {

            } else {
                Map<Integer,List<String>> levelOfFriends = createFriendshipForFriend1(1,friend1);
                friendShipMap.put(friend2,levelOfFriends);
            }

            System.out.println("");
       /* printFriendShip(friend1,1);
        printFriendShip(friend2,1);
        printFriendShip(friend1,2);*/

    }

    private Map<Integer,List<String>> createFriendshipForFriend1(Integer level, String friendName) {
       Map<Integer,List<String>> levelOfFriends = new HashMap<>();

       List<String> friends = new ArrayList<>();
       friends.add(friendName);

       levelOfFriends.put(level,friends);

       return levelOfFriends;
    }

    private void printFriendShip(String name, int level) {
        Map<Integer,List<String>> printFriends = friendShipMap.get(name);
        System.out.println(name+" 's Friends =>");

        List<String> friendsToPrint = printFriends.get(level);

        for(String each : friendsToPrint ) {
            System.out.println(each);
        }

    }

    private void updateFriendShip(Integer level, String friendName, List<String> currentFriends) {
        for(String each: currentFriends){
            Map<Integer,List<String>> levelFriends  = friendShipMap.get(each);

            List<String> nextLevelFriends = levelFriends.get(level - 1);
            nextLevelFriends.add(friendName);
            levelFriends.put(level,nextLevelFriends);
        }
    }
}
