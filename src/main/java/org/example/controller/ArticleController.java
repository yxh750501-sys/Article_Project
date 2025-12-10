package org.example.controller;

import org.example.dto.Article;
import org.example.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArticleController extends Controller {  // controller를 상속 받아 실제 기능 구현
    private Scanner sc;               // 입력 받기 위해 사용
    private  List<Article> articles;  // 게시글 저장 목록
    private String cmd;               // 명령어 전체 저장

    private int lastArticleId = 3;    // 마지막 게시글 번호

    public ArticleController(Scanner sc) {
        this.sc = sc;
        articles = new ArrayList<>();
    }

    public void doAction(String cmd, String actionMethodName) { // App에서 전달된 action에 따라 어떤 기능을 실행할지 결정
        this.cmd = cmd;  // 명령어 전체 저장

        switch (actionMethodName) {
            case "write":
                doWrite();
                break;
            case "list":
                showList();
                break;
            case "detail":
                showDetail();
                break;
            case "delete":
                doDelete();
                break;
            case "modify":
                doModify();
                break;
            default:
                System.out.println("Invalid action method");
                break;
        }
    }   // app에서 받은 ActionMethodName을 보고 그에 맞는 함수 실행 (예 : write, list, detail 등등)

    private void doWrite() {
        System.out.println("==게시글 작성==");
        int id = lastArticleId + 1; // 새 게시글 번호 = 기존 마지막 번호 + 1
        System.out.print("제목 : "); // 제목 입력 받기
        String title = sc.nextLine().trim();
        System.out.print("내용 : ");// 내용 입력 받기;
        String body = sc.nextLine().trim();
        String regDate = Util.getNowStr();// 작성 시간, 수정 시간 현재 시간으로 변경
        String updateDate = Util.getNowStr();

        Article article = new Article(id, regDate, updateDate, title, body); // 새 게시글 객체 생성
        articles.add(article); // 리스트에 저장

        System.out.println(id + "번 글이 작성되었습니다.");
        lastArticleId++; // 마지막 글 번호 증가
    }

    private void showList() {  // 게시글 목록 보여주는 기능
        System.out.println("==게시글 목록==");
        if (articles.size() == 0) {
            System.out.println("아무것도 없음");
            return;  // 게시글이 없을 경우 "아무것도 없음"
        }

        String searchKeyword = cmd.substring("article list".length()).trim(); // 전체 게시글 출력 예정

        List<Article> forPrintArticles = articles;

        if (searchKeyword.length() > 0) { // 검색어가 있는 경우
            System.out.println("검색어 : " + searchKeyword);
            forPrintArticles = new ArrayList<>();  // 검색된 결과를 담을 빈 리스트 생성

            for (Article article : articles) {  // 제목에 검색어가 포함된 게시글만 추리기
                if (article.getTitle().contains(searchKeyword)) {
                    forPrintArticles.add(article);
                }
            }
            if (forPrintArticles.size() == 0) {
                System.out.println("검색 결과 없음");
                return; // 검색 결과가 없다면 안내 후 종료
            }
        }

        System.out.println("   번호  /       날짜       /       제목     /   내용  ");
        for (int i = forPrintArticles.size() - 1; i >= 0; i--) { // 최신 글부터 출력하려고 역순으로 출력
            Article article = forPrintArticles.get(i);
            if (Util.getNowStr().split(" ")[0].equals(article.getRegDate().split(" ")[0])) {
                System.out.printf("   %d     /    %s          /    %s     /     %s   \n", article.getId(), article.getRegDate().split(" ")[1], article.getTitle(), article.getBody());
            } else {
                System.out.printf("   %d     /    %s          /    %s     /     %s   \n", article.getId(), article.getRegDate().split(" ")[0], article.getTitle(), article.getBody());
            } // 오늘 작성된 글이면 시간만, 아니면 날짜만 출력

        }
    }

    private void showDetail() {   // 게시글 상세보기 기능
        System.out.println("==게시글 상세보기==");

        int id = Integer.parseInt(cmd.split(" ")[2]); // 명령어에서 숫자 부분 꺼냄

        Article foundArticle = getArticleById(id); // 해당 id의 게시글을 찾음

        if (foundArticle == null) {
            System.out.println("해당 게시글은 없습니다");
            return; // 없으면 "해당 게시글은 없습니다" 후 종료
        }
        System.out.println("번호 : " + foundArticle.getId());
        System.out.println("작성날짜 : " + foundArticle.getRegDate());
        System.out.println("수정날짜 : " + foundArticle.getUpdateDate());
        System.out.println("제목 : " + foundArticle.getTitle());
        System.out.println("내용 : " + foundArticle.getBody());     // 있다면 모든 정보 출력
    }

    private void doDelete() {      // 게시글 삭제 기능
        System.out.println("==게시글 삭제==");

        int id = Integer.parseInt(cmd.split(" ")[2]); // 삭제할 글의 번호 추출

        Article foundArticle = getArticleById(id);

        if (foundArticle == null) {
            System.out.println("해당 게시글은 없습니다");
            return;
        }
        articles.remove(foundArticle);
        System.out.println(id + "번 게시글이 삭제되었습니다");
    }

    private void doModify() {  // 게시글 수정 기능
        System.out.println("==게시글 수정==");

        int id = Integer.parseInt(cmd.split(" ")[2]);

        Article foundArticle = getArticleById(id);

        if (foundArticle == null) {
            System.out.println("해당 게시글은 없습니다");
            return;
        }
        System.out.println("기존 title : " + foundArticle.getTitle());
        System.out.println("기존 body : " + foundArticle.getBody());   // 기존 내용/제목 보여주기
        System.out.print("새 제목 : "); // 세 제목 입력
        String newTitle = sc.nextLine().trim();
        System.out.print("새 내용 : "); // 세 내용 입력
        String newBody = sc.nextLine().trim();

        foundArticle.setTitle(newTitle);
        foundArticle.setBody(newBody);

        foundArticle.setUpdateDate(Util.getNowStr());  // 변경적용

        System.out.println(id + "번 게시글이 수정되었습니다");
    }

    private Article getArticleById(int id) {   // id로 게시글 찾는 함수
        for (Article article : articles) {
            if (article.getId() == id) {
                return article;
            }
        }
        return null;    // 못 찾으면 null
    }

    /**
     * 게시글 테스트 데이터 생성
     **/
    public void makeTestData() {
        System.out.println("==게시글 테스트 데이터 생성==");   // 테스트용 게시글 3개 만들기
        articles.add(new Article(1, "2025-12-07 12:12:12", "2025-12-07 12:12:12", "제목 123", "내용 1"));
        articles.add(new Article(2, Util.getNowStr(), Util.getNowStr(), "제목 23", "내용 2"));
        articles.add(new Article(3, Util.getNowStr(), Util.getNowStr(), "제목 1234", "내용 3"));
    }
}