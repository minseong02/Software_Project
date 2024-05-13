package com.naver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class JavaNaver {

    private static int zoomLevel = 16;
    private static JLabel label;
    private static String latitude;
    private static String longitude;
    private static Point markerPosition = new Point(350, 400); // Approximate center position for simplicity

    public static void main(String[] args) {
        JFrame frame = new JFrame("Naver Static Map Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 800);

        label = new JLabel();
        label.setBounds(0, 0, 700, 800);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(700, 800));
        layeredPane.add(label, JLayeredPane.DEFAULT_LAYER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton zoomInButton = new JButton("Zoom In");
        JButton zoomOutButton = new JButton("Zoom Out");
        buttonPanel.add(zoomInButton);
        buttonPanel.add(zoomOutButton);
        buttonPanel.setBounds(600, 10, 100, 60);
        layeredPane.add(buttonPanel, JLayeredPane.PALETTE_LAYER);

        frame.add(layeredPane);

        zoomInButton.addActionListener(e -> adjustZoom(1));
        zoomOutButton.addActionListener(e -> adjustZoom(-1));

        label.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (isNearMarker(e.getPoint())) {
                    JOptionPane.showMessageDialog(frame, "You clicked near the marker!", "Marker Info", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        label.addMouseWheelListener(new MouseWheelListener() {
            public void mouseWheelMoved(MouseWheelEvent e) {
                int notches = e.getWheelRotation();
                if (notches < 0) {
                    adjustZoom(1);
                } else {
                    adjustZoom(-1);
                }
            }
        });

        fetchAndDisplayMap();
        frame.setVisible(true);
    }

    private static boolean isNearMarker(Point clickPoint) {
        double distance = clickPoint.distance(markerPosition);
        return distance < 50; // Check if click is within 50 pixels of the marker
    }

    private static void fetchAndDisplayMap() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://ipinfo.io/json?token=85efe3742137e2"))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String[] loc = response.body().split("\"loc\": \"")[1].split("\",")[0].split(",");
            latitude = loc[0];
            longitude = loc[1];

            String clientId = "5bt0m1r7kk";
            String clientSecret = "gxArtOeB8YTQrtpI5vNwZPPKaXSdcOANdDKRUhHX";
            String markers = String.format("type:d|size:mid|pos:%s%%20%s", longitude, latitude);
            String mapUrl = String.format(
                    "https://naveropenapi.apigw.ntruss.com/map-static/v2/raster?w=700&h=800&center=%s,%s&level=%d&markers=%s&X-NCP-APIGW-API-KEY-ID=%s&X-NCP-APIGW-API-KEY=%s",
                    longitude, latitude, zoomLevel, markers, clientId, clientSecret
            );

            URL url = new URL(mapUrl);
            BufferedImage image = ImageIO.read(url);
            label.setIcon(new ImageIcon(image));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void adjustZoom(int delta) {
        zoomLevel += delta;
        fetchAndDisplayMap();
    }
}
