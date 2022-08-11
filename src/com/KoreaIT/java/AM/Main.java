package com.KoreaIT.java.AM;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("==프로그램 시작==");

		Scanner sc = new Scanner(System.in);
		
		int lastArticleId = 0;
		
		while(true) { // 명령어 무한 반복
			System.out.printf("명령어) ");
			String cmd = sc.nextLine(); // cmd는 사용자가 직접 입력하는것. - 키보드
			
			if(cmd.equals("exit")) { // 탈출구
				break;
			}
			if(cmd.length() == 0) { // 키보드 커맨드가 0인 경우.
				System.out.println("명령어를 입력해주세요.");
				continue;
			}
			
			if(cmd.equals("article write")) {
				int id = lastArticleId + 1;
				lastArticleId = id;
				System.out.println("제목 : ");
				String title = sc.nextLine();
				System.out.println("내용 : ");
				String body = sc.nextLine();
				
				System.out.printf("%s,%s\n", title, body);
				
				System.out.printf("%d번 글이 생성되었습니다.\n", id);
			} else if(cmd.equals("article list")) {
				System.out.println("게시글이 없습니다.");
			} else {
				System.out.println("존재하지 않는 명령어입니다.");
			}
		}

		sc.close();

		System.out.println("==프로그램 끝==");
	}
}
