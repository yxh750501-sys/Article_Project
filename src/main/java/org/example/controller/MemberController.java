package org.example.controller;

import org.example.dto.Member;
import org.example.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberController extends Controller {  // 회원 관련 기능을 담당하는 컨트롤러

    private Scanner sc;           // 입력 받기 위한 scanner
    private List<Member> members; // 회원 객체들을 저장할 리스트
    private String cmd;           // 현재 명령어 저장

    private int lastMemberId = 3; // 테스트용 회원 3명 존재

    public MemberController(Scanner sc) {
        this.sc = sc;
        members = new ArrayList<>();
    }

    public void doAction(String cmd, String actionMethodName) { // App에서 실제 기능 실행을 요청하면 이 메서드가 판단해서 실행.
        this.cmd = cmd;

        switch (actionMethodName) {
            case "join":
                doJoin();
                break;
            default:
                System.out.println("Invalid action method");
                break;
        }
    }

    private void doJoin() {   //  회원가입 기능
        System.out.println("==회원 가입==");
        int id = lastMemberId + 1;  // 새 회원 id
        String loginId = null;
        while (true) {   // 아이디 입력, 중복 체크
            System.out.print("로그인 아이디 : ");
            loginId = sc.nextLine().trim();
            if (isJoinableLoginId(loginId) == false) {
                System.out.println("이미 사용중인 loginId");
                continue;
            }
            break;
        }
        String password = null; // 비밀번호 입력, 비밀번호 확인 체크
        while (true) {
            System.out.print("비밀번호 : ");
            password = sc.nextLine().trim();
            System.out.print("비밀번호 확인: ");
            String passwordConfirm = sc.nextLine().trim();
            if (password.equals(passwordConfirm) == false) {
                System.out.println("비번 확인해");
                continue;
            }
            break;
        }
        System.out.print("이름 : ");   // 이름 입력
        String name = sc.nextLine().trim(); // 등록 날짜와 수정 날짜 기록
        String regDate = Util.getNowStr();
        String updateDate = Util.getNowStr();

        Member member = new Member(id, regDate, updateDate, loginId, password, name);
        members.add(member);  // 새 회원 객체 생성

        System.out.println(id + "번 회원이 가입 되었습니다.");
        lastMemberId++;
    }

    private boolean isJoinableLoginId(String loginId) {
        for (Member member : members) {
            if (member.getLoginId().equals(loginId)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 회원 테스트 데이터 생성
     **/
    public void makeTestData() {
        System.out.println("==회원 테스트 데이터 생성==");
        members.add(new Member(1, Util.getNowStr(), Util.getNowStr(), "test1", "test1", "회원1"));
        members.add(new Member(2, Util.getNowStr(), Util.getNowStr(), "test2", "test2", "회원2"));
        members.add(new Member(3, Util.getNowStr(), Util.getNowStr(), "test3", "test3", "회원3"));
    }
}