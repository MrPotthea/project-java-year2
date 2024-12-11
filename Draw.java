import java.awt.Label;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

public class Draw extends JFrame implements ActionListener {

    Label lbDraw = new Label("Phone Number Draw");
    Label lbWinner = new Label("Waiting for the draw...");
    Label lbWinner1 = new Label("Who is the winner..............?");
    JPanel pg = new JPanel();
    JPanel pin = new JPanel(new BorderLayout());
    JPanel pnl = new JPanel(new GridLayout(3, 2, 10, 10));
    JPanel pbtn = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    Label lbinput = new Label("Enter phone number:");
    JTextField txtp = new JTextField(20);
    Label lbname = new Label("Enter name:");
    JTextField txtn = new JTextField(20);
    private DefaultTableModel tb = new DefaultTableModel(new String[]{"Name", "Phone number"}, 0);
    private JTable table = new JTable(tb);
    JScrollPane scrollPane = new JScrollPane(table);
    JButton btnadd = new JButton("ADD");
    JButton btnsadd = new JButton("STOP ADD");
    JButton btngadd = new JButton("GO ADD");
    JButton btnstart = new JButton("START DRAW");
    JButton btnstop = new JButton("STOP DRAW");
    JButton btnok = new JButton("END");
    JButton btnexit = new JButton("EXIT");
    ArrayList<String> givendraw = new ArrayList<>();
    ArrayList<String> winners = new ArrayList<>();
    Random random = new Random();
    Timer timer;

    public Draw() {
        super("Draw");
        lbWinner.setFont(new Font("Consolas", Font.BOLD, 15));
        lbWinner.setAlignment(Label.CENTER);
        lbWinner1.setFont(new Font("Consolas", Font.BOLD, 18));
        lbWinner1.setAlignment(Label.CENTER);
        add(lbDraw, BorderLayout.NORTH);
        lbDraw.setAlignment(Label.CENTER);
        lbDraw.setFont(new Font("Consolas", Font.BOLD, 15));
        pnl.add(lbname);
        pnl.add(txtn);
        pnl.add(lbinput);
        pnl.add(txtp);
        pin.add(pnl, BorderLayout.NORTH);
        pbtn.add(btnadd);
        pbtn.add(btnsadd);
        pbtn.add(btngadd);
        pbtn.add(btnstart);
        pbtn.add(btnstop);
        pbtn.add(btnok);
        pbtn.add(btnexit);
        btnstart.setVisible(false);
        btnstop.setVisible(false);
        btngadd.setVisible(false);
        btnok.setVisible(false);
        btnexit.setVisible(false);
        pin.add(pbtn, BorderLayout.CENTER);
        pin.add(scrollPane, BorderLayout.SOUTH); 
        JPanel pl = new JPanel(new BorderLayout());
        pg.add(pin);
        pl.add(pg, BorderLayout.NORTH);
        JPanel gride = new JPanel(new GridLayout(2, 1));
        JPanel gridef = new JPanel(new FlowLayout());
        gride.add(lbWinner);
        gride.add(lbWinner1);
        gridef.add(gride);
        pl.add(gridef, BorderLayout.CENTER);
        add(pl);
        btnadd.addActionListener(this);
        btnsadd.addActionListener(this);
        btngadd.addActionListener(this);
        btnstart.addActionListener(this);
        btnstop.addActionListener(this);
        btnok.addActionListener(this);
        btnexit.addActionListener(this);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
        setSize(700, 730);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent ae) {
        String name = txtn.getText();
        String phone = txtp.getText();

        if (ae.getSource() == btnadd) {
            if (name.isEmpty() || phone.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter all information", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                givendraw.add(name + "   " + phone);
                tb.addRow(new String[]{name, phone});
                txtn.setText("");
                txtp.setText("");
                btnstart.setVisible(false);
                btnsadd.setVisible(true);
            }
        } else if (ae.getSource() == btnsadd) {
            btnstart.setVisible(true);
            btnadd.setVisible(false);
            btngadd.setVisible(true);
            btnsadd.setVisible(false);
            txtn.setText("");
            txtn.setEditable(false);
            txtp.setText("");
            txtp.setEditable(false);
        } else if (ae.getSource() == btngadd) {
            btnstart.setVisible(false);
            btnadd.setVisible(true);
            btngadd.setVisible(false);
            btnsadd.setVisible(true);
            txtn.setText("");
            txtn.setEditable(true);
            txtp.setText("");
            txtp.setEditable(true);
        } else if (ae.getSource() == btnstart) {
            if (givendraw.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Data is empty, please add information", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                btnstart.setVisible(false);
                btnstop.setVisible(true);
                btnadd.setVisible(false);
                btngadd.setVisible(false);
                btnsadd.setVisible(false);

                // Start drawing by updating the label with random entries
                timer = new Timer(50, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int randomIndex = random.nextInt(givendraw.size());
                        lbWinner.setText("Drawing...!");
                        lbWinner1.setText(givendraw.get(randomIndex));
                    }
                });
                timer.start();
            }
        } else if (ae.getSource() == btnstop) {
            if (timer != null) {
                timer.stop();
            }
            btnstop.setVisible(false);
            btnstart.setVisible(true);
            btnok.setVisible(true);
            String winningPrize = lbWinner1.getText();
            givendraw.remove(winningPrize);
            winners.add(winningPrize);
            lbWinner.setText("Congratulations!");
            lbWinner1.setText(winningPrize);
        } else if (ae.getSource() == btnok) {
            btnstart.setVisible(false);
            btnok.setVisible(false);
            btnexit.setVisible(true);
            // Display all winners
            StringBuilder winnersList = new StringBuilder("All Winners:\n");
            int reward = 100000;
            int n = 1;
            for (String winner : winners) {
                winnersList.append(n + ". " + winner + "   reward (" + reward + "$)\n");
                reward -= 10000;
                n++;
            }
            lbWinner.setText("The draw is over");
            lbWinner1.setText("");
            JOptionPane.showMessageDialog(this, winnersList.toString(), "All Winners", JOptionPane.INFORMATION_MESSAGE);
        } else if (ae.getSource() == btnexit) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new Draw();
    }
}
