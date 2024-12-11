package databaseftxampp;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

public class XamppDBC extends JFrame implements ActionListener {

    private Connection con = null;
    private Statement stm = null;
    private ResultSet rss = null;
    private Label lbname = new Label("Name :");
    private Label lbgender = new Label("Gender :");
    private Label lbscore = new Label("Score :");
    private Label lbnsubject = new Label("Number subject :");
    private JTextField txtname = new JTextField();
    private Choice chsex = new Choice();
    private JTextField txtscore = new JTextField(20);
    private JTextField txtnsubject = new JTextField();
    private JButton btnadd = new JButton("Add");
    private JButton btndelete = new JButton("Delete");
    private JButton btnupdate = new JButton("Update");
    private JButton btndisplay = new JButton("Display");
    private JButton btnclear = new JButton("Clear");
    private JButton btncancel = new JButton("Cancel");
    private JButton btnfirst = new JButton("First");
    private JButton btnlast = new JButton("Last");
    private JButton btnprevious = new JButton("Previous");
    private JButton btnnext = new JButton("Next");
    private JPanel pnl = new JPanel(new BorderLayout());
    private JPanel pbtn1 = new JPanel(new GridLayout(1, 6, 10, 10));

    private DefaultTableModel tableModel = new DefaultTableModel();
    private JTable dataTable = new JTable(tableModel);

    public XamppDBC() {
        connectDatabase();
        setTitle("Calculator Average");
        JPanel pin = new JPanel(new GridLayout(5, 2, 10, 10));
        JPanel pbtn = new JPanel(new GridLayout(5, 1, 20, 20));
        JPanel pflow = new JPanel(new FlowLayout());
        JPanel pbflow = new JPanel(new FlowLayout());
        pin.add(lbname);
        pin.add(txtname);
        pin.add(lbgender);
        pin.add(chsex);
        chsex.add("Male");
        chsex.add("Female");
        chsex.add("Other");
        pin.add(lbscore);
        pin.add(txtscore);
        pin.add(lbnsubject);
        pin.add(txtnsubject);
        pbtn.add(btnadd);
        pbtn.add(btndelete);
        pbtn.add(btnupdate);
        pbtn.add(btndisplay);
        pbtn1.add(btnnext);
        pbtn1.add(btnprevious);
        pbtn1.add(btnfirst);
        pbtn1.add(btnlast);
        pbtn1.add(btnclear);
        pbtn1.add(btncancel);
        JPanel flow = new JPanel(new FlowLayout());
        flow.add(pbtn1);
        pbflow.add(pbtn);
        pflow.add(pin);
        pnl.add(pflow, BorderLayout.WEST);
        pnl.add(pbflow, BorderLayout.CENTER);
        pnl.add(flow, BorderLayout.SOUTH);
        JPanel newP = new JPanel(new BorderLayout());
        newP.add(pnl, BorderLayout.NORTH);
        newP.add(new JScrollPane(dataTable), BorderLayout.CENTER);
        add(newP);
        btnadd.addActionListener(this);
        btndelete.addActionListener(this);
        btnupdate.addActionListener(this);
        btndisplay.addActionListener(this);
        btnnext.addActionListener(this);
        btnprevious.addActionListener(this);
        btnfirst.addActionListener(this);
        btnlast.addActionListener(this);
        btnclear.addActionListener(this);
        btncancel.addActionListener(this);
        setSize(770, 650);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set up the table model with column names
        tableModel.addColumn("ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Gender");
        tableModel.addColumn("Score");
        tableModel.addColumn("Number Subject");
        tableModel.addColumn("Average");
    }

