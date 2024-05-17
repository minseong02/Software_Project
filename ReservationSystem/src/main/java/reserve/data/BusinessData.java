/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reserve.data;
/* 사용자 회원가입에 필요한 정보를 저장하고 있는 클래스 */

/**
 *
 * @author regin
 */

public class BusinessData {
    private String ID;        // 아이디
    private String PW;        // 비밀번호
    private String name;      // 이름
    private String TM;        // 상호명
    private String Bno;       // 사업자번호
    private String no;        // 전화번호
    
    public BusinessData(String ID, String PW, String name,  String TM, String Bno, String no){
        this.ID = ID;
        this.PW = PW;
        this.name = name;
        this.TM = TM;
        this.Bno = Bno;
        this.no = no;
    }
    
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
    
    public String getPW() {
        return PW;
    }

    public void setPW(String PW) {
        this.PW = PW;
    }
    
    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }
   
    public String getBno() {
        return Bno;
    }

    public void setBno(String Bno) {
        this.Bno = Bno;
    }
   
    public String getTM() {
        return TM;
    }

    public void setTM(String TM) {
        this.TM = TM;
    }
    
    public String getno() {
        return no;
    }

    public void setno(String no) {
        this.no = no;
    }
}
