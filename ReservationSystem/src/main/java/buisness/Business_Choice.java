/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package buisness;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author mskim
 */
public class Business_Choice extends JFrame{
    public Business_Choice(){
        super("사업자 관리");
        setSize(700,800);
        
        menu(); // 메뉴 메소드 호출
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    void menu(){
        JMenuBar mb = new JMenuBar(); // 메뉴바 생성
        JMenu screenmenu = new JMenu("사업자 유형");
        
        
        JMenuItem hotel_menuItem;
        JMenuItem airplane_menuItem;
        JMenuItem car_menuItem;
        
        ImageIcon hotelIcon = new ImageIcon("hotel.png");
        hotel_menuItem = new JMenuItem("호텔");
        hotel_menuItem.setIcon(hotelIcon);
        screenmenu.add(hotel_menuItem);
        hotel_menuItem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                getContentPane().removeAll(); // 기존 패널 삭제
                getContentPane().add(new HotelBS()); // 호텔 사업자 패널 뜨도록 하기
                revalidate(); // 변경사항 적용
                repaint();
            }
        });
        
        
        ImageIcon airplaneIcon = new ImageIcon("airplane.png");
        airplane_menuItem = new JMenuItem("항공");
        airplane_menuItem.setIcon(airplaneIcon);
        screenmenu.add(airplane_menuItem);
        airplane_menuItem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                getContentPane().removeAll(); // 기존 패널 삭제
                getContentPane().add(new AirplaneBS()); // 항공 사업자 패널 뜨도록 하기
                revalidate(); // 변경사항 적용
                repaint();
            }
        });
        
       
        ImageIcon carIcon = new ImageIcon("car.png");
        car_menuItem = new JMenuItem("렌터카");
        car_menuItem.setIcon(carIcon);
        screenmenu.add(car_menuItem);
        car_menuItem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                getContentPane().removeAll(); // 기존 패널 삭제
                getContentPane().add(new CarBS()); // 항공 사업자 패널 뜨도록 하기
                revalidate(); // 변경사항 적용
                repaint();
            }
        });
      
        
        
        
        mb.add(screenmenu);
        setJMenuBar(mb);
        
  
        
        
        
    }

       
       
    
    
}
