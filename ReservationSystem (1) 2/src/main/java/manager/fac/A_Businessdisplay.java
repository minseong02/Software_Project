/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package manager.fac;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import manager.A_Memberdisplay;

/**
 *
 * @author 원채연
 */
public class A_Businessdisplay extends javax.swing.JFrame {
    private PBusinessManage businManager;

    /**
     * Creates new form A_UserManage
     */
    public A_Businessdisplay() {
        initComponents();
        setTitle("사업자 관리");
        setLocationRelativeTo(null);
        setSize(700, 800);
        setDefaultCloseOperation(A_Businessdisplay.EXIT_ON_CLOSE);
        
        
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        FManageFactory fac = new FBusinFactory();
        PManage mng = fac.createmanage("File/BusinessInfo.txt");
        mng.loadInfo(model, jTable1);

              
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        out_butt = new javax.swing.JButton();
        back_butt = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jTable1.setFont(new java.awt.Font("맑은 고딕", 0, 17)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "유저넘버", "이름", "상호명", "사업자번호", "전화번호"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
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
            jTable1.getColumnModel().getColumn(4).setResizable(false);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(back_butt, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(out_butt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(out_butt, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(back_butt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
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
        if(jTable1.getSelectedRowCount() == 1){ //성공적으로 탈퇴
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        FManageFactory fac = new FBusinFactory();
        PManage mng = fac.createmanage("File/BusinessInfo.txt");
        mng.deleteInfo(model, jTable1.getSelectedRow());
        JOptionPane.showMessageDialog(this, "해당 회원이 탈퇴되었습니다.");
        } else{
        JOptionPane.showMessageDialog(this, "회원을 선택해 주세요.");
        }
    }//GEN-LAST:event_out_buttActionPerformed

    /**
     * @param args the command line arguments
     */



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back_butt;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton out_butt;
    // End of variables declaration//GEN-END:variables

}
