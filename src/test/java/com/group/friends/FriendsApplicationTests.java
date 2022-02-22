package com.group.friends;

import com.group.friends.controller.MemberController;
import com.group.friends.model.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class FriendsApplicationTests {

	@Autowired
	MemberController memberController;

	@Test
	public void testMemberRegistration_success(){
		Member member = new Member();
		member.setName("dummy");
		ResponseEntity<Member> result = memberController.registerMember(member);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(result.getStatusCode(),HttpStatus.OK);
		Assertions.assertNotNull(result.getBody());
	}

	@Test
	public void testMemberRegistration_noSuccess(){
		Member member = new Member();
		member.setName(null);
		ResponseEntity<Member> result = memberController.registerMember(member);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(result.getStatusCode(), HttpStatus.BAD_REQUEST);
	}

	@Test
	public void testMemberUnRegistration_success(){
		Member member = new Member();
		member.setName("alreadyMember");
		memberController.registerMember(member);

		ResponseEntity<Member> result = memberController.unregisterMember(member);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(result.getStatusCode(),HttpStatus.OK);
		Assertions.assertNotNull(result.getBody());
	}

	@Test
	public void testMemberUnRegistration_noSuccess(){
		Member member = new Member();
		member.setName("notRegisteredMember");
		ResponseEntity<Member> result = memberController.unregisterMember(member);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(result.getStatusCode(),HttpStatus.UNPROCESSABLE_ENTITY);
	}
}
