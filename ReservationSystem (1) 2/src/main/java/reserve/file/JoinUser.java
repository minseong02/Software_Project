package reserve.file;

import User_Main.JavaNaver;
import reserve.data.UserData;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Class for handling user join operations.
 */
public class JoinUser implements CreateInterface {

    private ArrayList<String> readInfo = new ArrayList<>();
    private List<UserData> userInfo;
    private static JoinUser instance;

    private String line;
    private String UserFile = "File/UserInfo.txt";
    private int index = 0;

    private JoinUser() {
        userInfo = new ArrayList<>();
    }

    public JoinUser(List<UserData> userInfo) {
        this.userInfo = userInfo;
    }

    public static JoinUser getInstance() {
        if (instance == null) {
            instance = new JoinUser();
        }
        return instance;
    }

    @Override
    public void fRead() {
        try (BufferedReader fileread = new BufferedReader(new InputStreamReader(new FileInputStream(UserFile), "UTF-8"))) {
            index = 1;
            while ((line = fileread.readLine()) != null) {
                readInfo.add(line);
                index++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("파일이 존재하지 않습니다. 경로를 확인해주세요.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getIndex() {
        return index;
    }

    @Override
    public void fWrite(String data) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(UserFile, true), "UTF-8"))) {
            bw.write(data);
            bw.flush();
        }
    }

    @Override
    public void sPlite() {
        for (String info : readInfo) {
            String[] str = info.split("\\|");
            userInfo.add(new UserData(str[0], str[1], str[2], str[3], str[4], str[5]));
        }
    }

    public List<UserData> returnUserInfo() {
        return userInfo;
    }

    @Override
    public boolean login(String ID, String PW) {
        if (ID.isEmpty() || PW.isEmpty()) {
            return false;
        }

        for (UserData user : userInfo) {
            if (user.getID().equals(ID) && user.getPW().equals(PW)) {
                saveLoginId(ID);
                return true;
            }
        }
        return false;
    }

    private void saveLoginId(String ID) {
        String loginInfoFile = "File/LoginInfo.txt";
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(loginInfoFile, true), "UTF-8"))) {
            bw.write(ID);
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void LoginSuccess() {
        JOptionPane.showMessageDialog(null, "로그인 되었습니다.");

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 800);
        frame.setLocationRelativeTo(null);
        JavaNaver.openReservationSystem(frame); // 지도로 이동
        frame.setVisible(true);
    }
}
