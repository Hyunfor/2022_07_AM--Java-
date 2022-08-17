package com.KoreaIT.java.AM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	private static List<Article> articles;

	static {
		articles = new ArrayList<>();
	}
	
	public static void main(String[] args) {
		System.out.println("==프로그램 시작==");

		makeTestData();
		
		Scanner sc = new Scanner(System.in); 
		
		int lastArticleId = 3; 
		
		while(true) { // 명령어 무한 반복
			System.out.printf("명령어) ");
			String cmd = sc.nextLine().trim(); // cmd는 사용자가 직접 입력하는것. - 키보드
			// trim()은 공백도 인식함.
			
			if(cmd.equals("exit")) { // 프로그램 종료
				break;
			}
			if(cmd.length() == 0) { // 키보드 커맨드가 0인 경우.
				System.out.println("명령어를 입력해주세요.");
				continue;
			}
			
			if(cmd.equals("article write")) {
				int id = lastArticleId + 1;
				lastArticleId = id; // id에 덮어씌워 1씩 증가
				String regDate = Util.getDateStr();
				System.out.println("제목 : ");
				String title = sc.nextLine();
				System.out.println("내용 : ");
				String body = sc.nextLine();
				
//				System.out.printf("%s,%s\n", title, body);
				Article article = new Article(id, regDate, title, body);
				articles.add(article);
				
				
				System.out.printf("%d번 글이 생성되었습니다.\n", id);
			} else if(cmd.equals("article list")) {
				
				if(articles.size() == 0) {
					System.out.println("게시글이 없습니다."); 
					continue;
				}
				System.out.println("번호  |  제목   |   조회");
				for(int i = articles.size() - 1; i >= 0; i--) { // 게시판 글확인은 역순으로.
					Article article = articles.get(i);	
					System.out.printf("%4d   |   %s   |   %d\n", article.id, article.title, article.hit); // 숫자 ~~d% 몇칸 확보.
				}
				
			} else if (cmd.startsWith("article detail ")) { // startsWith - ~~ 로 시작한다면.
				String[] cmdBits = cmd.split(" ");
				
				int id = Integer.parseInt(cmdBits[2]); // "2" - > 2 . String을 int로 변환
				
				Article foundArticle = null;
				
				for(int i = 0; i < articles.size(); i++) { // 0~4
					Article article = articles.get(i);
					
					if(article.id == id) {
						foundArticle = article;
						break;
					}
				}
				
				if(foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				} 
					foundArticle.increaseHit();
					System.out.printf("번호 : %d\n", foundArticle.id);
					System.out.printf("날짜 : %s\n", foundArticle.regDate);
					System.out.printf("제목 : %s\n", foundArticle.title);
					System.out.printf("내용 : %s\n", foundArticle.body);
					System.out.printf("조회 : %d\n", foundArticle.hit);
					
			} else if(cmd.startsWith("article modify ")){ // 게시글 수정.
				String[] cmdBits = cmd.split(" ");
				
				int id = Integer.parseInt(cmdBits[2]);
				
				Article foundArticle = null;
				
				for(int i = 0; i < articles.size(); i++) { 
					Article article = articles.get(i);
					
					if(article.id == id) {
						foundArticle = article;
						break;
					}
				}
				
				if(foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}
				
				System.out.printf("새 제목 : ");
				String title = sc.nextLine();
				System.out.printf("새 내용 : ");
				String body = sc.nextLine();
				
				foundArticle.title = title;
				foundArticle.body = body;
				
				System.out.printf("%d번 글을 수정했습니다.\n", id);
				
				
			} else if(cmd.startsWith("article delete ")) {
				String[] cmdBits = cmd.split(" ");
				
				int id = Integer.parseInt(cmdBits[2]);
				
				int foundIndex = -1;
				
				for(int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);
					
					if(article.id == id) {
						foundIndex = i;
						break;
					}
				}
				
				if(foundIndex == -1) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}
				//size() = > 3
				// index : 0,1,2
				// id : 1,2,3
				
				articles.remove(foundIndex);
				System.out.printf("%d번 글을 삭제했습니다.\n", id);
				
				
			} else {
				System.out.println("존재하지 않는 명령어입니다.");
			}
		}

		sc.close();

		System.out.println("==프로그램 끝==");
	}
	
	private static void makeTestData() {
		System.out.println("테스트를 위한 데이터를 생성합니다.");

		articles.add(new Article(1, Util.getDateStr(), "제목1", "내용1", 10));
		articles.add(new Article(2, Util.getDateStr(), "제목2", "내용2", 20));
		articles.add(new Article(3, Util.getDateStr(), "제목3", "내용3", 30));
	}
	
}

class Article{
	int id;
	String regDate;
	String title;
	String body;
	int hit;
	
	public Article(int id, String regDate, String title, String body) {
		this(id, regDate, title, body, 0);
	}
	
	public Article(int id, String regDate, String title, String body, int hit){
		this.id = id;
		this.regDate = regDate;
		this.title = title;
		this.body = body;
		this.hit = hit;
	}
	
	public void increaseHit() {
		hit++;
	}
}
