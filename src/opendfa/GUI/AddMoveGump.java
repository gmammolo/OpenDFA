/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opendfa.GUI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import opendfa.DFA.Edge;
import opendfa.DFA.Move;
import opendfa.OpenDFA;

/**
 *
 * @author terasud
 */
public class AddMoveGump extends javax.swing.JDialog {

    private OpenDFA dfa;
    private Edge edge;

    /**
     * Creates new form AddMoveGump
     */
    public AddMoveGump(java.awt.Frame parent, boolean modal, OpenDFA dfa) {
        this(parent, modal, dfa, new Edge(0, 0, new ArrayList<Character>(Arrays.asList('a')), "a"));
    }

    /**
     * Creates new form AddMoveGump
     */
    public AddMoveGump(java.awt.Frame parent, boolean modal, OpenDFA dfa, Edge e) {
        super(parent, modal);
        initComponents();
        buttonGroup1.add(jRadioButton1);
        buttonGroup1.add(jRadioButton2);
        this.dfa = dfa;
        this.edge = e;
        startInput.setText(e.start.toString());
        endInput.setText(e.end.toString());
        arrayText.setText(e.label);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel2 = new javax.swing.JLabel();
        endInput = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        startInput = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        singleText = new javax.swing.JTextField();
        jRadioButton2 = new javax.swing.JRadioButton();
        arrayText = new javax.swing.JTextField();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add New Transitions");

        jLabel2.setText("Start");

        endInput.setText("0");

        jLabel3.setText("end");

        startInput.setText("0");
        startInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startInputActionPerformed(evt);
            }
        });

        jLabel4.setText("=====[simboli]======>");

        jRadioButton1.setText("Simbolo Singolo:");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        singleText.setText("a");
        singleText.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                singleTextFocusGained(evt);
            }
        });

        jRadioButton2.setText("Range di Simboli:");

        arrayText.setText("a,b,c,d,s,r,e");
        arrayText.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                arrayTextFocusGained(evt);
            }
        });

        okButton.setText("OK");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("CANCEL");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(startInput, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(endInput, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(48, 48, 48)
                                        .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(cancelButton))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jRadioButton1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(singleText, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jRadioButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(arrayText)))))
                .addGap(51, 51, 51))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(endInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(startInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(singleText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton2)
                    .addComponent(arrayText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(77, 77, 77)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(okButton)
                    .addComponent(cancelButton))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        // TODO add your handling code here:
        Integer start = 0;
        try {
            start = Integer.parseInt(startInput.getText());
            if (!dfa.isValidState(start)) {
                WarningError.generateWarningMessage("lo start deve essere uno stato valido!");
                return;
            }
        } catch (NumberFormatException e) {
            WarningError.generateWarningMessage("lo start deve essere un'intero!");
            return;
        }
        Integer end = 0;
        try {
            end = Integer.parseInt(endInput.getText());
            if (!dfa.isValidState(end)) {
                WarningError.generateWarningMessage("l' end deve essere uno stato valido!");
                return;
            }
        } catch (NumberFormatException e) {
            WarningError.generateWarningMessage("l'end deve essere un'intero!");
            return;
        }
        if (jRadioButton1.isSelected()) {
            if (singleText.getText().length() != 1) {
                //TODO: verificare che sia un carattere valido
                WarningError.generateWarningMessage("deve contenere solo un carattere");
                return;
            } else {
                Character c = singleText.getText().charAt(0);
                dfa.remove(edge.start, edge.alphabet);
                dfa.setMove(start, c, end);
                dispose();
            }
        } else if (jRadioButton2.isSelected()) {
            String text = arrayText.getText();
            if (text.length() == 0) {
                WarningError.generateWarningMessage("inserire almeno un carattere");
                return;
            } else {
                ArrayList<Character> list = new ArrayList<>();
                Pattern p = Pattern.compile("\\w-\\w");
                Matcher m = p.matcher(arrayText.getText());
                while (m.find()) {
                    char _start = text.charAt(m.start());
                    char _end = text.charAt(m.end() - 1);
                    char _index = _start;
                    while (_index <= _end) {
                        list.add(_index++);
                    }
                }
                text = m.replaceAll("");
                for (Character c : text.toCharArray()) {
                    if (c != ' ' && c != ',') {
                        list.add(c);
                    }
                }
                dfa.remove(edge.start, edge.alphabet);
                dfa.setMove(start, list, end);
                dispose();
            }

        } else {
            WarningError.generateWarningMessage("seleziona almeno un metodo di input per l'alfabeto");
        }
    }//GEN-LAST:event_okButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        // TODO add your handling code here:
        dispose();

    }//GEN-LAST:event_cancelButtonActionPerformed

    private void singleTextFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_singleTextFocusGained
        jRadioButton1.setSelected(true);
    }//GEN-LAST:event_singleTextFocusGained

    private void arrayTextFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_arrayTextFocusGained
        jRadioButton2.setSelected(true);
    }//GEN-LAST:event_arrayTextFocusGained

    private void startInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_startInputActionPerformed

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
            java.util.logging.Logger.getLogger(AddMoveGump.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddMoveGump.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddMoveGump.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddMoveGump.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AddMoveGump dialog = new AddMoveGump(new javax.swing.JFrame(), true, new OpenDFA(0));
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        dialog.dispose();
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    public static void generateAddMoveGump(OpenDFA dfa) {
        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AddMoveGump dialog = new AddMoveGump(new javax.swing.JFrame(), true, dfa);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        dialog.dispose();
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    public static void generateAddMoveGump(OpenDFA dfa, int index) {
        /* Create and display the dialog */
        Edge e = dfa.getEdgeAt(index);
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                AddMoveGump dialog = new AddMoveGump(new javax.swing.JFrame(), true, dfa, e);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        dialog.dispose();
                    }
                });
                dialog.setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField arrayText;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton cancelButton;
    private javax.swing.JTextField endInput;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JButton okButton;
    private javax.swing.JTextField singleText;
    private javax.swing.JTextField startInput;
    // End of variables declaration//GEN-END:variables
}
