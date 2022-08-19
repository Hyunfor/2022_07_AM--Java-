package com.KoreaIT.java.AM.controller;

import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.AM.dto.Article;
import com.KoreaIT.java.AM.util.Util;

public class ArticleController {

	private static List<Article> articles;
	private Scanner sc;

	public ArticleController(Scanner sc, List<Article> articles) {
		this.sc = sc;
		this.articles = articles;
	}
	
	int lastArticleId = 3;

	public void doWrite() {
		int id = lastArticleId + 1; // id에 덮어씌워 1씩 증가
		lastArticleId = id;
		String regDate = Util.getDateStr();
		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();

		Article article = new Article(id, regDate, title, body);
		articles.add(article);

		System.out.printf("%d번 글이 생성되었습니다.\n", id);

	}

	public void showList() {
		if (articles.size() == 0) {
			System.out.println("게시글이 없습니다");
			return;
		}
		System.out.println("번호    |    제목    |    조회");
		for (int i = articles.size() - 1; i >= 0; i--) { // 게시판 글확인은 역순으로.
			Article article = articles.get(i);
			System.out.printf("%4d    |    %s    |    %d\n", article.id, article.title, article.hit);
		}

	}

	public void showDetail(String cmd) {
		String[] cmdBits = cmd.split(" ");

		int id = Integer.parseInt(cmdBits[2]);

		Article foundArticle = getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
			return;
		}

		foundArticle.increaseHit();

		System.out.printf("번호 : %d\n", foundArticle.id);
		System.out.printf("날짜 : %s\n", foundArticle.regDate);
		System.out.printf("제목 : %s\n", foundArticle.title);
		System.out.printf("내용 : %s\n", foundArticle.body);
		System.out.printf("조회 : %d\n", foundArticle.hit);

	}

	public void doModify(String cmd) {
		String[] cmdBits = cmd.split(" ");

		int id = Integer.parseInt(cmdBits[2]);

		Article foundArticle = getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
			return;
		}
		System.out.printf("새 제목 : ");
		String title = sc.nextLine();
		System.out.printf("새 내용 : ");
		String body = sc.nextLine();

		foundArticle.title = title;
		foundArticle.body = body;

		System.out.printf("%d번 글을 수정했습니다.\n", id);

	}

	public void doDelete(String cmd) {
		String[] cmdBits = cmd.split(" ");

		int id = Integer.parseInt(cmdBits[2]); // "2" - > 2 . String을 int로 변환

		int foundIndex = getArticleIndexById(id);

		if (foundIndex == -1) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
			return;
		}

		articles.remove(foundIndex);
		System.out.printf("%d번 글을 삭제했습니다.\n", id);

	}

	private static int getArticleIndexById(int id) { // 게시글 삭제 중복 코드 제거 
		int i = 0;
		for (Article article : articles) {

			if (article.id == id) {
				return i;
			}
			i++;
		}
		return -1;
	}

	private static Article getArticleById(int id) {
		int index = getArticleIndexById(id);

		if (index != -1) {
			return articles.get(index);
		}

		return null;
	}

	public void makeTestData() {
		System.out.println("테스트를 위한 데이터를 생성합니다.");

		articles.add(new Article(1, Util.getDateStr(), "제목1", "내용1", 10));
		articles.add(new Article(2, Util.getDateStr(), "제목2", "내용2", 20));
		articles.add(new Article(3, Util.getDateStr(), "제목3", "내용3", 30));
	}
	
}
