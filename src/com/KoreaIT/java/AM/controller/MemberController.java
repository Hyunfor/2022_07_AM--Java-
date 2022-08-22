package com.KoreaIT.java.AM.controller;

import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.AM.dto.Article;
import com.KoreaIT.java.AM.dto.Member;
import com.KoreaIT.java.AM.util.Util;

public class MemberController {

	private static List<Member> members;
	private Scanner sc;

	public MemberController(Scanner sc, List<Member> members) {
		this.sc = sc;
		this.members = members;
	}

	int lastMemberId = 0;

	public void doJoin() {
		String loginId = null;
		String loginPw = null;
		String loginPwC = null;

		int id = lastMemberId + 1;
		lastMemberId = id;
		String regDate = Util.getDateStr();
		while (true) {
			System.out.printf("아이디 : ");
			loginId = sc.nextLine();

			if (isJoinableLoginId(loginId) == false) { // 아이디 중복체크
				System.out.printf("%s 는(은) 이미 사용중인 아이디입니다\n", loginId);
				continue;
			}

			break;
		}

		while (true) {
			System.out.printf("비밀번호 : ");
			loginPw = sc.nextLine();
			System.out.printf("비밀번호 확인: ");
			loginPwC = sc.nextLine();

			if (loginPw.equals(loginPwC) == false) { // 비밀번호 확인
				System.out.println("비밀번호를 다시 입력해주세요");
				continue;
			}
			break;
		}

		System.out.printf("이름 : ");
		String name = sc.nextLine();

		Member member = new Member(id, regDate, loginId, loginPw, name);
		members.add(member);

		System.out.printf("%d번 회원이 생성되었습니다.\n", id);

	}
	
	private boolean isJoinableLoginId(String loginId) {
		int index = getMemberIndexByLoginId(loginId);

		if (index == -1) { // 중복되는 아이디를 찾지 못했을 경우
			return true;
		}

		return false;
	}

	private int getMemberIndexByLoginId(String loginId) {
		int i = 0;
		for (Member member : members) {

			if (member.loginId.equals(loginId)) {
				return i;
			}
			i++;
		}
		return -1;
	}

	public void memberList() {
		if (members.size() == 0) {
			System.out.println("가입자가 없습니다");
			return;
		}
		System.out.println("번호    |    회원	  |	  이름");
		for (int i = members.size() - 1; i >= 0; i--) { // 회원확인은 역순으로.
			Member member = members.get(i);
			System.out.printf("%4d    |    %s	|	%s\n", member.id, member.loginId, member.name);
		}
		
	}
	
	public void memberDetail(String cmd) {
		String[] cmdBits = cmd.split(" ");

		int id = Integer.parseInt(cmdBits[2]);

		Member foundMember = getMemberById(id);

		if (foundMember == null) {
			System.out.printf("%d번 존재하지 않는 회원입니다.\n", id);
			return;
		}

		System.out.printf("번호 : %d\n", foundMember.id);
		System.out.printf("아이디 : %s\n", foundMember.loginId);
		System.out.printf("이름 : %s\n", foundMember.name);
		
	}
	
	public void memberModify(String cmd) {
		String[] cmdBits = cmd.split(" ");

		int id = Integer.parseInt(cmdBits[2]);

		Member foundMember = getMemberById(id);

		if (foundMember == null) {
			System.out.printf("%d번 회원은 존재하지 않습니다.\n", id);
			return;
		}
		
		System.out.printf("새 이름 : ");
		String name = sc.nextLine();
		while (true) {
			System.out.printf("비밀번호 : ");
			String loginPw = sc.nextLine();
			System.out.printf("비밀번호 확인: ");
			String loginPwC = sc.nextLine();

			if (loginPw.equals(loginPwC) == false) { // 비밀번호 확인
				System.out.println("비밀번호를 다시 입력해주세요");
				continue;
			}
			break;
		}

		foundMember.name = name;

		System.out.printf("%d번 회원정보를 수정했습니다.\n", id);
		
	}


	private Member getMemberById(int id) {
		int index = getMemberIndexById(id);

		if (index != -1) {
			return members.get(index);
		}

		return null;
	}

	private int getMemberIndexById(int id) {
		int i = 0;
		for (Member member : members) {

			if (member.id == id) {
				return i;
			}
			i++;
		}
		return -1;
	}

	
	

}
