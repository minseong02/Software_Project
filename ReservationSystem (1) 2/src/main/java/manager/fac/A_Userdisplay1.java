/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package manager.fac;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import manager.A_Memberdisplay;
import manager.Buylist;

/**
 *
 * @author 원채연
 */
public class A_Userdisplay1 extends javax.swing.JFrame {

    private PUserManage userManager;

    /**
     * Creates new form A_UserManage
     */
    public A_Userdisplay1() {
        initComponents();
        setTitle("사용자 관리");
        setLocationRelativeTo(null);
        setSize(700, 800);
        setDefaultCloseOperation(A_Userdisplay1.EXIT_ON_CLOSE);

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        FManageFactory fac = new FUserFactory();
        PManage mng = fac.createmanage("File/UserInfo.txt");
        mng.loadInfo(model, jTable1);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        out_butt = new javax.swing.JButton();
        back_butt = new javax.swing.JButton();
        buylist_butt1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(700, 800));
        setResizable(false);

        jTable1.setFont(new java.awt.Font("맑은 고딕", 0, 17)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "유저넘버", "이름", "생년월일", "전화번호"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setRowHeight(25);
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.getTableHeader().setResizingAllowed(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
        }

        out_butt.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
        out_butt.setText("탈퇴");
        out_butt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                out_buttActionPerformed(evt);
            }
        });

        back_butt.setText("뒤로가기");
        back_butt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                back_buttActionPerformed(evt);
            }
        });

        buylist_butt1.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
        buylist_butt1.setText("구매내역");
        buylist_butt1.setActionCommand("구내매역");
        buylist_butt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buylist_butt1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buylist_butt1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(out_butt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(back_butt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(out_butt, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buylist_butt1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                .addComponent(back_butt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void back_buttActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_back_buttActionPerformed
        // TODO add your handling code here:
        A_Memberdisplay am = new A_Memberdisplay();
        am.setVisible(true);
        setVisible(false);
        dispose();
    }//GEN-LAST:event_back_buttActionPerformed

    private void out_buttActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_out_buttActionPerformed
        // TODO add your handling code here:
        if (jTable1.getSelectedRowCount() == 1) { //성공적으로 탈퇴
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            FManageFactory fac = new FUserFactory();
            PManage mng = fac.createmanage("File/UserInfo.txt");
            mng.deleteInfo(model, jTable1.getSelectedRow());
            JOptionPane.showMessageDialog(this, "해당 회원이 탈퇴되었습니다.");
        } else {
            JOptionPane.showMessageDialog(this, "회원을 선택해 주세요.");
        }

    }//GEN-LAST:event_out_buttActionPerformed

    private void buylist_butt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buylist_butt1ActionPerformed
        // TODO add your handling code here:
        if (jTable1.getSelectedRowCount() == 1) {
            Buylist bl = new Buylist();
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            bl.userId = (String) model.getValueAt(jTable1.getSelectedRow(), 0);
            bl.loadbuyInfo();
            bl.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "회원을 선택해 주세요.");
        }
    }//GEN-LAST:event_buylist_butt1ActionPerformed

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back_butt;
    private javax.swing.JButton buylist_butt1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton out_butt;
    // End of variables declaration//GEN-END:variables

}
