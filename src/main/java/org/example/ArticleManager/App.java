package org.example.ArticleManager;

import org.example.controller.ArticleController;
import org.example.controller.Controller;
import org.example.controller.MemberController;

import java.util.Scanner;

public class App {
    public void run() {
        Scanner sc = new Scanner(System.in); // 키보드로 부터 입력을 받을 준비

        System.out.println("==프로그램 시작=="); // 시작 문구 출력

        MemberController memberController = new MemberController(sc); // 회원 컨드롤러 생성
        ArticleController articleController = new ArticleController(sc); // 게시글 컨트롤러 생성

        articleController.makeTestData();
        memberController.makeTestData();

        Controller controller = null; // 어떤 컨트롤러를 사용할지 담을 변수

        while (true) {
            System.out.print("명령어 ) ");
            String cmd = sc.nextLine().trim(); // 무한 반복하면서 명령어를 계속 받음

            if (cmd.equals("exit")) {
                break;                         // exit 입력 시 프로그램 종료.
            } else if (cmd.length() == 0) {
                System.out.println("명령어 입력하세요");
                continue;                      // 아무 것도 입력하지 않았을 시 다시 입력을 요구
            }

            String[] cmdBits = cmd.split(" ");

            String controllerName = cmdBits[0]; // 첫 단어(article or member)를 꺼냄

            if (cmdBits.length == 1) {
                System.out.println("명령어 확인 필요");
                continue;                       // 첫 단어만 입력한 경우 실행할 기능이 없으니
                                                // "명령어 확인 필요" 출력
            }

            String actionMethodName = cmdBits[1];   // 두 번째 단어 꺼냄(write, list, datail ~)

            if (controllerName.equals("article")) {
                controller = articleController;
            } else if (controllerName.equals("member")) {
                controller = memberController;
            } else {
                System.out.println("지원하지 않는 기능");
                continue;
            }                                       // 명령어 앞부분으로 어떤 컨트롤러인지 결정
                                                    // 이 외엔 지원하지 않는 기능

            controller.doAction(cmd, actionMethodName); // 명령어와 기능 이름을 실제 컨트롤러에 전달하여 실행

        }
        System.out.println("==프로그램 끝==");
        sc.close();
    }
}