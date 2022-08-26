package com.KoreaIT.java.AM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.AM.controller.ArticleController;
import com.KoreaIT.java.AM.controller.MemberController;
import com.KoreaIT.java.AM.dto.Article;
import com.KoreaIT.java.AM.dto.Member;

public class App {
	
	private static List<Article> articles;
	private static List<Member> members;
	public static Member loginedMember = null;

	public App() {
		articles = new ArrayList<>();
		members = new ArrayList<>();
	}

	public void run() {
		System.out.println("==프로그램 시작==");

		Scanner sc = new Scanner(System.in);
		
		MemberController memberController = new MemberController(sc, members);
		ArticleController articleController = new ArticleController(sc, articles);

		articleController.makeTestData();

		while (true) { // 명령어 무한 반복
			System.out.printf("명령어) ");
			String cmd = sc.nextLine().trim(); // cmd는 사용자가 직접 입력하는것. - 키보드
			// trim()은 공백도 인식함.
			
			if (cmd.equals("exit")) { // 프로그램 종료
				break;
			}
			if (cmd.length() == 0) { // 키보드 커맨드가 0인 경우.
				System.out.println("명령어를 입력해주세요");
				continue;
			} if (cmd.equals("member join")) {	
				
				memberController.doJoin();

			} else if(cmd.startsWith("member login ")) { // 로그인
				
				memberController.dologin(cmd);
				
			} else if(cmd.startsWith("member logout ")) { // 로그아웃
				
				memberController.dologout(cmd);
				
			} else if(cmd.equals("member list")) { // 회원목록 확인
				
				memberController.memberList();
				
			} else if(cmd.startsWith("member detail ")) { // 회원정보 조회
				
				memberController.memberDetail(cmd);
				
			} else if (cmd.startsWith("member modify ")) { // 회원정보 수정.
				
				memberController.memberModify(cmd);
				
			} else if (cmd.startsWith("member delete ")) { // 회원탈퇴
				
				memberController.memberDelete(cmd);
				
			} else if (cmd.equals("article write")) {
				
				articleController.doWrite();
				
			} else if (cmd.equals("article list")) {
				
				articleController.showList();
				
			} else if (cmd.startsWith("article detail ")) { // startsWith - ~~ 로 시작한다면.
				
				articleController.showDetail(cmd);
				
			} else if (cmd.startsWith("article modify ")) { // 게시글 수정.
				
				articleController.doModify(cmd);
				
			} else if (cmd.startsWith("article delete ")) {
				
				articleController.doDelete(cmd);
				
			} else {
				System.out.println("존재하지 않는 명령어입니다");
			}

		}

		sc.close();

		System.out.println("==프로그램 끝==");
	}


}