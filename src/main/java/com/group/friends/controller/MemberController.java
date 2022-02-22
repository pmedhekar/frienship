package com.group.friends.controller;

import com.group.friends.model.Member;
import com.group.friends.repository.MembersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class MemberController {

    @Autowired
    private MembersRepository membersRepository;

    @PostMapping("/register")
    public ResponseEntity registerMember(@RequestBody Member member) {
        if(member == null || !StringUtils.hasText(member.getName())) {
            return ResponseEntity.badRequest().body("Member Name Required for registration");
        }
        Member result = membersRepository.findByName(member.getName());
        if(result != null) {
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.ok(addNewMember(member));
    }

    @DeleteMapping("/unregister")
    public ResponseEntity unregisterMember(@RequestBody Member member){
        if(member == null || !StringUtils.hasText(member.getName())) {
            return ResponseEntity.badRequest().body("Member Name Required for un-registration");
        }

        Member result = membersRepository.findByName(member.getName());
        if(result != null) {
            deleteMember(result);
            return ResponseEntity.ok("Member with name "+member.getName() +" got un-registered successfully");
        }

        return ResponseEntity.unprocessableEntity().body("Un-Registration Failed");
    }


    private Member addNewMember(Member member) {
        member.setId(UUID.randomUUID().toString());
        return membersRepository.save(member);
    }

    private void deleteMember(Member member) {
         membersRepository.deleteById(member.getId().toString());
    }
}
