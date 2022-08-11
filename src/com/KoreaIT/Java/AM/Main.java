package com.KoreaIT.Java.AM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		System.out.println("== 프로그램 시작 ==");
		
		Scanner sc = new Scanner(System.in);
		
		int lastArticleId = 0; // 반복하면 안되니까 while 외부에.	
		
		List<Article> articles = new ArrayList<>(); // article만 저장하는 	저장소.	

		
		while (true) {
			System.out.println("명령어)");
			String cmd = sc.nextLine().trim(); // cmd - 키보드 커맨드 직접 입력
			// trim() 공백도 인식함.
			
			if(cmd.equals("exit")) {
				break;
			}
			if(cmd.length() == 0) { // 키보드 커맨드가 0인경우
				System.out.println("명령어를 입력해주세요.");
				continue;
			}
			if(cmd.equals("article write")) { // 글쓰기
				int id = lastArticleId + 1;
				lastArticleId = id;  // id에 덮어씌워서 1증가
				String regDate = Util.getDateStr();
				System.out.println("제목 : ");
				String title = sc.nextLine();
				System.out.println("내용 : ");
				String body = sc.nextLine();
				
				Article article = new Article(id, regDate, title, body);
				articles.add(article);
				
				System.out.printf("%d번 게시글이 생성되었습니다.\n", id);
				
//				lastArticleId ++;
//				System.out.printf("%s,%s\n", title, body); // 잘 들어갔는지 내용확인.
			} else if(cmd.equals("article list")) { // 글 리스트 확인
				if(articles.size() == 0) {
					System.out.println("게시글이 없습니다.");
					continue;
					}
				System.out.println("번호  |  제목  |  조회");
				for(int i = articles.size() - 1; i >= 0; i--) { // 게시판 글확인은 역순으로.	
					Article article = articles.get(i);
					System.out.printf("%4d  |  %s  |  %d\n", article.id, article.title, article.hit); // 숫자d ~칸을 확보하겠다.
				}
				
			} else if (cmd.startsWith("article detail ")){  // startsWith - ~~ 로 시작한다면.
				String[] cmdBits = cmd.split(" "); // 배열은 index를 부여가능.
				
				int id = Integer.parseInt(cmdBits[2]); // String을 Integer로 int로 변환.
				
				boolean found = false;
				Article foundArticle = null;
				
				for(int i = 0; i < articles.size(); i++) { // 게시판 글 순회하는 반복문.
					Article article = articles.get(i);  
					

					if(article.id == id) {
//						found = true;   // 원하는 id를 찾았을 경우에 true .
						foundArticle = article; // 존자하는 경우 article로 채워넣기.
						break;  // 원하는 id를 찾았을경우에 조건문 탈출.
					}
				}
				
				if(foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
					
				} else {
					
					foundArticle.increaseHit();
					
					System.out.printf("번호 : %d\n", foundArticle.id);
					System.out.printf("날짜 : %s\n", foundArticle.regDate); // 글을 쓴 날짜.
					System.out.printf("제목 : %s\n", foundArticle.title); 
					System.out.printf("내용 : %s\n", foundArticle.body);
					System.out.printf("조회 : %d\n", foundArticle.hit);
				}
				
				
			} else if (cmd.startsWith("article delete ")) { // 삭제하기
			
				String[] cmdBits = cmd.split(" ");

				int id = Integer.parseInt(cmdBits[2]);

				int foundIndex = -1; // index 기반으로 찾기 .음수로 설정해서 없다고 설정.

				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);

					if (article.id == id) {
						foundIndex = i; // 반복문을 돌려서 	있다면 i로 변환
						break;
					}
				}

				if (foundIndex  == -1) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}
				// size() => 3
				// index : 0,1,2
				// id : 1, 2, 3
				// 현재 게시물 id랑 index랑 불일치 - 해결.
				articles.remove(id - 1);
				System.out.printf("%d번 글을 삭제했습니다.\n", id);
				
			} else if(cmd.startsWith("article modify ")) { // 수정하기.
				
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
				System.out.println("새 제목 : ");
				String title = sc.nextLine();
				System.out.println("새 내용 : ");
				String body = sc.nextLine();
				
				foundArticle.title = title;
				foundArticle.body = body;
				
				System.out.printf("%d번 게시물을 수정했습니다.\n", id);
					
				} else {
					System.out.println("존재하지 않는 명령어 입니다.");
				}
			
					
			}
		
		sc.close();
		
		System.out.println("== 프로그램 끝 ==");

	}
		
}

class Article {	
	int id;
	String regDate;
	String title;
	String body;
	int hit;
	
	Article(int id, String regDate, String title, String body){
		this.id = id;
		this.regDate = regDate;
		this.title = title;
		this.body = body;
		this.hit = 0; // 최초로 글을 쓸때는 0	
	}
	public void increaseHit() { // detail 실행시 1씩 증가.	
		hit++;
	}

}
