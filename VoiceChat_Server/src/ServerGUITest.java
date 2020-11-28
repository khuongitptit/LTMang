
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author khuon
 */
public class ServerGUITest extends javax.swing.JFrame {

    /**
     * Creates new form ServerGUITest
     */
    ArrayList<RoomChat> list;
    static DefaultTableModel model;
    static void updateTableAddParticipant(int port){
        for(int i=0; i< roomTable.getRowCount();i++){
            if(Integer.parseInt(roomTable.getValueAt(i, 1).toString()) == port){
                int current = Integer.parseInt(roomTable.getValueAt(i, 3).toString());
                roomTable.setValueAt(current+1, i, 3);
            }
        }
    }
    static void updateTableRemoveParticipant(int port){
        for(int i=0; i< roomTable.getRowCount();i++){
            if(Integer.parseInt(roomTable.getValueAt(i, 1).toString()) == port){
                int current = Integer.parseInt(roomTable.getValueAt(i, 3).toString());
                roomTable.setValueAt(current-1, i, 3);
            }
        }
    }
//    static boolean isRoomAvailable(int port){
//        for(int i=0; i< roomTable.getRowCount();i++){
//            if(Integer.parseInt(roomTable.getValueAt(i, 1).toString()) == port){
//                int current = Integer.parseInt(roomTable.getValueAt(i, 3).toString());
//                int max = Integer.parseInt(roomTable.getValueAt(i, 2).toString());
//                return current < max;
//            }
//        }
//        return false;
//    }
    public ServerGUITest() {
        initComponents();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        list = ListRoomChat.getListRoomChat();
        //default rooms
        RoomChat room1 = new RoomChat(1999, 2, 0);
        RoomChat room2 = new RoomChat(2002, 3, 0);
        RoomChat room3 = new RoomChat(2005, 4, 0);
        list.add(room1);
        list.add(room2);
        list.add(room3);
        
        //
        for (RoomChat rc : list) {
            int port = rc.getPort();
            int maxParticipant = rc.getMaxParticipant();
            int currentParticipant = rc.getCurrentParticipant();
            ServerThread serverThread = new ServerThread(port, true);
            serverThread.start();
            ServerLog serverLog = new ServerLog(port);
            serverLog.start();
        }
        
        model = (DefaultTableModel) roomTable.getModel();
        model.setRowCount(0);
        for(int i=0;i<list.size();i++){
            int port = list.get(i).getPort();
            int maxParticipant = list.get(i).getMaxParticipant();
            int currentParticipant = list.get(i).getCurrentParticipant();
            model.addRow(new Object[]{i+1, port, maxParticipant, currentParticipant});
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        roomTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        roomTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "STT", "Số phòng", "Tối đa", "Hiện tại"
            }
        ));
        roomTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                roomTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(roomTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(138, 138, 138)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(155, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(82, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void roomTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_roomTableMouseClicked
        // TODO add your handling code here:
        int selectedPort = Integer.parseInt(model.getValueAt(roomTable.getSelectedRow(), 1).toString());
        for(RoomChat rc : ListRoomChat.getListRoomChat()){
            if(rc.getPort() == selectedPort){
                ArrayList<Participant> listP = rc.getParticipants();
                new RoomDetail(selectedPort, listP).setVisible(true);
            }
            
        }
        
    }//GEN-LAST:event_roomTableMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ServerGUITest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServerGUITest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServerGUITest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServerGUITest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServerGUITest().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable roomTable;
    // End of variables declaration//GEN-END:variables
}
