package org.example.controller;
// 모든 컨트롤러의 부모 클래스
public class Controller {
    public void doAction(String cmd, String actionMethodName) {

    }
}

// 모든 컨트롤러(Article, Member)가 공통으로 가지는 형태
// doAction 은 명령어가 들어오면 어떤 기능을 실행할지 결정하는 함수