    private void connectDatabase() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydata1", "root", "");
            stm = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            System.out.println("Connected to the database.");
        } catch (SQLException ex) {
            System.err.println("Failed to connect to the database.");
            ex.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnadd) {
            addData();
        } else if (ae.getSource() == btndelete) {
            deleteData();
        } else if (ae.getSource() == btnupdate) {
            updateData();
        } else if (ae.getSource() == btndisplay) {
            displayData();
        } else if (ae.getSource() == btnnext) {
            next();
        } else if (ae.getSource() == btnprevious) {
            previous();
        } else if (ae.getSource() == btnfirst) {
            first();
        } else if (ae.getSource() == btnlast) {
            last();
        } else if (ae.getSource() == btnclear) {
            clear();
        } else if (ae.getSource() == btncancel) {
            cancel();
        }
    }

    private void addData() {
        try {
            String name = txtname.getText();
            String gender = chsex.getSelectedItem();
            double score = Double.parseDouble(txtscore.getText());
            int nsub = Integer.parseInt(txtnsubject.getText());
            double avg = score / nsub;
            String insertQuery = "INSERT INTO tbdb (name, gender, score, n, average) VALUES ('" + name + "', '" + gender + "', '" + score + "', '" + nsub + "', '" + avg + "')";
            stm.executeUpdate(insertQuery);
            JOptionPane.showMessageDialog(this, "Data inserted successfully.");
        } catch (SQLException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error inserting data: " + ex.getMessage());
        }
    }

    private void deleteData() {
        try {
            int selectedRow = dataTable.getSelectedRow();
            if (selectedRow != -1) {
                long id = (long) tableModel.getValueAt(selectedRow, 0);
                String deleteQuery = "DELETE FROM tbdb WHERE id=" + id;
                stm.executeUpdate(deleteQuery);
                JOptionPane.showMessageDialog(this, "Data deleted successfully.");
                tableModel.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row to delete.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error deleting data: " + ex.getMessage());
        }
    }

    private void updateData() {
        try {
            int selectedRow = dataTable.getSelectedRow();
            if (selectedRow != -1) {
                long id = (long) tableModel.getValueAt(selectedRow, 0);
                String name = txtname.getText();
                String gender = chsex.getSelectedItem();
                double score = Double.parseDouble(txtscore.getText());
                int nsub = Integer.parseInt(txtnsubject.getText());
                double avg = score / nsub;

                String updateQuery = "UPDATE tbdb SET name='" + name + "', gender='" + gender + "', score=" + score + ", n=" + nsub + ", average=" + avg + " WHERE id=" + id;
                stm.executeUpdate(updateQuery);
                JOptionPane.showMessageDialog(this, "Data updated successfully.");
                displayData(); // Refresh table data
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row to update.");
            }
        } catch (SQLException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error updating data: " + ex.getMessage());
        }
    }

    private void displayData() {
        tableModel.setRowCount(0); // Clear existing rows
         cancel();
        try {
            String selectQuery = "SELECT * FROM tbdb";
            rss = stm.executeQuery(selectQuery);
            while (rss.next()) {
                long id = rss.getLong("id");
                String name = rss.getString("name");
                String gender = rss.getString("gender");
                double score = rss.getDouble("score");
                long nsub = rss.getLong("n");
                double avg = rss.getDouble("average");
                tableModel.addRow(new Object[]{id, name, gender, score, nsub, avg});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error displaying data: " + ex.getMessage());
        }
    }

    private void next() {
        try {
            if (rss != null && rss.next()) {
                displayCurrentRecord();
            } else {
                JOptionPane.showMessageDialog(this, "Already at the last record.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error moving to the next record: " + ex.getMessage());
        }
    }

    private void previous() {
        try {
            if (rss != null && rss.previous()) {
                displayCurrentRecord();
            } else {
                JOptionPane.showMessageDialog(this, "Already at the first record.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error moving to the previous record: " + ex.getMessage());
        }
    }

    private void first() {
        try {
            if (rss != null && rss.first()) {
                displayCurrentRecord();
            } else {
                JOptionPane.showMessageDialog(this, "No records found.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error moving to the first record: " + ex.getMessage());
        }
    }

    private void last() {
        try {
            if (rss != null && rss.last()) {
                displayCurrentRecord();
            } else {
                JOptionPane.showMessageDialog(this, "No records found.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error moving to the last record: " + ex.getMessage());
        }
    }

    private void clear() {
        tableModel.setRowCount(0);
        cancel();
    }

    private void cancel() {
        txtname.setText("");
        chsex.select("Male");
        txtscore.setText("");
        txtnsubject.setText("");
    }

    private void displayCurrentRecord() {
        try {
            long id = rss.getLong("id");
            String name = rss.getString("name");
            String gender = rss.getString("gender");
            double score = rss.getDouble("score");
            int nsub = rss.getInt("n");
            // Update text fields with the current record's data
            txtname.setText(name);
            chsex.select(gender);
            txtscore.setText(String.valueOf(score));
            txtnsubject.setText(String.valueOf(nsub));

            // Optionally, sync with the table
            int rowCount = tableModel.getRowCount();
            for (int i = 0; i < rowCount; i++) {
                if ((long) tableModel.getValueAt(i, 0) == id) {
                    dataTable.setRowSelectionInterval(i, i);
                    break;
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error displaying the current record: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new XamppDBC();
    }
}
