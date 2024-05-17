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
public class UserData {
    private String ID;        // 아이디
    private String PW;        // 비밀번호
    private String name;      // 이름
    private String gender;    // 성별
    private String birth;     // 생년월일
    private String no;        // 전화번호
    
    public UserData(String ID, String PW, String name, String gender, String birth, String no){
        this.ID = ID;
        this.PW = PW;
        this.name = name;
        this.gender = gender;
        this.birth = birth;
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
   
    public String getgender() {
        return gender;
    }

    public void setgender(String gender) {
        this.gender = gender;
    }
    
    public String getbirth() {
        return birth;
    }

    public void setbirth(String birth) {
        this.birth = birth;
    }
    
    public String getno() {
        return no;
    }

    public void setno(String no) {
        this.no = no;
    }
}
