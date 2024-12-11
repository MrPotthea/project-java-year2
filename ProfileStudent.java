import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

public class ProfileStudent extends JFrame implements ItemListener {

    JTextPane txtShow = new JTextPane();
    JScrollPane scProfile = new JScrollPane(txtShow);
    JCheckBox checkB1, checkB2, checkB3, checkB4, checkB5, checkB6, checkB7;
    JPanel pnl = new JPanel(new FlowLayout());
    JLabel stuimage = new JLabel();

    public ProfileStudent() {
        setTitle("Profile Student");

        // Initialize and add checkboxes
        checkB1 = new JCheckBox("Phun Potthea");
        checkB2 = new JCheckBox("Phan Phatsa");
        checkB3 = new JCheckBox("Phorn Pheakdey");
        checkB4 = new JCheckBox("Proem Ponleu");
        checkB5 = new JCheckBox("Seam Sreyneang");
        checkB6 = new JCheckBox("Mun Bora");
        checkB7 = new JCheckBox("Oem Bunrath");
        JCheckBox[] checkboxes = {checkB1, checkB2, checkB3, checkB4, checkB5, checkB6, checkB7};
        for (JCheckBox checkbox : checkboxes) {
            checkbox.addItemListener(this);
            pnl.add(checkbox);
        }

        // Set larger font size for the JTextPane
        txtShow.setFont(new Font("Consolas", Font.PLAIN, 20));  // Adjust font size here
        txtShow.setEditable(false);

        // Add components to frame
        add(pnl, BorderLayout.NORTH);
        add(scProfile, BorderLayout.CENTER);
        add(stuimage, BorderLayout.SOUTH);

        // Add window listener
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        // Frame configuration
        setSize(770, 650);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        JCheckBox source = (JCheckBox) e.getItemSelectable();
        if (e.getStateChange() == ItemEvent.SELECTED) {
            JCheckBox[] checkboxes = {checkB1, checkB2, checkB3, checkB4, checkB5, checkB6, checkB7};
            for (JCheckBox checkbox : checkboxes) {
                if (checkbox != source) {
                    checkbox.setSelected(false);
                }
            }
            displayStudentInfo(source);
        }
    }

    private void displayStudentInfo(JCheckBox selectedCheckbox) {
        String[] stphoto = {"d:/photo/messi.jpg", "d:/photo/p13.jpg", "d:/photo/pk (2).jpg", "d:/photo/pl (1).jpg", "d:/photo/s (1).jpg", "d:/photo/b1.jpg", "d:/photo/bunrath (1).png"};
        String[] stId = {"9", "8", "25", "18", "45", "36", "51"};
        String[] stfname = {"Potthea", "Phatsa", "Pheakdey", "Ponleu", "Sreyneang", "Bora", "Bunrath"};
        String[] stlname = {"Phun", "Phan", "Phorn", "Proem", "Seam", "Mun", "Oem"};
        String[] stsex = {"Male", "Female", "Male", "Male", "Female", "Male", "Male"};
        String[] stbirt = {"13 May 2005", "15 June 2003", "18 June 2003", "18 June 2005", "08 November 2003", "06 September 2004", "18 January 2003"};
        String[] stnation = {"Khmer", "Khmer", "Khmer", "Khmer", "Khmer", "Khmer", "Khmer"};
        String[] stemail = {"phun.potthea.1222@rupp.edu.kh", "phan.phatsa.1222@rupp.edu.kh", "phorn.pheakdey.1222@rupp.edu.kh", "proem.ponleu.1222@rupp.edu.kh", "seam.sreyneang.1222@rupp.edu.kh", "mun.bora.1222@rupp.edu.kh", "oem.bunrath.1222@rupp.edu.kh"};
        String[] stphone = {"+855 965735798", "+855 977817259", "+855 92574581", "+855 975438840", "+855 885539843", "+855 969296811", "+855 16969937"};
        String[] stlevel = {"University", "University", "University", "University", "University", "University", "University"};
        String[] staddress = {"Phnom Penh", "Phnom Penh", "Phnom Penh", "Phnom Penh", "Phnom Penh", "Phnom Penh", "Phnom Penh"};
        String[] stskill = {"Computer Science", "Computer Science", "Computer Science", "Computer Science", "Computer Science", "Computer Science", "Computer Science"};

        JCheckBox[] checkboxes = {checkB1, checkB2, checkB3, checkB4, checkB5, checkB6, checkB7};

        for (int i = 0; i < checkboxes.length; i++) {
            if (checkboxes[i] == selectedCheckbox) {
                // Clear previous content
                txtShow.setText("");
                StyledDocument doc = txtShow.getStyledDocument();           
                // Add text information
                try {
                    doc.insertString(doc.getLength(), "\n", null);
                    doc.insertString(doc.getLength(), "\t\t\t\t", null);
                    txtShow.insertIcon(new ImageIcon(stphoto[i]));
                    doc.insertString(doc.getLength(),
                        "\n\t\tID: " + stId[i] + "\n" +
                        "\t\tFirst Name: " + stfname[i] + "\n" +
                        "\t\tLast Name: " + stlname[i] + "\n" +
                        "\t\tGender: " + stsex[i] + "\n" +
                        "\t\tBirth Date: " + stbirt[i] + "\n" +
                        "\t\tNationality: " + stnation[i] + "\n" +
                        "\t\tEmail: " + stemail[i] + "\n" +
                        "\t\tPhone: " + stphone[i] + "\n" +
                        "\t\tAddress: " + staddress[i] + "\n" +
                        "\t\tLevel: " + stlevel[i] + "\n" +
                        "\t\tSkill: " + stskill[i] + "\n", null);
                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                }
                break;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        new ProfileStudent();
    }
}
