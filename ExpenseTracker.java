import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ExpenseTracker extends JFrame implements ActionListener {

    JLabel lblTitle, lblDate, lblAmount, lblCategory, lblNote, lblTotal, lblBudget, lblStatus;
    JTextField txtDate, txtAmount, txtNote, txtBudget;
    JComboBox<String> cmbCategory;
    JTextArea txtArea;
    JButton btnAdd, btnClear, btnExit, btnSetBudget, btnDeleteLast, btnSummary;
    JButton btnLight, btnDark, btnBlue;

    double totalExpense = 0;
    double budgetLimit = 0;

    double food = 0, travel = 0, study = 0, shopping = 0, other = 0;
    ArrayList<Double> amountList = new ArrayList<>();

    ExpenseTracker() {
        setTitle("Daily Expense Tracker");
        setSize(650, 580);
        setLayout(null);

        lblTitle = new JLabel("Daily Expense Tracker");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitle.setBounds(230, 10, 300, 30);
        add(lblTitle);

        lblDate = new JLabel("Date:");
        lblDate.setBounds(30, 60, 100, 25);
        add(lblDate);

        txtDate = new JTextField();
        txtDate.setBounds(150, 60, 150, 25);
        add(txtDate);

        lblAmount = new JLabel("Amount:");
        lblAmount.setBounds(30, 100, 100, 25);
        add(lblAmount);

        txtAmount = new JTextField();
        txtAmount.setBounds(150, 100, 150, 25);
        add(txtAmount);

        lblCategory = new JLabel("Category:");
        lblCategory.setBounds(30, 140, 100, 25);
        add(lblCategory);

        cmbCategory = new JComboBox<>(new String[]{"Food", "Travel", "Study", "Shopping", "Other"});
        cmbCategory.setBounds(150, 140, 150, 25);
        add(cmbCategory);

        lblNote = new JLabel("Note:");
        lblNote.setBounds(30, 180, 100, 25);
        add(lblNote);

        txtNote = new JTextField();
        txtNote.setBounds(150, 180, 150, 25);
        add(txtNote);

        lblBudget = new JLabel("Set Budget:");
        lblBudget.setBounds(330, 60, 100, 25);
        add(lblBudget);

        txtBudget = new JTextField();
        txtBudget.setBounds(430, 60, 150, 25);
        add(txtBudget);

        btnSetBudget = new JButton("Set Budget");
        btnSetBudget.setBounds(430, 95, 150, 25);
        add(btnSetBudget);

        btnAdd = new JButton("Add Expense");
        btnAdd.setBounds(330, 140, 150, 30);
        add(btnAdd);

        btnDeleteLast = new JButton("Delete Last");
        btnDeleteLast.setBounds(330, 180, 150, 30);
        add(btnDeleteLast);

        btnSummary = new JButton("Summary");
        btnSummary.setBounds(430, 180, 150, 30);
        add(btnSummary);

        btnClear = new JButton("Clear");
        btnClear.setBounds(330, 220, 150, 30);
        add(btnClear);

        // 🎨 THEME BUTTONS
        btnLight = new JButton("Light Theme");
        btnLight.setBounds(30, 220, 130, 30);
        add(btnLight);

        btnDark = new JButton("Dark Theme");
        btnDark.setBounds(170, 220, 130, 30);
        add(btnDark);

        btnBlue = new JButton("Blue Theme");
        btnBlue.setBounds(30, 260, 270, 30);
        add(btnBlue);

        txtArea = new JTextArea();
        txtArea.setEditable(false);
        JScrollPane sp = new JScrollPane(txtArea);
        sp.setBounds(30, 310, 550, 170);
        add(sp);

        lblTotal = new JLabel("Total Expense: ₹0");
        lblTotal.setFont(new Font("Arial", Font.BOLD, 14));
        lblTotal.setBounds(30, 490, 250, 25);
        add(lblTotal);

        btnExit = new JButton("Exit");
        btnExit.setBounds(490, 490, 90, 30);
        add(btnExit);

        lblStatus = new JLabel("Status: Ready");
        lblStatus.setBounds(30, 520, 400, 25);
        add(lblStatus);

        // Action Listeners
        btnAdd.addActionListener(this);
        btnClear.addActionListener(this);
        btnExit.addActionListener(this);
        btnSetBudget.addActionListener(this);
        btnDeleteLast.addActionListener(this);
        btnSummary.addActionListener(this);
        btnLight.addActionListener(this);
        btnDark.addActionListener(this);
        btnBlue.addActionListener(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        // 🎨 THEME LOGIC
        if (e.getSource() == btnLight) {
            getContentPane().setBackground(Color.WHITE);
            lblStatus.setText("Status: Light Theme Applied");
        }

        if (e.getSource() == btnDark) {
            getContentPane().setBackground(Color.DARK_GRAY);
            lblStatus.setText("Status: Dark Theme Applied");
        }

        if (e.getSource() == btnBlue) {
            getContentPane().setBackground(new Color(173, 216, 230));
            lblStatus.setText("Status: Blue Theme Applied");
        }

        if (e.getSource() == btnSetBudget) {
            try {
                budgetLimit = Double.parseDouble(txtBudget.getText());
                lblStatus.setText("Status: Budget set to ₹" + budgetLimit);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Enter valid budget");
            }
        }

        if (e.getSource() == btnAdd) {
            try {
                double amount = Double.parseDouble(txtAmount.getText());
                String category = cmbCategory.getSelectedItem().toString();

                totalExpense += amount;
                amountList.add(amount);

                if (category.equals("Food")) food += amount;
                else if (category.equals("Travel")) travel += amount;
                else if (category.equals("Study")) study += amount;
                else if (category.equals("Shopping")) shopping += amount;
                else other += amount;

                txtArea.append("Date: " + txtDate.getText() +
                        " | ₹" + amount +
                        " | " + category +
                        " | " + txtNote.getText() + "\n");

                lblTotal.setText("Total Expense: ₹" + totalExpense);
                lblStatus.setText("Status: Expense Added");

                if (budgetLimit > 0 && totalExpense > budgetLimit) {
                    JOptionPane.showMessageDialog(this, "Warning: Budget Exceeded!");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Enter valid input");
            }
        }

        if (e.getSource() == btnDeleteLast && !amountList.isEmpty()) {
            double last = amountList.remove(amountList.size() - 1);
            totalExpense -= last;
            lblTotal.setText("Total Expense: ₹" + totalExpense);
            lblStatus.setText("Status: Last Expense Deleted");
        }

        if (e.getSource() == btnSummary) {
            JOptionPane.showMessageDialog(this,
                    "Food: ₹" + food +
                            "\nTravel: ₹" + travel +
                            "\nStudy: ₹" + study +
                            "\nShopping: ₹" + shopping +
                            "\nOther: ₹" + other);
        }
  
        if (e.getSource() == btnClear) {
            txtDate.setText("");
            txtAmount.setText("");
            txtNote.setText("");
            lblStatus.setText("Status: Cleared");
        }

        if (e.getSource() == btnExit) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new ExpenseTracker();
    }
}