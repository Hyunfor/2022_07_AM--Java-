package com.KoreaIT.java.AM.controller;

import java.util.List;
import java.util.Scanner;

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

}
