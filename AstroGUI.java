import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;

/**
 * @author Will Krause
 * @version 2019-05-02
 */

public class AstroGUI {
    private Astronaut astronaut;
    private AstronautFactory afactory = new AstronautFactory();
    private String strAID;

    private Component createComponents() {
        final JLabel
                labSpacer1= new JLabel(""),
                labSpacer2= new JLabel("");

        JButton
                butLoad = new JButton("Load Astroanut"),
                butSave = new JButton("Save Astronaut");

        JLabel
                labAID  = new JLabel("Astronaut ID"),
                labLname   = new JLabel("Last Name"),
                labFname   = new JLabel("First Name"),
                labNickName = new JLabel("NickName"),
                labServiceBranch = new JLabel("Service Branch"),
                labDOB = new JLabel("DOB"),
                labRevNum = new JLabel("Revision Number");

        final JTextField
                txtAID  = new JTextField(6),
                txtLname   = new JTextField(15),
                txtFname   = new JTextField(15),
                txtNickName = new JTextField(15),
                txtServiceBranch = new JTextField(15),
                txtDOB = new JTextField(6),
                txtRevNum = new JTextField(5);
        txtRevNum.setEditable(false);

        butLoad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                strAID = txtAID.getText();
                astronaut = afactory.getAstronaut(strAID);
                txtLname.setText(astronaut.getLastName());
                txtFname.setText(astronaut.getFirstName());
                txtNickName.setText(astronaut.getNickName());
                txtServiceBranch.setText(astronaut.getServiceBranch());
                txtDOB.setText(astronaut.getDob().toString());
                txtRevNum.setText(astronaut.getRevNum()+"");
            }
        });

        butSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                astronaut.setLastName(txtLname.getText());
                astronaut.setFirstName(txtFname.getText());
                astronaut.setServiceBranch(txtServiceBranch.getText());
                astronaut.setNickName(txtNickName.getText());
                astronaut.setDob(Date.valueOf(txtDOB.getText()));
                String saved = afactory.saveAstronaut(astronaut);
                txtAID.setText("");
                txtLname.setText("");
                txtFname.setText("");
                txtDOB.setText("");
                txtNickName.setText("");
                txtRevNum.setText("");
                txtServiceBranch.setText("");
                if (saved.equals("Saved!")) {
                    JOptionPane.showMessageDialog(null, "The data was saved successfully!");
                }
                else {
                    JOptionPane.showMessageDialog(null, afactory.getDBstatus());
                }
            }
        });

        JPanel pane = new JPanel();
        pane.setLayout(new GridLayout(5,2));
        pane.add(labAID);
        pane.add(txtAID);
        pane.add(labLname);
        pane.add(txtLname);
        pane.add(labFname);
        pane.add(txtFname);
        pane.add(labNickName);
        pane.add(txtNickName);
        pane.add(labServiceBranch);
        pane.add(txtServiceBranch);
        pane.add(labDOB);
        pane.add(txtDOB);
        pane.add(labRevNum);
        pane.add(txtRevNum);
        pane.add(labSpacer1);
        pane.add(labSpacer2);
        pane.add(butLoad);
        pane.add(butSave);

        return pane;
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) { }
        JFrame frame = new JFrame("AstronautGui");
        AstroGUI app = new AstroGUI();
        Component contents = app.createComponents();
        frame.getContentPane().add(contents, BorderLayout.CENTER);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        frame.pack();
        frame.setVisible(true);
    }
}


