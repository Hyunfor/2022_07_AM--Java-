package com.KoreaIT.java.AM;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("==프로그램 시작==");

		Scanner sc = new Scanner(System.in);
		
		while(true) { // 명령어 무한 반복
			System.out.printf("명령어) ");
			String cmd = sc.nextLine(); // cmd는 사용자가 직접 입력하는것. - 키보드
			
			if(cmd.equals("exit")) { // 탈출구
				break;
			}
		}

		sc.close();

		System.out.println("==프로그램 끝==");
	}
}
