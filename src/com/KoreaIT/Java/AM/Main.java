package com.KoreaIT.Java.AM;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		System.out.println("== 프로그램 시작 ==");
		
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			System.out.println("명령어)");
			String cmd = sc.nextLine(); // cmd - 키보드 커맨드 직접 입력
			
			if(cmd.equals("exit")) {
				break;
			}
			if(cmd.length() == 0) { // 키보드 커맨드가 0인경우
				System.out.println("명령어를 입력해주세요.");
				continue;
			}
			if(cmd.length() == 1) {
				System.out.println("게시글이 생성되었습니다.");
				continue;
			}
			if(cmd.equals("article list")) {
				System.out.println("게시글이 없습니다.");
			} else {
					System.out.println("존재하지 않는 명령어 입니다.");
			}
		}
		
		sc.close();
		
		System.out.println("== 프로그램 끝 ==");

	}
		
}
