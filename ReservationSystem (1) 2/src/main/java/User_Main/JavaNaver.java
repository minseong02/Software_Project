package User_Main;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Stack;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import reserve.login.Login;

public class JavaNaver {

    private static int zoomLevel = 16;
    private static JLabel label;
    private static String latitude;
    private static String longitude;
    private static Point markerPosition = new Point(350, 400); // Approximate center position for simplicity
    static JLayeredPane layeredPane;
    private static JButton zoomInButton;
    private static JButton zoomOutButton;
    private static JButton reservationInquiryButton;
    private static JButton logoutButton;
    static Stack<JPanel> pageStack = new Stack<>(); // Stack to keep track of pages
    private static int reservationCounter = 1000; // Initialize reservation counter

    public static void openReservationSystem(JFrame frame) {
        frame.getContentPane().removeAll();
        frame.setTitle("여행 예약 시스템");

        label = new JLabel();
        label.setBounds(0, 0, 700, 800);

        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(700, 800));
        layeredPane.add(label, JLayeredPane.DEFAULT_LAYER);

        JPanel topRightPanel = new JPanel();
        topRightPanel.setOpaque(false);
        topRightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        reservationInquiryButton = new JButton("예약조회");
        topRightPanel.add(reservationInquiryButton);
        topRightPanel.setBounds(400, 10, 300, 60);
        layeredPane.add(topRightPanel, JLayeredPane.PALETTE_LAYER);

        JPanel centerRightPanel = new JPanel();
        centerRightPanel.setOpaque(false);
        centerRightPanel.setLayout(new GridLayout(2, 1, 0, 10));
        zoomInButton = new JButton("+");
        zoomOutButton = new JButton("-");
        centerRightPanel.add(zoomInButton);
        centerRightPanel.add(zoomOutButton);
        centerRightPanel.setBounds(650, 350, 50, 100); // Adjusted position for buttons
        layeredPane.add(centerRightPanel, JLayeredPane.PALETTE_LAYER);

        JPanel bottomRightPanel = new JPanel();
        bottomRightPanel.setOpaque(false);
        bottomRightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        logoutButton = new JButton("로그아웃");
        bottomRightPanel.add(logoutButton);
        bottomRightPanel.setBounds(600, 720, 100, 60);
        layeredPane.add(bottomRightPanel, JLayeredPane.PALETTE_LAYER);

        frame.add(layeredPane);

        zoomInButton.addActionListener(e -> adjustZoom(1));
        zoomOutButton.addActionListener(e -> adjustZoom(-1));
        reservationInquiryButton.addActionListener(e -> ReservationInquiryPage.showReservationInquiryPage());
        logoutButton.addActionListener(e -> logout((JFrame) SwingUtilities.getWindowAncestor(logoutButton)));

        label.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (isNearMarker(e.getPoint())) {
                    showHotelBookingPage();
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
        frame.revalidate();
        frame.repaint();
    }

    private static void showHotelBookingPage() {
        JPanel currentPanel = new JPanel();
        currentPanel.setLayout(null);
        currentPanel.setBounds(0, 0, 700, 800);
        for (Component component : layeredPane.getComponents()) {
            currentPanel.add(component);
        }
        pageStack.push(currentPanel); // Push current page to stack

        // Hotel Booking Popup
        BookingPopup hotelBookingPopup = new BookingPopup(new HotelBookingState());

        layeredPane.removeAll();
        layeredPane.add(hotelBookingPopup.getScrollPane(), JLayeredPane.DEFAULT_LAYER);
        layeredPane.revalidate();
        layeredPane.repaint();
    }

    public static String generateReservationNumber() {
        return "RES" + (reservationCounter++);
    }

    private static boolean isNearMarker(Point clickPoint) {
        // This method now returns true for any click, allowing any click to trigger the transition to the reservation page
        return true;
    }

    public static void fetchAndDisplayMap() {
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

            // Current location marker in red
            String currentLocationMarker = String.format("type:d|size:mid|color:red|pos:%s%%20%s", longitude, latitude);

            // Additional markers in green
            String additionalMarkers = "type:d|size:mid|color:green|pos:126.9780%2037.5665|type:d|size:mid|color:green|pos:129.0756%2035.1796|type:d|size:mid|color:green|pos:128.6014%2035.8714";

            String markers = currentLocationMarker + "|" + additionalMarkers;

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

    public static void setZoomButtonsVisible(boolean visible) {
        zoomInButton.setVisible(visible);
        zoomOutButton.setVisible(visible);
    }

    public static void goBack() {
        if (!pageStack.isEmpty()) {
            JPanel previousPage = pageStack.pop();
            layeredPane.removeAll();
            for (Component component : previousPage.getComponents()) {
                layeredPane.add(component);
            }
            layeredPane.revalidate();
            layeredPane.repaint();
            setZoomButtonsVisible(true); // Show zoom buttons
        }
    }

    private static void logout(JFrame currentFrame) {
        JOptionPane.showMessageDialog(layeredPane, "로그아웃 되었습니다.");
        currentFrame.dispose();
        new Login().setVisible(true);
    }
}
