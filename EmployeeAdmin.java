package asignmentjavaemployee;

import java.awt.Label;
import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class EmployeeAdmin extends JFrame implements ActionListener {

    int er = 0;
    Connection con = null;
    Statement stm = null;
    ResultSet rss = null;
    String encodedPassword;
    String decodedPassword;
    // Sign Up account
    String namesignup, phoneOremailsignup, passwordsignup, conpasswordsignup;
    // Login account
    String phoneoremaillogin, passwordlogin;

    // Add Employee
    String emfname, emlname, emgender, ememail, emphone, emjob, emtime, emaddress, emstatus, day, month, year, birtdate;
    double emsalary = 0;

    // Add Absent
    String abId, abReason, daysab, monthsab, yearsab, birtdatesab, dayeab, montheab, yeareab, birtdateeab;

    // Search Total absent
    String idToVerifytotal;

    // Edit Employee
    String emidedit, emfnameedit, emlnameedit, emgenderedit, ememailedit, emphoneedit, emjobedit, emtimeedit,
            emaddressedit, emstatusedit, dayedit, monthedit, yearedit, birtdateedit;
    double emsalaryedit = 0;
    int absNumberDays;
    // menu and profile
    private Label lbAdmin = new Label("Employee Admin");
    private Label lbnameAdmin = new Label("Admin");
    private JTextField hide = new JTextField();
    private JButton btnsEm = new JButton("Employee");
    private JButton btnaddEm = new JButton("Add Employee");
    private JButton btnabsentEm = new JButton("Add Absent");
    private JButton btnsabsentEm = new JButton("Total absent");
    private JButton btnsalaryEm = new JButton("Salary");
    private JButton btnlogoutEm = new JButton("Logout");
    private JButton btnattendanceEm = new JButton("Attendance");
    private JPanel west = new JPanel(new BorderLayout());
    private JPanel westAdmin = new JPanel(new BorderLayout());
    private JPanel pl = new JPanel(new GridLayout(8, 1, 15, 15));
    private JPanel pf = new JPanel(new FlowLayout());
    private JPanel pcenter = new JPanel(new FlowLayout());
    String adminImg = "d:/photo/admin3.png";

    // login page
    private JPanel plogin = new JPanel(new BorderLayout());
    private JPanel plog = new JPanel(new GridLayout(8, 1, 5, 5));
    private Label titlelog = new Label("Login account");
    private Label lblog = new Label("LOGIN");
    private Label uEmailorPhone = new Label("Phone number or Email");
    private Label password = new Label("Password");
    private JTextField txtUEmailorPhone = new JTextField();
    private JTextField txtPassword = new JTextField();
    private JButton btnlogin = new JButton("Login");
    private JButton btnsignup = new JButton("Sign Up");

    // Sign up page
    private JPanel psignup = new JPanel(new BorderLayout());
    private JPanel psign = new JPanel(new GridLayout(12, 1, 5, 5));
    private Label titlesign = new Label("Create account");
    private Label lbsign = new Label("SIGN UP");
    private Label signname = new Label("Name");
    private Label signEmailorPhone = new Label("Phone number or Email");
    private Label passwordsign = new Label("Password");
    private Label conpasswordsign = new Label("Confirm Password");
    private JTextField txtsignEmailorPhone = new JTextField();
    private JTextField txtsignPassword = new JTextField();
    private JTextField txtconsignPassword = new JTextField();
    private JTextField txtsignname = new JTextField();
    private JButton btnsign = new JButton("Sign Up");
    private JButton btnlogacc = new JButton("Login");

    // add employee page
    private Label lbtitle = new Label("Add Employee");
    private Label lbfname = new Label("First Name");
    private Label lblname = new Label("Last Name");
    private Label lbsex = new Label("Gender");
    private Label lbbirt = new Label("Birtdate");
    private Label lbcr = new Label("Address");
    private Label lbphone = new Label("Phone Number");
    private Label lbemail = new Label("Email");
    private Label lbjob = new Label("Job title");
    private Label lbstatus = new Label("Status");
    private Label lbtime = new Label("Time");
    private JTextField txtfname = new JTextField(47);
    private JTextField txtlname = new JTextField();
    private JTextField txtphone = new JTextField();
    private JTextField txtemail = new JTextField();
    private Choice chsex = new Choice();
    private Choice chd = new Choice();
    private Choice chm = new Choice();
    private Choice chy = new Choice();
    private Choice chjob = new Choice();
    private Choice chcr = new Choice();
    private Choice chsingleOrFamily = new Choice();
    private Choice chtime = new Choice();
    private JButton btnsave = new JButton("Save");
    private JButton btnupdate = new JButton("Edit");
    private JButton btncancel = new JButton("Cancel");
    private JPanel pnladd = new JPanel(new BorderLayout());
    private JPanel pLR = new JPanel(new BorderLayout());
    private JPanel peladd = new JPanel(new GridLayout(16, 1, 0, 10));
    private JPanel peladd1 = new JPanel(new GridLayout(16, 1, 0, 10));
    private JPanel btnInput = new JPanel(new GridLayout(1, 2, 20, 0));
    private JPanel btnaddflow = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    private JPanel pdate = new JPanel(new GridLayout(1, 3, 7, 0)); // ផ្នែក Choice Birtdate
    private JPanel pdatew = new JPanel(new GridLayout(1, 3, 7, 0)); // ផ្នែក Choice Birtdate

    // edit search employee page
    private Label lbtitleedit = new Label("Edit Employee");
    private Label lbfnameedit = new Label("First Name");
    private Label lblnameedit = new Label("Last Name");
    private Label lbsexedit = new Label("Gender");
    private Label lbbirtedit = new Label("Birtdate");
    private Label lbcredit = new Label("Current Residence");
    private Label lbphoneedit = new Label("Phone Number");
    private Label lbemailedit = new Label("Email");
    private Label lbjobedit = new Label("Job Title");
    private Label lbstatusedit = new Label("Status");
    private Label lbtimeedit = new Label("Time");
    private JTextField txtid1edit = new JTextField(47);
    private JTextField txtfnameedit = new JTextField();
    private JTextField txtlnameedit = new JTextField();
    private JTextField txtphoneedit = new JTextField();
    private JTextField txtemailedit = new JTextField();
    private Choice chsexedit = new Choice();
    private Choice chdedit = new Choice();
    private Choice chmedit = new Choice();
    private Choice chyedit = new Choice();
    private Choice chjobedit = new Choice();
    private Choice chaddedit = new Choice();
    private Choice chcredit = new Choice();
    private Choice chsingleOrFamilyedit = new Choice();
    private Choice chtimeedit = new Choice();
    private JButton btnupdateedit = new JButton("Edit");
    private JButton btncanceledit = new JButton("Cancel");
    private JPanel pnladdedit = new JPanel(new BorderLayout());
    private JPanel pLRedit = new JPanel(new BorderLayout());
    private JPanel peladdedit = new JPanel(new GridLayout(16, 1, 0, 10));
    private JPanel peladd1edit = new JPanel(new GridLayout(16, 1, 0, 10));
    private JPanel btnInputedit = new JPanel(new GridLayout(1, 2, 20, 0));
    private JPanel btnaddflowedit = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    private JPanel pdateedit = new JPanel(new GridLayout(1, 3, 7, 0)); // ផ្នែក Choice Birtdate
    private JPanel pdatewedit = new JPanel(new GridLayout(1, 3, 7, 0)); // ផ្នែក Choice Birtdate

    // Show employee in table page
    private DefaultTableModel tb = new DefaultTableModel(new String[]{"ID", "F.Name", "L.Name", "Gender", "Birtdate",
        "Phone", "Email", "Job Title", "Time", "Address", "Status", "Create"}, 0);
    private JTable table = new JTable(tb);
    private JScrollPane scrll;
    private JButton btnsearchsearch = new JButton("Cancel");
    private JButton btnsearch = new JButton("Search");
    private JTextField txtsearch = new JTextField(30);
    private JTextField h = new JTextField(59);
    private Label lbShowEm = new Label("Employee");
    private JPanel pSEm = new JPanel(new BorderLayout());
    private JPanel btf = new JPanel(new FlowLayout());
    private JTextField txthide = new JTextField();
    private JButton btnEditEm = new JButton("Edit");
    private JButton btnremoveEm = new JButton("Remove");
    private JPanel btnedit = new JPanel(new GridLayout(1, 2, 20, 0));
    private JPanel btneditem = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    // input absent page
    private Label lbinAbsentEm = new Label("Absent Employee");
    private Label lbinputId = new Label("Id");
    private JTextField txtid = new JTextField(40);
    private Label lbinputdateStart = new Label("Stop from");
    private Choice ds = new Choice();
    private Choice ms = new Choice();
    private Choice ys = new Choice();
    private Label lbinputdateEnd = new Label("To");
    private Choice de = new Choice();
    private Choice me = new Choice();
    private Choice ye = new Choice();
    private Label nabsent = new Label("Number of days");
    private JTextField txtsbday = new JTextField(40);
    private Label lbreason = new Label("Reason");
    private JTextField txtreason = new JTextField();
    private JPanel pinabsent = new JPanel(new BorderLayout());
    private JPanel pab = new JPanel(new BorderLayout());
    private JPanel pabin = new JPanel(new BorderLayout());
    private JPanel pabtb = new JPanel(new BorderLayout());
    private JPanel pdateabs = new JPanel(new GridLayout(1, 3, 10, 10));
    private JPanel pdateabe = new JPanel(new GridLayout(1, 3, 10, 10));
    private JPanel pelabsent = new JPanel(new GridLayout(7, 1, 0, 5));
    private JPanel pelabsent2 = new JPanel(new GridLayout(7, 1, 0, 5));
    private JPanel btnInputab = new JPanel(new GridLayout(1, 2, 20, 0));
    private JPanel btnaddabsent = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    private JButton btnsaveab = new JButton("Save");
    private JButton btncancelab = new JButton("Cancel");
    private JButton btnremoveeditab = new JButton("Remove");
    private DefaultTableModel tbab = new DefaultTableModel(
            new String[]{"NO", "ID", "Name", "Number of days", "From", "To", "Reason", "Create"}, 0);
    private JTable tableab = new JTable(tbab);
    private JScrollPane scrllab;
    private JTextField txthideab = new JTextField(98);

    // Total Absent Employee page
    private DefaultTableModel tbabsent = new DefaultTableModel(new String[]{"ID", "Name", "Total absents"}, 0);
    private JTable tableabsent = new JTable(tbabsent);
    private JScrollPane scrll1;
    private JButton btnsearchab = new JButton("Search");
    private JButton btnsearchabs = new JButton("Cancel");
    private JTextField txtsearchab = new JTextField(30);
    private JTextField hab = new JTextField(59);
    private Label lbAbsentEm = new Label("Total Absent");
    private JPanel pabsent = new JPanel(new BorderLayout());
    private JPanel btflow = new JPanel(new FlowLayout());
    private JPanel btneditAb = new JPanel(new GridLayout(1, 2, 20, 0));
    private JPanel btneditAbem = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    // Salary Employee page
    private DefaultTableModel tbsalary = new DefaultTableModel(new String[]{"ID", "Name", "Salary"}, 0);
    private JTable tablesalary = new JTable(tbsalary);
    private JScrollPane scrllsalary;
    private JButton btnsearchsa = new JButton("Search");
    private JButton btncancelsa = new JButton("Cancel");
    private JTextField txtsearchsa = new JTextField(30);
    private JTextField hsa = new JTextField(59);
    private Label lbsalaryEm = new Label("Salary Employee");
    private JPanel psalary = new JPanel(new BorderLayout());
    private JPanel btflowsalary = new JPanel(new FlowLayout());
    private JPanel btneditsa = new JPanel(new GridLayout(1, 2, 20, 0));
    private JPanel btneditsaem = new JPanel(new FlowLayout(FlowLayout.RIGHT));

    // Attendance page
    private Label lbinattendanceEm = new Label("Attendance Employee");
    private Label lbinputIdem = new Label("Id");
    private JTextField txtidem = new JTextField(40);
    private JPanel pinattendance = new JPanel(new BorderLayout());
    JPanel attendance = new JPanel(new BorderLayout());
    private JPanel pelattendance = new JPanel(new GridLayout(2, 1, 0, 5));
    private JPanel btnattendance = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    private JPanel btnattendance1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    private JButton btnsaveattendance = new JButton("Save");
    private JButton btncancelattendance = new JButton("Cancel");
    private JButton btnremoveattendance = new JButton("Remove");
    private DefaultTableModel tbattendance = new DefaultTableModel(new String[]{"ID", "Name", "Status", "Date"}, 0);
    private JTable tableattendance = new JTable(tbattendance);
    private JScrollPane scrllattendance;
    private JTextField txthideattendance = new JTextField(98);

    public EmployeeAdmin() {
        super("Employee");
        connectDatabase();
        hideColumn(tableab, 0); // hide colum on table absent
        add(lbAdmin, BorderLayout.NORTH);
        lbAdmin.setFont(new Font("Consolas", Font.PLAIN, 25));
        lbAdmin.setForeground(Color.BLACK);
        lbAdmin.setBackground(Color.GREEN);
        JLabel imageAdmin = new JLabel(new ImageIcon(adminImg));
        westAdmin.add(imageAdmin, BorderLayout.NORTH);
        westAdmin.add(lbnameAdmin, BorderLayout.CENTER);
        lbnameAdmin.setFont(new Font("Consolas", Font.BOLD, 15));
        lbnameAdmin.setForeground(Color.WHITE);
        lbnameAdmin.setAlignment(Label.CENTER);
        westAdmin.setBackground(Color.BLACK);
        westAdmin.add(hide, BorderLayout.SOUTH);
        hide.setEnabled(false);
        hide.setBorder(null);
        hide.setBackground(Color.BLACK);
        west.add(westAdmin, BorderLayout.NORTH);
        pl.add(btnaddEm);
        pl.add(btnsEm);
        pl.add(btnattendanceEm);
        pl.add(btnabsentEm);
        pl.add(btnsabsentEm);
        pl.add(btnsalaryEm);
        pl.add(btnlogoutEm);
        west.add(pl, BorderLayout.CENTER);
        pf.setBackground(Color.BLACK);
        pl.setBackground(Color.BLACK);
        btnsEm.setBackground(Color.BLACK);
        btnaddEm.setBackground(Color.BLACK);
        btnabsentEm.setBackground(Color.BLACK);
        btnsabsentEm.setBackground(Color.BLACK);
        btnsalaryEm.setBackground(Color.BLACK);
        btnlogoutEm.setBackground(Color.RED);
        btnattendanceEm.setBackground(Color.BLACK);
        btnsEm.setForeground(Color.WHITE);
        btnaddEm.setForeground(Color.WHITE);
        btnabsentEm.setForeground(Color.WHITE);
        btnsabsentEm.setForeground(Color.WHITE);
        btnsalaryEm.setForeground(Color.WHITE);
        btnlogoutEm.setForeground(Color.WHITE);
        btnattendanceEm.setForeground(Color.WHITE);
        pf.add(west);
        add(pf, BorderLayout.WEST);

        // login account
        plogin.add(titlelog, BorderLayout.NORTH);
        titlelog.setFont(new Font("Consolas", Font.PLAIN, 18));
        titlelog.setForeground(Color.BLUE);
        titlelog.setBackground(Color.YELLOW);
        JTextField loghidetxt = new JTextField(98);
        loghidetxt.setBackground(Color.WHITE);
        loghidetxt.setBorder(null);
        loghidetxt.setEnabled(false);
        plogin.add(loghidetxt, BorderLayout.CENTER);
        JPanel plogsouth = new JPanel(new BorderLayout());
        JTextArea areahide = new JTextArea(5, 10);
        areahide.setBackground(Color.WHITE);
        areahide.setBorder(null);
        areahide.setEnabled(false);
        plogsouth.add(areahide, BorderLayout.NORTH);
        plog.add(lblog);
        JTextField ha = new JTextField();
        ha.setBorder(null);
        ha.setEnabled(false);
        JTextField ha1 = new JTextField();
        ha1.setBorder(null);
        ha1.setEnabled(false);
        plog.add(ha);
        plog.add(uEmailorPhone);
        plog.add(txtUEmailorPhone);
        plog.add(password);
        plog.add(txtPassword);
        JPanel btnlog = new JPanel(new GridLayout(1, 2, 10, 10));
        btnlog.add(this.btnlogin);
        btnlog.add(this.btnsignup);
        plog.add(ha1);
        plog.add(btnlog);
        JPanel inlog = new JPanel(new BorderLayout());
        JTextField hideR = new JTextField(20);
        JTextField hideL = new JTextField(20);
        hideR.setBorder(null);
        hideR.setEnabled(false);
        hideL.setBorder(null);
        hideL.setEnabled(false);
        inlog.add(hideR, BorderLayout.WEST);
        inlog.add(hideL, BorderLayout.EAST);
        inlog.add(plog, BorderLayout.CENTER);
        lblog.setAlignment(Label.CENTER);
        lblog.setFont(new Font("Consolas", Font.PLAIN, 18));
        lblog.setForeground(Color.BLUE);
        lblog.setBackground(Color.lightGray);
        plogsouth.add(inlog, BorderLayout.CENTER);
        plogin.add(plogsouth, BorderLayout.SOUTH);
        plog.setBackground(Color.WHITE);
        btnlog.setBackground(Color.WHITE);
        btnlogin.setBackground(Color.GREEN);
        pcenter.add(plogin);

        // SignUp account
        psignup.add(titlesign, BorderLayout.NORTH);
        titlesign.setFont(new Font("Consolas", Font.PLAIN, 18));
        titlesign.setForeground(Color.BLUE);
        titlesign.setBackground(Color.YELLOW);
        JTextField signhidetxt = new JTextField(98);
        signhidetxt.setBackground(Color.WHITE);
        signhidetxt.setBorder(null);
        signhidetxt.setEnabled(false);
        psignup.add(signhidetxt, BorderLayout.CENTER);
        JPanel psignsouth = new JPanel(new BorderLayout());
        JTextArea areahide1 = new JTextArea(5, 10);
        areahide1.setBackground(Color.WHITE);
        areahide1.setBorder(null);
        areahide1.setEnabled(false);
        psignsouth.add(areahide1, BorderLayout.NORTH);
        psign.add(lbsign);
        JTextField hasign = new JTextField();
        hasign.setBorder(null);
        hasign.setEnabled(false);
        JTextField hasign1 = new JTextField();
        hasign1.setBorder(null);
        hasign1.setEnabled(false);
        psign.add(hasign);
        psign.add(signname);
        psign.add(txtsignname);
        psign.add(signEmailorPhone);
        psign.add(txtsignEmailorPhone);
        psign.add(passwordsign);
        psign.add(txtsignPassword);
        psign.add(conpasswordsign);
        psign.add(txtconsignPassword);
        JPanel btnsigna = new JPanel(new GridLayout(1, 2, 10, 10));
        btnsigna.add(this.btnsign);
        btnsigna.add(this.btnlogacc);
        psign.add(hasign1);
        psign.add(btnsigna);
        JPanel insign = new JPanel(new BorderLayout());
        JTextField hideRs = new JTextField(20);
        JTextField hideLs = new JTextField(20);
        hideRs.setBorder(null);
        hideRs.setEnabled(false);
        hideLs.setBorder(null);
        hideLs.setEnabled(false);
        insign.add(hideRs, BorderLayout.WEST);
        insign.add(hideLs, BorderLayout.EAST);
        insign.add(psign, BorderLayout.CENTER);
        lbsign.setAlignment(Label.CENTER);
        lbsign.setFont(new Font("Consolas", Font.PLAIN, 18));
        lbsign.setForeground(Color.BLUE);
        lbsign.setBackground(Color.lightGray);
        psignsouth.add(insign, BorderLayout.CENTER);
        psignup.add(psignsouth, BorderLayout.SOUTH);
        psign.setBackground(Color.WHITE);
        btnsigna.setBackground(Color.WHITE);
        btnlogacc.setBackground(Color.GREEN);
        pcenter.add(psignup);

        // add employee
        pnladd.add(lbtitle, BorderLayout.NORTH);
        lbtitle.setFont(new Font("Consolas", Font.PLAIN, 18));
        lbtitle.setForeground(Color.BLUE);
        lbtitle.setBackground(Color.YELLOW);
        for (int d = 1; d <= 31; d++) {
            String day = String.format("%02d", d);
            chd.add(day);
            chdedit.add(day);
            ds.add(day);
            de.add(day);
        }
        String mon[] = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        for (int m = 0; m < 12; m++) {
            String month = String.format("%s", mon[m]);
            chm.add(month);
            chmedit.add(month);
            ms.add(month);
            me.add(month);
        }
        for (int y = 2025; y >= 1980; y--) {
            String year = String.format("%d", y);
            chy.add(year);
            chyedit.add(year);
            ys.add(year);
            ye.add(year);
        }
        String job[] = {"Customer Service", "Loan Processing", "Compliance", "Security", "IT Support", "Networking",
            "Front-End", "Back-End"};
        for (int j = 0; j < 8; j++) {
            String tjob = String.format("%s", job[j]);
            chjob.add(tjob);
            chjobedit.add(tjob);
        }
        String address[] = {"Banteay Meanchey", "Battambang", "Kampong Cham", "Kampong Chhnang", "Kampong Speu",
            "Kampong Thom", "Kampot", "Kandal", "Kep", "Koh Kong", "Kratié", "Mondulkiri", "Oddar Meanchey",
            "Pailin", "Phnom Penh", "Preah Sihanouk", "Preah Vihear", "Pursat", "Ratanakiri", "Siem Reap",
            "Stung Treng", "Svay Rieng", "Takéo", "Tboung Khmum"};
        for (int a = 0; a < 24; a++) {
            String addr = String.format("%s", address[a]);
            chcr.add(addr);
            chcredit.add(addr);
        }
        peladd.add(new JLabel());
        peladd.add(lbfname);
        peladd.add(txtfname);
        peladd.add(lblname);
        peladd.add(txtlname);
        peladd.add(lbsex);
        chsex.add("Male");
        chsex.add("Female");
        peladd.add(chsex);
        peladd.add(lbbirt);
        pdate.add(chd);
        pdate.add(chm);
        pdate.add(chy);
        peladd.add(pdate);
        pdate.setBackground(Color.WHITE);
        peladd.add(lbemail);
        peladd.add(txtemail);
        peladd.add(lbphone);
        peladd.add(txtphone);
        pdatew.setBackground(Color.WHITE);
        pLR.add(peladd, BorderLayout.WEST);
        peladd1.setBackground(Color.WHITE);
        peladd1.add(new JLabel());
        peladd1.add(lbjob);
        peladd1.add(chjob);
        peladd1.add(lbtime);
        chtime.add("Full time");
        chtime.add("Part time");
        peladd1.add(chtime);
        peladd1.add(lbcr);
        peladd1.add(chcr);
        peladd1.add(lbstatus);
        chsingleOrFamily.add("Single");
        chsingleOrFamily.add("Family");
        peladd1.add(chsingleOrFamily);
        JTextField tx1 = new JTextField(47);
        peladd1.add(tx1);
        JPanel panelHide = new JPanel(new BorderLayout());
        JTextField tx = new JTextField(4);
        tx.setBorder(null);
        tx.setEnabled(false);
        tx1.setBorder(null);
        tx1.setEnabled(false);
        panelHide.add(tx, BorderLayout.WEST);
        panelHide.add(peladd1, BorderLayout.EAST);
        pLR.add(panelHide, BorderLayout.EAST);
        pnladd.add(pLR, BorderLayout.CENTER);
        peladd.setBackground(Color.WHITE);
        pcenter.setBackground(Color.WHITE);
        btnInput.add(btnsave);
        btnupdate.setBackground(Color.YELLOW);
        btnupdate.setFont(new Font("SanSari", Font.BOLD, 15));
        btnupdate.setVisible(false);
        btnsave.setFont(new Font("SanSari", Font.BOLD, 15));
        btncancel.setFont(new Font("SanSari", Font.BOLD, 15));
        btnsave.setBackground(Color.GREEN);
        btnInput.add(btncancel);
        btnaddflow.add(btnInput);
        pnladd.add(btnaddflow, BorderLayout.SOUTH);
        pcenter.add(pnladd);
        btnInput.setBackground(Color.WHITE);
        btnaddflow.setBackground(Color.WHITE);

        //Edit employee
        pnladdedit.add(lbtitleedit, BorderLayout.NORTH);
        lbtitleedit.setFont(new Font("Consolas", Font.PLAIN, 18));
        lbtitleedit.setForeground(Color.BLUE);
        lbtitleedit.setBackground(Color.YELLOW);
        peladdedit.add(txtid1edit);
        txtid1edit.setVisible(false);
        peladdedit.add(lbfnameedit);
        peladdedit.add(txtfnameedit);
        peladdedit.add(lblnameedit);
        peladdedit.add(txtlnameedit);
        peladdedit.add(lbsexedit);
        chsexedit.add("Male");
        chsexedit.add("Female");
        peladdedit.add(chsexedit);
        peladdedit.add(lbbirtedit);
        pdateedit.add(chdedit);
        pdateedit.add(chmedit);
        pdateedit.add(chyedit);
        peladdedit.add(pdateedit);
        pdateedit.setBackground(Color.WHITE);
        peladdedit.add(lbemailedit);
        peladdedit.add(txtemailedit);
        peladdedit.add(lbphoneedit);
        peladdedit.add(txtphoneedit);
        pdatewedit.setBackground(Color.WHITE);
        pLRedit.add(peladdedit, BorderLayout.WEST);
        peladd1edit.setBackground(Color.WHITE);
        JTextField idh = new JTextField();
        idh.setVisible(false);
        peladd1edit.add(idh);
        peladd1edit.add(lbjobedit);
        peladd1edit.add(chjobedit);
        peladd1edit.add(lbtimeedit);
        chtimeedit.add("Full time");
        chtimeedit.add("Part time");
        peladd1edit.add(chtimeedit);
        peladd1edit.add(lbcredit);
        peladd1edit.add(chcredit);
        peladd1edit.add(lbstatusedit);
        chsingleOrFamilyedit.add("Single");
        chsingleOrFamilyedit.add("Family");
        peladd1edit.add(chsingleOrFamilyedit);
        JTextField tx1editedit = new JTextField(47);
        peladd1edit.add(tx1editedit);
        JPanel panelHideedit = new JPanel(new BorderLayout());
        JTextField txeditedit = new JTextField(4);
        txeditedit.setBorder(null);
        txeditedit.setEnabled(false);
        tx1editedit.setBorder(null);
        tx1editedit.setEnabled(false);
        panelHideedit.add(txeditedit, BorderLayout.WEST);
        panelHideedit.add(peladd1edit, BorderLayout.EAST);
        pLRedit.add(panelHideedit, BorderLayout.EAST);
        pnladdedit.add(pLRedit, BorderLayout.CENTER);
        peladdedit.setBackground(Color.WHITE);
        pcenter.setBackground(Color.WHITE);
        btnInputedit.add(btnupdateedit);
        btnupdateedit.setBackground(Color.YELLOW);
        btnupdateedit.setFont(new Font("SanSari", Font.BOLD, 15));
        btncanceledit.setFont(new Font("SanSari", Font.BOLD, 15));
        btnInputedit.add(btncanceledit);
        btnaddflowedit.add(btnInputedit);
        pnladdedit.add(btnaddflowedit, BorderLayout.SOUTH);
        pcenter.add(pnladdedit);
        btnInputedit.setBackground(Color.WHITE);
        btnaddflowedit.setBackground(Color.WHITE);
        // Show Employee
        pSEm.add(lbShowEm, BorderLayout.NORTH);
        lbShowEm.setFont(new Font("Consolas", Font.PLAIN, 18));
        lbShowEm.setForeground(Color.BLUE);
        lbShowEm.setBackground(Color.YELLOW);
        btf.add(h);
        h.setBorder(null);
        h.setEnabled(false);
        h.setBackground(null);
        btf.add(btnsearchsearch);
        btnsearchsearch.setVisible(false);
        btf.add(btnsearch);
        btf.add(txtsearch);
        btf.setBackground(Color.WHITE);
        txthide.setBackground(Color.WHITE);
        txthide.setBorder(null);
        txthide.setEnabled(false);
        pSEm.add(btf, BorderLayout.CENTER);
        JPanel ptbSem = new JPanel(new BorderLayout());
        scrll = new JScrollPane(table);
        ptbSem.add(scrll, BorderLayout.NORTH);
        btnedit.add(btnEditEm);
        btnEditEm.setBackground(Color.YELLOW);
        btnedit.add(btnremoveEm);
        btnremoveEm.setBackground(Color.red);
        btneditem.add(btnedit);
        btnEditEm.setFont(new Font("SanSari", Font.BOLD, 15));
        btnremoveEm.setFont(new Font("SanSari", Font.BOLD, 15));
        ptbSem.add(btneditem, BorderLayout.CENTER);
        btnedit.setBackground(Color.WHITE);
        btneditem.setBackground(Color.WHITE);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setBackground(Color.GREEN);
        table.getTableHeader().setForeground(Color.BLACK);
        pSEm.add(ptbSem, BorderLayout.SOUTH);
        pSEm.setBackground(Color.WHITE);
        pcenter.add(pSEm);
        // Attendance
        JPanel fulltb = new JPanel(new BorderLayout());
        fulltb.add(lbinattendanceEm, BorderLayout.NORTH);
        lbinattendanceEm.setFont(new Font("Consolas", Font.PLAIN, 18));
        lbinattendanceEm.setForeground(Color.BLUE);
        lbinattendanceEm.setBackground(Color.YELLOW);
        pelattendance.add(lbinputIdem);
        pelattendance.add(txtidem);
        pinattendance.add(pelattendance, BorderLayout.CENTER);
        btnattendance.add(btnsaveattendance);
        btnattendance.add(btncancelattendance);
        btnsaveattendance.setBackground(Color.GREEN);
        btnsaveattendance.setFont(new Font("SanSari", Font.BOLD, 15));
        btncancelattendance.setFont(new Font("SanSari", Font.BOLD, 15));
        pinattendance.add(btnattendance, BorderLayout.SOUTH);
        txthideattendance.setBorder(null);
        txthideattendance.setEnabled(false);
        txthideattendance.setBackground(Color.WHITE);
        JPanel pr = new JPanel(new BorderLayout());
        pr.setBackground(Color.WHITE);
        pr.add(pinattendance, BorderLayout.WEST);
        JPanel tbattendance = new JPanel(new BorderLayout());
        tbattendance.add(txthideattendance, BorderLayout.NORTH);
        scrllattendance = new JScrollPane(tableattendance);
        tbattendance.add(scrllattendance, BorderLayout.CENTER);
        fulltb.add(pr, BorderLayout.CENTER);
        fulltb.add(tbattendance, BorderLayout.SOUTH);
        attendance.add(fulltb, BorderLayout.NORTH);
        tableattendance.getTableHeader().setReorderingAllowed(false);
        tableattendance.getTableHeader().setBackground(Color.GREEN);
        tableattendance.getTableHeader().setForeground(Color.BLACK);
        btnattendance1.add(btnremoveattendance);
        btnremoveattendance.setFont(new Font("SanSari", Font.BOLD, 15));
        btnremoveattendance.setBackground(Color.red);
        attendance.add(btnattendance1, BorderLayout.CENTER);
        btnattendance1.setBackground(Color.WHITE);
        btnattendance.setBackground(Color.WHITE);
        pelattendance.setBackground(Color.WHITE);
        pcenter.add(attendance);

        // Input Absent
        pinabsent.add(lbinAbsentEm, BorderLayout.NORTH);
        lbinAbsentEm.setFont(new Font("Consolas", Font.PLAIN, 18));
        lbinAbsentEm.setForeground(Color.BLUE);
        lbinAbsentEm.setBackground(Color.YELLOW);
        pelabsent.add(lbinputId);
        pelabsent.add(txtid);
        pelabsent.add(nabsent);
        pelabsent.add(txtsbday);
        pelabsent2.add(lbinputdateStart);
        pdateabs.add(ds);
        pdateabs.add(ms);
        pdateabs.add(ys);
        pelabsent2.add(pdateabs);
        pelabsent2.add(lbinputdateEnd);
        pdateabe.setBackground(Color.WHITE);
        pdateabs.setBackground(Color.WHITE);
        pdateabe.add(de);
        pdateabe.add(me);
        pdateabe.add(ye);
        pelabsent2.add(pdateabe);
        pelabsent.add(lbreason);
        pelabsent.add(txtreason);
        JPanel addab = new JPanel(new BorderLayout());
        addab.add(pelabsent, BorderLayout.WEST);
        JPanel center = new JPanel(new BorderLayout());
        JTextField t = new JTextField(4);
        center.add(t, BorderLayout.WEST);
        t.setBorder(null);
        t.setEnabled(false);
        center.add(pelabsent2, BorderLayout.CENTER);
        pelabsent2.setBackground(Color.WHITE);
        addab.add(center, BorderLayout.CENTER);
        txtreason.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        pabin.add(addab, BorderLayout.NORTH);
        pinabsent.add(addab, BorderLayout.CENTER);
        pinabsent.setBackground(Color.WHITE);
        btnInputab.add(btnsaveab);
        btnsaveab.setBackground(Color.GREEN);
        btnInputab.add(btncancelab);
        btnaddabsent.add(btnInputab);
        btnsaveab.setFont(new Font("SanSari", Font.BOLD, 15));
        btncancelab.setFont(new Font("SanSari", Font.BOLD, 15));
        pinabsent.add(btnaddabsent, BorderLayout.SOUTH);
        pab.add(pinabsent, BorderLayout.CENTER);
        txthideab.setBackground(Color.lightGray);
        txthideab.setBorder(null);
        txthideab.setEnabled(false);
        txthideab.setBackground(Color.WHITE);
        pabtb.add(txthideab, BorderLayout.NORTH);
        scrllab = new JScrollPane(tableab);
        tableab.getTableHeader().setReorderingAllowed(false);
        tableab.getTableHeader().setBackground(Color.GREEN);
        tableab.getTableHeader().setForeground(Color.BLACK);
        pabtb.add(scrllab, BorderLayout.CENTER);
        JPanel absentin = new JPanel(new BorderLayout());
        absentin.add(pabtb, BorderLayout.CENTER);
        JPanel flowab = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        flowab.add(btnremoveeditab);
        flowab.setBackground(Color.WHITE);
        btnremoveeditab.setFont(new Font("SanSari", Font.BOLD, 15));
        btnremoveeditab.setBackground(Color.red);
        absentin.add(flowab, BorderLayout.SOUTH);
        pab.add(absentin, BorderLayout.SOUTH);
        pcenter.add(pab);
        btnInputab.setBackground(Color.WHITE);
        btnaddabsent.setBackground(Color.WHITE);
        pelabsent.setBackground(Color.WHITE);
        // Total Absent Employee
        pabsent.add(lbAbsentEm, BorderLayout.NORTH);
        lbAbsentEm.setFont(new Font("Consolas", Font.PLAIN, 18));
        lbAbsentEm.setForeground(Color.BLUE);
        lbAbsentEm.setBackground(Color.YELLOW);
        btflow.add(hab);
        hab.setBorder(null);
        hab.setEnabled(false);
        hab.setBackground(null);
        btflow.add(btnsearchab);
        btflow.add(btnsearchabs);
        btnsearchabs.setVisible(false);
        btflow.add(txtsearchab);
        btflow.setBackground(Color.WHITE);
        pabsent.add(btflow, BorderLayout.CENTER);
        JPanel absent = new JPanel(new BorderLayout());
        scrll1 = new JScrollPane(tableabsent);
        absent.add(scrll1, BorderLayout.NORTH);
        btneditAb.setBackground(Color.WHITE);
        btneditAbem.setBackground(Color.WHITE);
        absent.add(btneditAbem, BorderLayout.CENTER);
        tableabsent.getTableHeader().setReorderingAllowed(false);
        tableabsent.getTableHeader().setBackground(Color.GREEN);
        tableabsent.getTableHeader().setForeground(Color.BLACK);
        pabsent.add(absent, BorderLayout.SOUTH);
        pcenter.add(pabsent);
        // Salary
        psalary.add(lbsalaryEm, BorderLayout.NORTH);
        lbsalaryEm.setFont(new Font("Consolas", Font.PLAIN, 18));
        lbsalaryEm.setForeground(Color.BLUE);
        lbsalaryEm.setBackground(Color.YELLOW);
        btflowsalary.add(hsa);
        hsa.setBorder(null);
        hsa.setEnabled(false);
        hsa.setBackground(null);
        btflowsalary.add(btnsearchsa);
        btflowsalary.add(btncancelsa);
        btncancelsa.setVisible(false);
        btflowsalary.add(txtsearchsa);
        btflowsalary.setBackground(Color.WHITE);
        psalary.add(btflowsalary, BorderLayout.CENTER);
        JPanel salary = new JPanel(new BorderLayout());
        scrllsalary = new JScrollPane(tablesalary);
        salary.add(scrllsalary, BorderLayout.NORTH);
        btneditsaem.add(btneditsa);
        btneditsa.setBackground(Color.WHITE);
        btneditsaem.setBackground(Color.WHITE);
        salary.add(btneditsaem, BorderLayout.CENTER);
        tablesalary.getTableHeader().setReorderingAllowed(false);
        tablesalary.getTableHeader().setBackground(Color.GREEN);
        tablesalary.getTableHeader().setForeground(Color.BLACK);
        psalary.add(salary, BorderLayout.SOUTH);
        psalary.setBackground(Color.WHITE);
        pcenter.add(psalary);
        add(pcenter, BorderLayout.CENTER);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
        btnsEm.addActionListener(this);
        btnabsentEm.addActionListener(this);
        btnsabsentEm.addActionListener(this);
        btnaddEm.addActionListener(this);
        btnsalaryEm.addActionListener(this);
        btnlogoutEm.addActionListener(this);
        btnattendanceEm.addActionListener(this);
        btnlogacc.addActionListener(this);
        btnsignup.addActionListener(this);
        btnlogin.addActionListener(this);// User login(admin)
        btnlogoutEm.addActionListener(this);// User logout(admin)
        btnsign.addActionListener(this);
        btnsave.addActionListener(this);// Save employee
        btnEditEm.addActionListener(this);
        btnupdate.addActionListener(this);// Update Employee
        btnremoveEm.addActionListener(this);// Remove Employee
        btncancel.addActionListener(this);// Cancel Employee
        btnsaveab.addActionListener(this);// Add Absent
        btncancelab.addActionListener(this);// Cancel Absent
        btnsaveattendance.addActionListener(this);// add attendance
        btnremoveattendance.addActionListener(this);// Delete attendance
        btncancelattendance.addActionListener(this);// Cancel attendance
        btnsearch.addActionListener(this);// Search Employee
        btnsearchsearch.addActionListener(this);
        btnupdateedit.addActionListener(this);// Edit Employee
        btnremoveeditab.addActionListener(this);// Remove absent
        btnsearchabs.addActionListener(this);// Serach ToTal
        btnsearchab.addActionListener(this);
        btncancelsa.addActionListener(this);
        btnsearchsa.addActionListener(this);
        btncanceledit.addActionListener(this);// Cancel Edit Employee
        setVisible(true);
        setSize(1300, 850);
        setLocationRelativeTo(null);
        btnsEm.setVisible(false);
        btnaddEm.setVisible(false);
        btnabsentEm.setVisible(false);
        btnsabsentEm.setVisible(false);
        btnsalaryEm.setVisible(false);
        btnlogoutEm.setVisible(false);
        btnattendanceEm.setVisible(false);
        pSEm.setVisible(false);
        pnladd.setVisible(false);
        pabsent.setVisible(false);
        pab.setVisible(false);
        psalary.setVisible(false);
        attendance.setVisible(false);
        plogin.setVisible(true);
        psignup.setVisible(false);
        pnladdedit.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnsave) { // Add Employee
            addEmployee();
        }
        if (ae.getSource() == btncancel) {
            clearfiledAddEm();
        }
        if (ae.getSource() == btnEditEm) {
            editEmployee();
        }
        if (ae.getSource() == btnsaveattendance) { // Attendance
            attendance();
        }
        if (ae.getSource() == btnremoveattendance) { // remove attendance
            removeAttendance();
        }
        if (ae.getSource() == btncancelattendance) {
            txtidem.setText("");
        }
        if (ae.getSource() == btnupdateedit) { // Update Employee
            updateEmployee();
        }
        if (ae.getSource() == btncanceledit) {
            txtsearch.setText("");
            table.getSelectionModel().clearSelection();
            clearfiledEditEm();
            showPageshowEmployee();
        }
        if (ae.getSource() == btnremoveEm) { // Remove Employee
            removeEmployee();
        }
        if (ae.getSource() == btnsearch) { // Search Employee
            searchEmployee();
        }
        if (ae.getSource() == btnsearchsearch) {// Cancel search
            txtsearch.setText("");
            btnsearch.setVisible(true);
            btnsearchsearch.setVisible(false);
            showPageshowEmployee();
        }
        if (ae.getSource() == btnsaveab) { // Add absence
            absentEmployee();// function add absent
        }
        if (ae.getSource() == btncancelab) {
            clearAbsentfild();// function clear filed Absent
        }
        if (ae.getSource() == btnremoveeditab) { // Remove absent
            removeAbsent();// function raemove absent
        }
        if (ae.getSource() == btnsearchab) {
            searchAbsent();// function search total absent
        }
        if (ae.getSource() == btnsearchabs) { // Cancel search Total Absent
            btnsearchab.setVisible(true);
            btnsearchabs.setVisible(false);
            txtsearchab.setText("");
            showPageTotalAbsent();// function show page total absent
        }
        if (ae.getSource() == btnsEm) {
            showPageshowEmployee();// function show page Show Employee
        }
        if (ae.getSource() == btnaddEm) {// Button menu Add employee
            showPageAddEm();// function show page add employee
        }
        if (ae.getSource() == btnabsentEm) {// Button menu Add absent
            showPageAddAnsent();// function show page add absent
        }
        if (ae.getSource() == btnsabsentEm) {// Button menu Total absent
            showPageTotalAbsent();// function show page total absent
        }
        if (ae.getSource() == btnsalaryEm) {
            showPageSalary();
        }
        if (ae.getSource() == btnattendanceEm) { // Button meneu Attendance
            showPageattendance();// function show page attendance
        }
        if (ae.getSource() == btnlogin) { // Admin login
            login();
        }
        if (ae.getSource() == btnsign) { // Admin Sign Up
            signup();
        }
        if (ae.getSource() == btnlogoutEm) {
            buttonlogout();// show page login hide button menu and other page
            lbnameAdmin.setText("");
        }
        if (ae.getSource() == btnlogacc) {// show page login
            plogin.setVisible(true);
            psignup.setVisible(false);
        }
        if (ae.getSource() == btnsignup) {// show page Signup
            plogin.setVisible(false);
            psignup.setVisible(true);
        }
        if (ae.getSource() == btnsearchsa) {
            searchSalary();
            btncancelsa.setVisible(true);
            btnsearchsa.setVisible(false);
        }
        if (ae.getSource() == btncancelsa) {
            txtsearchsa.setText("");
            btncancelsa.setVisible(false);
            btnsearchsa.setVisible(true);
            showPageSalary();
        }
    }

    public void connectDatabase() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbemployee", "root", "");
            stm = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            System.out.println("Connected to the database.");
        } catch (SQLException ex) {
            System.err.println("Failed to connect to the database.");
            ex.printStackTrace();
        }
    }

    public static String encodePassword(String password) {
        // Encode the password using Base64
        return Base64.getEncoder().encodeToString(password.getBytes());
    }

    public static String decodePassword(String encodedPassword) {
        // Decode the Base64 encoded password back to its original form
        return new String(Base64.getDecoder().decode(encodedPassword));
    }

    public void signup() { // Sign Up account
        namesignup = txtsignname.getText();
        phoneOremailsignup = txtsignEmailorPhone.getText();
        passwordsignup = txtsignPassword.getText();
        conpasswordsignup = txtconsignPassword.getText();
        encodedPassword = encodePassword(passwordsignup);
        if (namesignup.isEmpty() || phoneOremailsignup.isEmpty() || passwordsignup.isEmpty() || conpasswordsignup.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter all information", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!passwordsignup.equals(conpasswordsignup)) {
            JOptionPane.showMessageDialog(null, "Please enter the same password in both fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            // Check if phone or email is already used
            String checkDuplicateSql = "SELECT `id` FROM `admin` WHERE `pRe`='" + phoneOremailsignup + "'";
            rss = stm.executeQuery(checkDuplicateSql);
            if (rss.next()) {
                JOptionPane.showMessageDialog(this, "This phone number or email is already used!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Insert the sign-up information into the admin table
            String insertSign = "INSERT INTO `admin`(`name`, `pRe`,q `password`) VALUES ('" + namesignup + "', '" + phoneOremailsignup + "', '" + encodedPassword + "')";
            stm.executeUpdate(insertSign, Statement.RETURN_GENERATED_KEYS);
            // Retrieve the generated ID for the new admin entry
            rss = stm.getGeneratedKeys();
            if (rss.next()) {
                int newAdminId = rss.getInt(1);
                // Query the database to retrieve the name of the admin just added
                String selectName = "SELECT `name` FROM `admin` WHERE `id` = " + newAdminId;
                rss = stm.executeQuery(selectName);
                if (rss.next()) {
                    String adminName = rss.getString("name");
                    lbnameAdmin.setText(adminName);
                    JOptionPane.showMessageDialog(this, "Signed up successfully! Welcome, " + adminName + "!");
                }
            }
            // Enable the login fields and clear the sign-up fields
            txtUEmailorPhone.setEnabled(true);
            txtPassword.setEnabled(true);
            clearfiledSignupandLogin(); // Clear login fields and sign-up fields
            showbuttonMenu(); // Show relevant panels and buttons upon successful sign-up
            showPageAddEm(); // Additional logic to be executed after sign-up

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error inserting data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void login() { // Login account
        phoneoremaillogin = txtUEmailorPhone.getText();
        passwordlogin = txtPassword.getText();
        if (phoneoremaillogin.isEmpty() || passwordlogin.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill out all fields!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            // Query to get the stored password and name for the given phone/email
            String loginQuery = "SELECT `id`, `name`, `password` FROM `admin` WHERE `pRe`='" + phoneoremaillogin + "'";
            rss = stm.executeQuery(loginQuery);
            if (rss.next()) { // If a record is found
                String getPassword = rss.getString("password");
                decodedPassword = decodePassword(getPassword);
                // Check if the decoded password matches the entered password
                if (decodedPassword.equals(passwordlogin)) {
                    String adminName = rss.getString("name");
                    lbnameAdmin.setText(adminName);
                    JOptionPane.showMessageDialog(this, "Login successful! Welcome, " + adminName + "!");
                    hideFormLogin(); // Show relevant panels and buttons upon successful login
                    clearfiledSignupandLogin(); // Clear login fields and sign-up fields
                } else {
                    if (er <= 2) {
                        er += 1;
                        JOptionPane.showMessageDialog(this, "Incorrect password or email!", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        txtUEmailorPhone.setEnabled(false);
                        txtPassword.setEnabled(false);
                        JOptionPane.showMessageDialog(null, "You're disabled. Please try again!");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "No account found with this email or phone!", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error executing query: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void addEmployee() {
        emfname = txtfname.getText();
        emlname = txtlname.getText();
        emgender = chsex.getSelectedItem();
        ememail = txtemail.getText();
        emphone = txtphone.getText();
        emjob = chjob.getSelectedItem();
        emtime = chtime.getSelectedItem();
        emaddress = chcr.getSelectedItem();
        emstatus = chsingleOrFamily.getSelectedItem();
        day = chd.getSelectedItem();
        month = chm.getSelectedItem();
        year = chy.getSelectedItem();
        birtdate = day + "/" + month + "/" + year;

        String insertEmployee = "INSERT INTO `tbemployee`(`fname`, `lname`, `gender`, `birt`, `email`, `phone`, `jobtitle`, `time`, `address`, `status`) VALUES ('"
                + emfname + "', '" + emlname + "', '" + emgender + "', '" + birtdate + "', '"
                + ememail + "', '" + emphone + "', '" + emjob + "', '" + emtime + "', '"
                + emaddress + "', '" + emstatus + "')";

        if (emfname.isEmpty() || emlname.isEmpty() || emphone.isEmpty() || ememail.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter all information", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            switch (emjob) {
                case "Customer Service":
                    emsalary = 500;
                    break;
                case "Loan Processing":
                    emsalary = 600;
                    break;
                case "Compliance":
                    emsalary = 700;
                    break;
                case "Security":
                    emsalary = 800;
                    break;
                case "IT support":
                    emsalary = 900;
                    break;
                case "Networking":
                    emsalary = 1000;
                    break;
                case "Front End":
                    emsalary = 1200;
                    break;
                case "Back End":
                    emsalary = 1300;
                    break;
                default:
                    emsalary = 0;
            }
            if (emtime.equals("Part time")) {
                emsalary /= 2;
            }
            try {
                stm = con.createStatement();
                stm.executeUpdate(insertEmployee, Statement.RETURN_GENERATED_KEYS);
                rss = stm.getGeneratedKeys();
                int employeeId = 0;
                if (rss.next()) {
                    employeeId = rss.getInt(1);
                }
                // Insert into totalabsent table using the generated employee ID
                String insertTotalAb = "INSERT INTO `totalabsent`(`id`, `name`, `totalabsent`) VALUES ("
                        + employeeId + ", '" + emfname + " " + emlname + "', 0)";
                String innsertTotalSalary = "INSERT INTO `totalsalary`(`id`, `name`, `salary`) VALUES (" + employeeId
                        + ",'" + emfname + " " + emlname + "','" + emsalary + "')";
                stm.executeUpdate(insertTotalAb);
                stm.executeUpdate(innsertTotalSalary);
                JOptionPane.showMessageDialog(this, "Added successfully!");
                clearfiledAddEm();

            } catch (SQLException | NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Error inserting data: " + ex.getMessage());
            }
        }
    }

    public void editEmployee() { // Button Edit for get data to form edit employee
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            // Edit employee information
            String id = (String) tb.getValueAt(selectedRow, 0);
            String fname = (String) tb.getValueAt(selectedRow, 1);
            String lname = (String) tb.getValueAt(selectedRow, 2);
            String gender = (String) tb.getValueAt(selectedRow, 3);
            birtdate = (String) tb.getValueAt(selectedRow, 4);
            String phone = (String) tb.getValueAt(selectedRow, 5);
            String email = (String) tb.getValueAt(selectedRow, 6);
            String job = (String) tb.getValueAt(selectedRow, 7);
            String time = (String) tb.getValueAt(selectedRow, 8);
            String address = (String) tb.getValueAt(selectedRow, 9);
            String status = (String) tb.getValueAt(selectedRow, 10);
            txtid1edit.setText(id);
            txtfnameedit.setText(fname);
            txtlnameedit.setText(lname);
            chsexedit.select(gender);
            String[] birthdateParts = birtdate.split("/");
            chdedit.select(birthdateParts[0]);
            chmedit.select(birthdateParts[1]);
            chyedit.select(birthdateParts[2]);
            txtphoneedit.setText(phone);
            txtemailedit.setText(email);
            chjobedit.select(job);
            chtimeedit.select(time);
            chaddedit.select(address);
            chsingleOrFamilyedit.select(status);
        }
        showPageEditEm();// function show page edit employee
    }

    public void updateEmployee() {
        // Retrieve data from the form
        emidedit = txtid1edit.getText();
        emfnameedit = txtfnameedit.getText();
        emlnameedit = txtlnameedit.getText();
        emgenderedit = chsexedit.getSelectedItem();
        ememailedit = txtemailedit.getText();
        emphoneedit = txtphoneedit.getText();
        emjobedit = chjobedit.getSelectedItem();
        emtimeedit = chtimeedit.getSelectedItem();
        emaddressedit = chcredit.getSelectedItem();
        emstatusedit = chsingleOrFamilyedit.getSelectedItem();
        dayedit = chdedit.getSelectedItem();
        monthedit = chmedit.getSelectedItem();
        yearedit = chyedit.getSelectedItem();
        birtdateedit = dayedit + "/" + monthedit + "/" + yearedit;
        String fullname = emfnameedit + " " + emlnameedit;
        // SQL queries for updating employee, totalabsent, and totalsalary tables
        String editEmployee = "UPDATE `tbemployee` SET `fname`='" + emfnameedit + "',`lname`='" + emlnameedit
                + "',`gender`='" + emgenderedit + "',`birt`='" + birtdateedit + "',`email`='" + ememailedit
                + "',`phone`='" + emphoneedit + "',`jobtitle`='" + emjobedit + "',`time`='" + emtimeedit
                + "',`address`='" + emaddressedit + "',`status`='" + emstatusedit + "' WHERE `id` = '" + emidedit + "'";

        String editTotalAbsent = "UPDATE `totalabsent` SET `name`='" + fullname + "' WHERE `id` = '" + emidedit + "'";
        // Check if a row is selected and the input fields are not empty
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            if (emidedit.isEmpty() || emfnameedit.isEmpty() || emlnameedit.isEmpty() || emphoneedit.isEmpty()
                    || ememailedit.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter the new information you want to update", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                // Calculate salary based on job title and time
                switch (emjobedit) {
                    case "Customer Service":
                        emsalaryedit = 500;
                        break;
                    case "Loan Processing":
                        emsalaryedit = 600;
                        break;
                    case "Compliance":
                        emsalaryedit = 700;
                        break;
                    case "Security":
                        emsalaryedit = 800;
                        break;
                    case "IT support":
                        emsalaryedit = 900;
                        break;
                    case "Networking":
                        emsalaryedit = 1000;
                        break;
                    case "Front End":
                        emsalaryedit = 1200;
                        break;
                    case "Back End":
                        emsalaryedit = 1300;
                        break;
                    default:
                        emsalaryedit = 0;
                }
                if (emtimeedit.equals("Part time")) {
                    emsalaryedit /= 2;
                }
                String editTotalSalary = "UPDATE `totalsalary` SET `name`='" + fullname + "', `salary`='" + emsalaryedit
                        + "' WHERE `id` = '" + emidedit + "'";
                // Confirm the update action
                int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to update?",
                        "Update Confirmation", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    try {
                        // Execute the updates
                        stm.executeUpdate(editEmployee);
                        stm.executeUpdate(editTotalAbsent);
                        stm.executeUpdate(editTotalSalary);
                        JOptionPane.showMessageDialog(this, "Employee updated successfully!");
                        // Clear the form fields and refresh the table view
                        clearEditEmployee();
                        table.setRowSelectionInterval(selectedRow, selectedRow);

                    } catch (SQLException | NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Error updating data: " + ex.getMessage());
                    }
                } else {
                    // Cancel the update action
                    table.getSelectionModel().clearSelection();
                    clearEditEmployee(); // Function to clear the fields after editing
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to update.");
        }

        // Reset the search field and refresh the employee table view
        txtsearch.setText("");
        showPageshowEmployee(); // Function to refresh the table view
    }

    public void removeEmployee() {
        int selectedRow = table.getSelectedRow();
        Object idToDelete = tb.getValueAt(selectedRow, 0);
        String sqlRemoveEm = "DELETE FROM `tbemployee` WHERE `id` = '" + idToDelete + "'";
        String sqlRemoveTotalAb = "DELETE FROM `totalabsent` WHERE `id` = '" + idToDelete + "'";
        String sqlRemoveTotalSalary = "DELETE FROM `totalsalary` WHERE `id` = '" + idToDelete + "'";
        if (selectedRow != -1) {
            int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove?", "Remove Confirmation",
                    JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                try {
                    int rowsAffected = stm.executeUpdate(sqlRemoveEm);
                    stm.executeUpdate(sqlRemoveTotalAb);
                    stm.executeUpdate(sqlRemoveTotalSalary);
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(this, "Employee deleted successfully!");
                        tb.removeRow(selectedRow);
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Error executing query: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                table.getSelectionModel().clearSelection();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row to remove", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void searchEmployee() {
        String idToVerify = txtsearch.getText();
        btnsearch.setVisible(false);
        btnsearchsearch.setVisible(true);
        String searchSql = "SELECT * FROM `tbemployee` WHERE `id` = '" + idToVerify + "'";
        boolean found = false;
        try {
            rss = stm.executeQuery(searchSql);
            // Clear the previous search results
            tb.setRowCount(0);
            // If a matching record is found
            if (rss.next()) {
                tb.addRow(new Object[]{
                    rss.getString("id"),
                    rss.getString("fname"),
                    rss.getString("lname"),
                    rss.getString("gender"),
                    rss.getString("birt"),
                    rss.getString("phone"),
                    rss.getString("email"),
                    rss.getString("jobtitle"),
                    rss.getString("time"),
                    rss.getString("address"),
                    rss.getString("status"),
                    rss.getString("date")
                });
                found = true;
            }
            rss.close(); // Close the ResultSet
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error executing query: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        if (!found) {
            JOptionPane.showMessageDialog(this, "No employee found with ID: " + idToVerify, "Not Found",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void attendance() {
        String attendanceId = txtidem.getText();
        if (attendanceId.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter employee ID", "Error", JOptionPane.ERROR_MESSAGE);
            txtidem.setText("");
            return;
        } else {
            try {
                String checkEmployeeSql = "SELECT * FROM tbemployee WHERE id = '" + attendanceId + "'";
                rss = stm.executeQuery(checkEmployeeSql);
                if (rss.next()) {
                    String firstName = rss.getString("fname");
                    String lastName = rss.getString("lname");
                    String fullName = firstName + "    " + lastName;
                    // Get the current date (assuming your DB stores the attendance date)
                    java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
                    String checkAttendanceSql = "SELECT * FROM attendance WHERE id = '" + attendanceId + "' AND date = '" + currentDate + "'";
                    rss = stm.executeQuery(checkAttendanceSql);
                    if (rss.next()) {
                        JOptionPane.showMessageDialog(null, "Attendance for this employee has already been recorded today", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        String sqlAttendance = "INSERT INTO attendance (id, name, status, date) VALUES ('" + attendanceId + "', '"
                                + fullName + "', 'To come', '" + currentDate + "')";
                        stm.executeUpdate(sqlAttendance);
                        JOptionPane.showMessageDialog(null, "Attendance recorded successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Employee ID not found", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error while recording attendance: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            txtidem.setText("");
        }
        showPageattendance();
    }

    public void removeAttendance() {// Remove attendance
        int selectedRow = tableattendance.getSelectedRow();
        Object idToDelete = tbattendance.getValueAt(selectedRow, 0);
        String sqlRemoveEm = "DELETE FROM `attendance` WHERE `id` = '" + idToDelete + "'";
        if (selectedRow != -1) {
            int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove?", "Remove Confirmation",
                    JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                try {
                    int rowsAffected = stm.executeUpdate(sqlRemoveEm);
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(this, "Attendance deleted successfully!");
                        tbattendance.removeRow(selectedRow);
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Error executing query: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                tableattendance.getSelectionModel().clearSelection();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row to remove", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void absentEmployee() { // Add Absent
        abId = txtid.getText();
        abReason = txtreason.getText();
        daysab = ds.getSelectedItem();
        monthsab = ms.getSelectedItem();
        yearsab = ys.getSelectedItem();
        birtdatesab = daysab + "/" + monthsab + "/" + yearsab; // Absence start date
        dayeab = de.getSelectedItem();
        montheab = me.getSelectedItem();
        yeareab = ye.getSelectedItem();
        birtdateeab = dayeab + "/" + montheab + "/" + yeareab; // Absence end date

        try {
            absNumberDays = Integer.parseInt(txtsbday.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number of absence days.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (abId.isEmpty() || abReason.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter all information", "Error", JOptionPane.ERROR_MESSAGE);
            clearDataAbsent(); // Function to clear data on Textfield
            return;
        }

        if (absNumberDays <= 0) {
            JOptionPane.showMessageDialog(this, "Number of days must be greater than zero.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            clearDataAbsent(); // Function to clear data on Textfield
            return;
        } else if (absNumberDays > 3) {
            JOptionPane.showMessageDialog(null, "Maximum allowed absence is 3 days.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            clearDataAbsent(); // Function to clear data on Textfield
            return;
        } else {
            try {
                // Check if the employee exists in the database
                String checkEmployeeSql = "SELECT * FROM tbemployee WHERE id = '" + abId + "'";
                rss = stm.executeQuery(checkEmployeeSql);
                if (rss.next()) {
                    String firstName = rss.getString("fname");
                    String lastName = rss.getString("lname");
                    String fullName = firstName + "  " + lastName;
                    java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
                    // Check if an absence record exists for the same date
                    String checkAbsentSql = "SELECT * FROM absent WHERE id = '" + abId + "' AND `create` = '" + currentDate + "'";
                    rss = stm.executeQuery(checkAbsentSql);
                    if (rss.next()) {
                        JOptionPane.showMessageDialog(this, "Absence for this employee has already been recorded today.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        clearDataAbsent(); // Function to clear data on Textfield
                        return;
                    }

                    // Insert the absence record
                    String insertAbsent = "INSERT INTO `absent`(`id`, `name`, `n`, `from`, `to`, `reason`) VALUES ('"
                            + abId + "', '" + fullName + "', '" + absNumberDays + "', '" + birtdatesab
                            + "', '" + birtdateeab + "', '" + abReason + "')";
                    stm.executeUpdate(insertAbsent);

                    // Update the total absence count
                    String updateTotalAb = "UPDATE `totalabsent` SET `totalabsent` = `totalabsent` + " + absNumberDays
                            + " WHERE `id` = '" + abId + "'";
                    stm.executeUpdate(updateTotalAb);

                    // Apply a fine based on the number of days absent
                    double fine = absNumberDays * 2.5;
                    String updateTotalSalary = "UPDATE `totalsalary` SET `salary` = `salary` - " + fine
                            + " WHERE `id` = '" + abId + "'";
                    stm.executeUpdate(updateTotalSalary);

                    JOptionPane.showMessageDialog(this,
                            "A $" + fine + " fine has been applied for exceeding 1 absence.", "Fine Applied",
                            JOptionPane.WARNING_MESSAGE);
                    JOptionPane.showMessageDialog(null, "Absent recorded successfully", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    clearDataAbsent();
                } else {
                    JOptionPane.showMessageDialog(null, "Employee ID not found", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error processing absence: " + ex.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        showPageAddAnsent(); // Function to refresh the absence list
    }

    public void removeAbsent() { // Remove Absent
        int selectedRow = tableab.getSelectedRow();
        if (selectedRow != -1) {
            Object noToDelete = tbab.getValueAt(selectedRow, 0);
            Object idToDelete = tbab.getValueAt(selectedRow, 1);
            String selectAbsDaysSql = "SELECT `n` FROM `absent` WHERE `no` = '" + noToDelete + "'";
            try {
                rss = stm.executeQuery(selectAbsDaysSql);
                if (rss.next()) {
                    int absDaysToRemove = rss.getInt("n");
                    String sqlRemoveAb = "DELETE FROM `absent` WHERE `no` = '" + noToDelete + "'";
                    String updateTotalAb = "UPDATE `totalabsent` SET `totalabsent` = `totalabsent` - " + absDaysToRemove
                            + " WHERE `id` = '" + idToDelete + "'";
                    int option = JOptionPane.showConfirmDialog(null,
                            "Are you sure you want to remove this absence record?", "Remove Confirmation",
                            JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        int rowsAffected = stm.executeUpdate(sqlRemoveAb);
                        if (rowsAffected > 0) {
                            stm.executeUpdate(updateTotalAb);
                            // Calculate the refund
                            double refund = absDaysToRemove * 2.5;
                            String updateTotalSalary = "UPDATE `totalsalary` SET `salary` = `salary` + " + refund
                                    + " WHERE `id` = '" + idToDelete + "'";
                            stm.executeUpdate(updateTotalSalary);
                            JOptionPane.showMessageDialog(this,
                                    "Fine refunded: $" + refund + ". Total absences updated.", "Fine Refunded",
                                    JOptionPane.INFORMATION_MESSAGE);
                            JOptionPane.showMessageDialog(this, "Absent deleted successfully!");
                            tbab.removeRow(selectedRow);
                        }
                    } else {
                        tableab.getSelectionModel().clearSelection();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Unable to find the absent record.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error executing query: " + ex.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row to remove", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void searchAbsent() {
        idToVerifytotal = txtsearchab.getText(); // Get the ID from the search text field
        btnsearchabs.setVisible(true);
        btnsearchab.setVisible(false);
        String searchAbsentSql = "SELECT * FROM `totalabsent` WHERE `id` = '" + idToVerifytotal + "'";
        boolean found = false;
        try {
            rss = stm.executeQuery(searchAbsentSql);
            // Clear the previous search results
            tbabsent.setRowCount(0);
            // If a matching record is found
            if (rss.next()) {
                tbabsent.addRow(new Object[]{
                    rss.getString("id"),
                    rss.getString("name"),
                    rss.getString("totalabsent"),});
                found = true;
            }
            rss.close(); // Close the ResultSet
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error executing query: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        if (!found) {
            JOptionPane.showMessageDialog(this, "No employee found with ID: " + idToVerifytotal, "Not Found",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void searchSalary() {
        idToVerifytotal = txtsearchsa.getText(); // Get the ID from the search text field
        String searchsalarySql = "SELECT * FROM `totalsalary` WHERE `id` = '" + idToVerifytotal + "'";
        boolean found = false;
        try {
            rss = stm.executeQuery(searchsalarySql);
            // Clear the previous search results
            tbsalary.setRowCount(0);
            // If a matching record is found
            if (rss.next()) {
                tbsalary.addRow(new Object[]{
                    rss.getString("id"),
                    rss.getString("name"),
                    rss.getString("salary"),});
                found = true;
            }
            rss.close(); // Close the ResultSet
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error executing query: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        if (!found) {
            JOptionPane.showMessageDialog(this, "No employee found with ID: " + idToVerifytotal, "Not Found",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void showPageSalary() {
        tbsalary.setRowCount(0);
        try {
            String selectQuery = "SELECT * FROM totalSalary";
            rss = stm.executeQuery(selectQuery);
            while (rss.next()) {
                long id = rss.getLong("id");
                String name = rss.getString("name");
                double salary = rss.getDouble("salary");
                tbsalary.addRow(new String[]{String.valueOf(id), name, String.valueOf(salary)});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error displaying data: " + ex.getMessage());
        }
        pSEm.setVisible(false);
        pnladd.setVisible(false);
        pabsent.setVisible(false);
        pab.setVisible(false);
        psalary.setVisible(true);
        attendance.setVisible(false);
        pnladdedit.setVisible(false);
    }

    public void clearDataAbsent() {
        txtid.setText("");
        txtreason.setText("");
        txtsbday.setText("");
        ds.select("01");
        ms.select("Jan");
        ys.select("2025");
        de.select("01");
        me.select("Jan");
        ye.select("2025");
    }

    public void showPageshowEmployee() {
        tb.setRowCount(0);// clear data on table employee
        try {
            String selectQuery = "SELECT * FROM tbemployee";
            rss = stm.executeQuery(selectQuery);
            while (rss.next()) {
                long id = rss.getLong("id");
                String fname = rss.getString("fname");
                String lname = rss.getString("lname");
                String gender = rss.getString("gender");
                String birt = rss.getString("birt");
                String email = rss.getString("email");
                String phone = rss.getString("phone");
                String jobtitle = rss.getString("jobtitle");
                String time = rss.getString("time");
                String address = rss.getString("address");
                String status = rss.getString("status");
                String create = rss.getString("date");
                tb.addRow(new String[]{String.valueOf(id), fname, lname, gender, birt, phone, email, jobtitle, time,
                    address, status, create});

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error displaying data: " + ex.getMessage());
        }
        pSEm.setVisible(true);
        pnladd.setVisible(false);
        pabsent.setVisible(false);
        pab.setVisible(false);
        psalary.setVisible(false);
        attendance.setVisible(false);
        plogin.setVisible(false);
        psignup.setVisible(false);
        pnladdedit.setVisible(false);
    }

    public void clearEditEmployee() {
        txtid1edit.setText("");
        txtfnameedit.setText("");
        txtlnameedit.setText("");
        chsexedit.select("Male");
        txtemailedit.setText("");
        txtphoneedit.setText("");
        chjobedit.select("Customer Service");
        chtimeedit.select("Full time");
        chcredit.select("Banteay Meanchey");
        chsingleOrFamilyedit.select("Single");
        chdedit.select("01");
        chmedit.select("Jan");
        chyedit.select("2010");
    }

    public void clearfiledAddEm() {
        txtfname.setText("");
        txtlname.setText("");
        chsex.select("Male");
        txtemail.setText("");
        txtphone.setText("");
        chjob.select("Customer Service");
        chtime.select("Full time");
        chcr.select("Banteay Meanchey");
        chsingleOrFamily.select("Single");
        chd.select("01");
        chm.select("Jan");
        chy.select("2010");
    }

    public void hideFormLogin() {// Show relevant panels and buttons upon successful login
        pnladd.setVisible(true);
        plogin.setVisible(false);
        btnsEm.setVisible(true);
        btnaddEm.setVisible(true);
        btnabsentEm.setVisible(true);
        btnsabsentEm.setVisible(true);
        btnsalaryEm.setVisible(true);
        btnlogoutEm.setVisible(true);
        btnattendanceEm.setVisible(true);
    }

    public void clearfiledSignupandLogin() {
        // Clear login fields
        txtUEmailorPhone.setText("");
        txtPassword.setText("");
        // Clear sign-up fields
        txtsignname.setText("");
        txtsignEmailorPhone.setText("");
        txtsignPassword.setText("");
        txtconsignPassword.setText("");
    }

    public void showbuttonMenu() {
        btnsEm.setVisible(true);
        btnaddEm.setVisible(true);
        btnabsentEm.setVisible(true);
        btnsabsentEm.setVisible(true);
        btnsalaryEm.setVisible(true);
        btnlogoutEm.setVisible(true);
        btnattendanceEm.setVisible(true);
    }

    public void showPageAddEm() {
        pnladdedit.setVisible(false);
        pSEm.setVisible(false);
        pnladd.setVisible(true);
        pabsent.setVisible(false);
        pab.setVisible(false);
        psalary.setVisible(false);
        attendance.setVisible(false);
        plogin.setVisible(false);
        psignup.setVisible(false);
    }

    public void buttonlogout() {
        btnsEm.setVisible(false);
        btnaddEm.setVisible(false);
        btnabsentEm.setVisible(false);
        btnsabsentEm.setVisible(false);
        btnsalaryEm.setVisible(false);
        btnlogoutEm.setVisible(false);
        btnattendanceEm.setVisible(false);
        pSEm.setVisible(false);
        pnladd.setVisible(false);
        pabsent.setVisible(false);
        pab.setVisible(false);
        psalary.setVisible(false);
        attendance.setVisible(false);
        plogin.setVisible(true);
    }

    public void clearfiledEditEm() {
        txtid1edit.setText("");
        txtfnameedit.setText("");
        txtlnameedit.setText("");
        chsexedit.select("Male");
        txtemailedit.setText("");
        txtphoneedit.setText("");
        chjobedit.select("Customer Service");
        chtimeedit.select("Full time");
        chcredit.select("Banteay Meanchey");
        chsingleOrFamilyedit.select("Single");
        chdedit.select("01");
        chmedit.select("Jan");
        chyedit.select("2010");
    }

    public void clearAbsentfild() {
        txtid.setText("");
        txtreason.setText("");
        txtsbday.setText("");
        ds.select("01");
        ms.select("Jan");
        ys.select("2025");
        de.select("01");
        me.select("Jan");
        ye.select("2025");
    }

    public void showPageEditEm() {
        pSEm.setVisible(false);
        pnladd.setVisible(false);
        pabsent.setVisible(false);
        pab.setVisible(false);
        psalary.setVisible(false);
        attendance.setVisible(false);
        plogin.setVisible(false);
        psignup.setVisible(false);
        pnladdedit.setVisible(true);
    }

    public void showPageAddAnsent() {
        tbab.setRowCount(0);
        try {
            String selectAbsent = "SELECT * FROM `absent`";
            rss = stm.executeQuery(selectAbsent);
            while (rss.next()) {
                long no = rss.getLong("no");
                long id = rss.getLong("id");
                String name = rss.getString("name");
                long n = rss.getLong("n");
                String from = rss.getString("from");
                String to = rss.getString("to");
                String reason = rss.getString("reason");
                String create = rss.getString("create");
                tbab.addRow(new String[]{String.valueOf(no), String.valueOf(id), name, String.valueOf(n), from, to,
                    reason, create});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error displaying data: " + ex.getMessage());
        }
        pSEm.setVisible(false);
        pnladd.setVisible(false);
        pabsent.setVisible(false);
        pab.setVisible(true);
        psalary.setVisible(false);
        attendance.setVisible(false);
        plogin.setVisible(false);
        psignup.setVisible(false);
        pnladdedit.setVisible(false);
    }

    public void showPageTotalAbsent() {
        tbabsent.setRowCount(0);
        try {
            String selectQuery = "SELECT * FROM totalabsent";
            rss = stm.executeQuery(selectQuery);
            while (rss.next()) {
                long id = rss.getLong("id");
                String name = rss.getString("name");
                Long totalabsent = rss.getLong("totalabsent");
                tbabsent.addRow(new String[]{String.valueOf(id), name, String.valueOf(totalabsent)});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error displaying data: " + ex.getMessage());
        }
        pSEm.setVisible(false);
        pnladd.setVisible(false);
        pabsent.setVisible(true);
        pab.setVisible(false);
        psalary.setVisible(false);
        attendance.setVisible(false);
        plogin.setVisible(false);
        psignup.setVisible(false);
        pnladdedit.setVisible(false);
    }

    public void showPageattendance() {
        tbattendance.setRowCount(0);
        try {
            String selectQuery = "SELECT * FROM attendance";
            rss = stm.executeQuery(selectQuery);
            while (rss.next()) {
                long id = rss.getLong("id");
                String name = rss.getString("name");
                String status = rss.getString("status");
                String date = rss.getString("date");
                tbattendance.addRow(new String[]{String.valueOf(id), name, status, date});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error displaying data: " + ex.getMessage());
        }
        pSEm.setVisible(false);
        pnladd.setVisible(false);
        pnladdedit.setVisible(false);
        pabsent.setVisible(false);
        pab.setVisible(false);
        psalary.setVisible(false);
        attendance.setVisible(true);
    }

    private static void hideColumn(JTable table, int columnIndex) {
        TableColumn column = table.getColumnModel().getColumn(columnIndex);
        table.getColumnModel().removeColumn(column);
    }

    public static void main(String[] args) {
        new EmployeeAdmin();
    }
}
