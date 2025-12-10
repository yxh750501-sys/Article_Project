package org.example.dto;
// 회원 정보 저장용 클래스
public class Member extends Dto {

    private String loginId;
    private String password;
    private String name;

// 생성자 : 회원 정보를 넣어 객체를 생성한다.
    public Member(int id, String updateDate, String regDate, String loginId, String password, String name) {
        this.id = id;
        this.updateDate = updateDate;
        this.regDate = regDate;
        this.loginId = loginId;
        this.password = password;
        this.name = name;
    }

    //Getter & Setter 변수 접근을 할 수 있게 도와주는 메서드

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}