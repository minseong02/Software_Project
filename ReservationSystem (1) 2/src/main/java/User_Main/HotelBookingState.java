package User_Main;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;

// HotelBookingState 클래스 정의 - FormState 인터페이스 구현

public class HotelBookingState implements FormState {

    @Override
    public void handle(BookingPopup context) {
        HotelBookingFormBuilder builder = new HotelBookingFormBuilder();
        ButtonGroup breakfastGroup = new ButtonGroup();
        ButtonGroup roomTypeGroup = new ButtonGroup();
        ButtonGroup tripTypeGroup = new ButtonGroup();
        ButtonGroup insuranceGroup = new ButtonGroup();

        JPanel formPanel = builder
                // 호텔 예약 정보
                .addLabel("비즈니스 넘버:", 20, 60, 100, 25)
                .addTextField("hotelBusinessNumber", 140, 60, 200, 25)
                .addLabel("호텔 이름:", 20, 100, 100, 25)
                .addTextField("hotelName", 140, 100, 200, 25)
                .addLabel("지역:", 20, 140, 100, 25)
                .addComboBox("hotelRegion", new String[]{"서울특별시", "부산광역시", "대구광역시", "인천광역시", "광주광역시", "대전광역시", "울산광역시", "세종특별자치시", "경기도", "강원도", "충청북도", "충청남도", "전라북도", "전라남도", "경상북도", "경상남도", "제주특별자치도"}, 140, 140, 200, 25)
                .addLabel("상세 주소:", 20, 180, 100, 25)
                .addTextField("hotelAddress", 140, 180, 200, 25)
                .addLabel("투숙객 수:", 20, 220, 100, 25)
                .addComboBox("guestNumber", new String[]{"1명", "2명", "3명", "4명", "5명", "6명"}, 140, 220, 200, 25)
                .addLabel("조식 여부:", 20, 260, 100, 25)
                .addRadioButton("breakfast", "O", 140, 260, breakfastGroup)
                .addRadioButton("breakfast", "X", 200, 260, breakfastGroup)
                .addLabel("룸 타입:", 20, 300, 100, 25)
                .addRadioButton("roomType", "스위트룸", 140, 300, roomTypeGroup)
                .addRadioButton("roomType", "디럭스룸", 230, 300, roomTypeGroup)
                .addRadioButton("roomType", "스탠다드룸", 320, 300, roomTypeGroup)
                .addLabel("숙박 비용:", 20, 340, 100, 25)
                .addTextField("cost", 140, 340, 200, 25)
                .addLabel("등록 여부:", 20, 380, 100, 25)
                .addTextField("registration", 140, 380, 200, 25)
                .addLabel("호텔 대표 사진:", 20, 420, 100, 25)

                // 항공 예약 정보
                .addLabel("항공 비즈니스 넘버:", 20, 460, 150, 25)
                .addTextField("airlineBusinessNumber", 170, 460, 200, 25)
                .addLabel("항공사:", 20, 500, 100, 25)
                .addComboBox("airline", new String[]{"대한 항공", "아시아나 항공", "제주 항공"}, 140, 500, 200, 25)
                .addLabel("출발 지역:", 20, 540, 100, 25)
                .addComboBox("departureRegion", new String[]{"인천", "김포", "부산", "제주"}, 140, 540, 200, 25)
                .addLabel("도착 지역:", 20, 580, 100, 25)
                .addComboBox("arrivalRegion", new String[]{"인천", "김포", "부산", "제주"}, 140, 580, 200, 25)
                .addLabel("좌석 타입:", 20, 620, 100, 25)
                .addComboBox("seatType", new String[]{"이코노미", "비즈니스", "퍼스트 클래스"}, 140, 620, 200, 25)
                .addLabel("왕복/편도:", 20, 660, 100, 25)
                .addRadioButton("tripType", "왕복", 140, 660, tripTypeGroup)
                .addRadioButton("tripType", "편도", 200, 660, tripTypeGroup)
                .addLabel("항공 비용:", 20, 700, 100, 25)
                .addTextField("airlineCost", 140, 700, 200, 25)
                .addLabel("등록 여부:", 20, 740, 100, 25)
                .addTextField("airlineRegistration", 140, 740, 200, 25)

                // 렌터카 예약 정보
                .addLabel("렌터카 예약 번호:", 20, 780, 150, 25)
                .addTextField("carRentalNumber", 170, 780, 200, 25)
                .addLabel("렌터카 회사:", 20, 820, 100, 25)
                .addTextField("carRentalCompany", 140, 820, 200, 25)
                .addLabel("이용 시간:", 20, 860, 100, 25)
                .addTextField("rentalTime", 140, 860, 200, 25)
                .addLabel("렌터카 비용:", 20, 900, 100, 25)
                .addTextField("rentalCost", 140, 900, 200, 25)
                .addLabel("차량 종류:", 20, 940, 100, 25)
                .addTextField("carType", 140, 940, 200, 25)
                .addLabel("연료 종류:", 20, 980, 100, 25)
                .addComboBox("fuelType", new String[]{"휘발유", "경유", "전기", "하이브리드"}, 140, 980, 200, 25)
                .addLabel("렌터카 보험 여부:", 20, 1020, 150, 25)
                .addRadioButton("insurance", "O", 170, 1020, insuranceGroup)
                .addRadioButton("insurance", "X", 200, 1020, insuranceGroup)
                .addLabel("렌터카 등록 여부:", 20, 1060, 150, 25)
                .addTextField("carRegistration", 170, 1060, 200, 25)

                .addButton("예약 완료", 290, 1100, 120, 30, e -> context.completeReservation(builder.build())) // 예약 완료 버튼 위치 수정
                .addButton("닫기", 20, 20, 80, 30, e -> context.goBack()) // 닫기 버튼 위치 수정
                .build();

        context.setFormPanel(formPanel);
        context.addScrollPane(formPanel);
    }
}