import java.awt.Label;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class RandomStuGroup extends JFrame implements ActionListener {

    Label lbRand = new Label("Group Assignment Generator");
    Label lbenternStu = new Label("Enter total number of students:");
    JTextField txtTotalStudent = new JTextField();
    Label lbenternstuingroup = new Label("Enter number of students per group:");
    JTextField txtTotalStuInGroup = new JTextField();
    JTextArea txtAreaRand = new JTextArea(27, 50);
    JScrollPane scp1 = new JScrollPane(txtAreaRand);
    JButton btnGenerate = new JButton("Generate Groups");
    JPanel pin = new JPanel(new BorderLayout());
    JPanel pnl = new JPanel(new GridLayout(2, 2, 10, 10));
    JPanel pbtn = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JPanel psp = new JPanel(new BorderLayout());
    JPanel pg = new JPanel();

    public RandomStuGroup() {
        super("Student Group Generator");

        // Configure UI elements
        lbRand.setAlignment(Label.CENTER);
        lbRand.setFont(new Font("Consolas", Font.BOLD, 15));
        
        // Set larger font size for the JTextArea
        txtAreaRand.setFont(new Font("Consolas", Font.PLAIN, 16));  // Adjust font size here
        txtAreaRand.setEditable(false);
        txtAreaRand.append("\t\tGroup Assignments\n");

        // Add components to panels
        pnl.add(lbenternStu);
        pnl.add(txtTotalStudent);
        pnl.add(lbenternstuingroup);
        pnl.add(txtTotalStuInGroup);
        pbtn.add(btnGenerate);
        pin.add(pnl, BorderLayout.NORTH);
        pin.add(pbtn, BorderLayout.CENTER);
        psp.add(scp1, BorderLayout.CENTER);
        pin.add(psp, BorderLayout.SOUTH);
        pg.add(pin);
        add(lbRand, BorderLayout.NORTH);
        add(pg);

        // Add event listeners
        btnGenerate.addActionListener(this);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        // Frame configuration
        setSize(670, 730);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnGenerate) {
            try {
                int totalStudents = Integer.parseInt(txtTotalStudent.getText().trim());
                int studentsPerGroup = Integer.parseInt(txtTotalStuInGroup.getText().trim());

                if (totalStudents <= 0 || studentsPerGroup <= 0) {
                    JOptionPane.showMessageDialog(this, "Values must be greater than zero.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (studentsPerGroup > totalStudents) {
                    JOptionPane.showMessageDialog(this, "Students per group cannot exceed total students.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Clear previous data
                txtAreaRand.setText("\t\tGroup Assignments\n\n");

                // Generate list of students
                ArrayList<String> students = new ArrayList<>();
                for (int i = 1; i <= totalStudents; i++) {
                    students.add("Student " + i);
                }
                Collections.shuffle(students);  // Shuffle to randomize the list

                int totalGroups = totalStudents / studentsPerGroup;
                int remainingStudents = totalStudents % studentsPerGroup;

                ArrayList<ArrayList<String>> groups = new ArrayList<>();
                for (int i = 0; i < totalGroups; i++) {
                    groups.add(new ArrayList<>());
                }

                int groupIndex = 0;
                for (String student : students.subList(0, totalStudents - remainingStudents)) {
                    groups.get(groupIndex).add(student);
                    groupIndex = (groupIndex + 1) % totalGroups;
                }

                // Distribute remaining students
                if (remainingStudents > 0) {
                    groupIndex = totalGroups - 1;
                    for (String student : students.subList(totalStudents - remainingStudents, totalStudents)) {
                        groups.get(groupIndex).add(student);
                        groupIndex = (groupIndex - 1 + totalGroups) % totalGroups;
                    }
                }

                // Display total students and groups
                txtAreaRand.append("Total Students: " + totalStudents + "\n");
                txtAreaRand.append("Total Groups: " + totalGroups + "\n\n");

                // Display groups
                for (int i = 0; i < groups.size(); i++) {
                    txtAreaRand.append("Team " + (i + 1) + ":\n");
                    for (String student : groups.get(i)) {
                        txtAreaRand.append(student + "\n");
                    }
                    txtAreaRand.append("\n");
                }

                // Clear input fields
                txtTotalStudent.setText("");
                txtTotalStuInGroup.setText("");

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter valid numeric values.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        new RandomStuGroup();
    }
}
