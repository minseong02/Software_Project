package com.naver;
import javax.swing.*;
import java.awt.*;
import java.net.URL;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

/**
 * Java Swing application with two buttons and dialog messages.
 */
public class JavaNaver {

 public static void main(String[] args) {
        // 프레임 설정
        JFrame frame = new JFrame("Naver Static Map Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 800);  // 프레임의 크기 지정

        // 네이버 지도 API 사용
        try {
            // 클라이언트 ID 설정 (네이버 클라우드 플랫폼에서 발급받은 ID)
String clientId = "5bt0m1r7kk";
String clientSecret = "gxArtOeB8YTQrtpI5vNwZPPKaXSdcOANdDKRUhHX"; // 발급받은 클라이언트 시크릿
String mapUrl = "https://naveropenapi.apigw.ntruss.com/map-static/v2/raster?"
                + "w=700&h=800&center=127.1054328,37.3595963&level=16"
                + "&X-NCP-APIGW-API-KEY-ID=" + clientId
                + "&X-NCP-APIGW-API-KEY=" + clientSecret;


            // URL 객체 생성 및 이미지 다운로드
            URL url = new URL(mapUrl);
            BufferedImage image = ImageIO.read(url);  // URL에서 이미지 읽기
            JLabel label = new JLabel(new ImageIcon(image));  // 이미지를 아이콘으로 설정해 라벨 생성

            // 라벨을 프레임의 중앙에 추가
            frame.getContentPane().add(label, BorderLayout.CENTER);
        } catch (Exception e) {
            e.printStackTrace();  // 예외 발생 시 스택 추적 출력
        }

        // 프레임 보이기 설정
        frame.setVisible(true);
    }
}
