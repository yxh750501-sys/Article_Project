package org.example.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// 여러 곳에서 반복해서 쓰는 '현재 시간 가져오기' 기능을 분리.
public class Util {
    /**현재 날짜 및 시각 가져오는 함수(str)**/

    // 현재 날짜 및 시간을 "2025-01-01 12:12:12" 형태의 문자열로 반환
    public static String getNowStr() {

        LocalDateTime now = LocalDateTime.now(); // 현재 시스템의 날짜 / 시간 가져오기
        String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        // 보기 좋은 형식으로 바꾸기
        
        return formatedNow;
    }
}