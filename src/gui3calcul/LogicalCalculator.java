package gui3calcul;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LogicalCalculator implements ActionListener {

    JFrame frame;
    JTextField textfield;
    JButton[] numberButtons = new JButton[16];
    JButton[] functionButtons = new JButton[9];
    JButton addButton, subButton, mulButton, divButton;
    JButton decButton, equButton, delButton, clrButton, negButton;
    JRadioButton binaryButton, decimalButton, hexadecimalButton;
    ButtonGroup radixButtonGroup;
    JPanel panel;

    Font myFont = new Font("Ink Free", Font.BOLD, 30);

    double num1 = 0, num2 = 0, result = 0;
    char operator;

    public LogicalCalculator() {

        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 600);
        frame.setLayout(null);

        textfield = new JTextField();
        textfield.setBounds(50, 25, 300, 50);
        textfield.setFont(myFont);
        textfield.setEditable(false);

        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("x");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("Del");
        clrButton = new JButton("Clr");
        negButton = new JButton("(-)");
        binaryButton = new JRadioButton("Binary");
        decimalButton = new JRadioButton("Decimal");
        hexadecimalButton = new JRadioButton("Hexadecimal");

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;
        functionButtons[8] = negButton;

        for (int i = 0; i < 9; i++) {
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(myFont);
            functionButtons[i].setFocusable(false);
        }

        negButton.setBounds(50, 480, 100, 50);
        delButton.setBounds(150, 480, 100, 50);
        clrButton.setBounds(250, 480, 100, 50);

        panel = new JPanel();
        panel.setBounds(50, 100, 300, 350);
        panel.setLayout(new GridLayout(4, 4, 10, 10));


        for (int i = 0; i < 16; i++) {
            if (i < 10) {
                numberButtons[i] = new JButton(String.valueOf(i));
            } else {
                numberButtons[i] = new JButton(Character.toString((char) ('A' + (i - 10))));
            }
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);
            panel.add(numberButtons[i]);
        }

        panel.add(addButton);
        panel.add(subButton);
        panel.add(mulButton);
        panel.add(divButton);

        panel.add(decButton);
        panel.add(equButton);

        frame.add(panel);
        frame.add(negButton);
        frame.add(delButton);
        frame.add(clrButton);
        frame.add(textfield);
        frame.add(binaryButton);
        frame.add(decimalButton);
        frame.add(hexadecimalButton);

        binaryButton.setBounds(50, 70, 100, 50);
        decimalButton.setBounds(150, 70, 100, 50);
        hexadecimalButton.setBounds(250, 70, 120, 50);

        radixButtonGroup = new ButtonGroup();
        radixButtonGroup.add(binaryButton);
        radixButtonGroup.add(decimalButton);
        radixButtonGroup.add(hexadecimalButton);

        binaryButton.addActionListener(this);
        decimalButton.addActionListener(this);
        hexadecimalButton.addActionListener(this);

        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                textfield.setText(textfield.getText().concat(String.valueOf(i)));
            }
        }
        if (e.getSource() == decButton) {
            textfield.setText(textfield.getText().concat("."));
        }
        if (e.getSource() == addButton) {
            num1 = Double.parseDouble(textfield.getText());
            operator = '+';
            textfield.setText("");
        }
        if (e.getSource() == subButton) {
            num1 = Double.parseDouble(textfield.getText());
            operator = '-';
            textfield.setText("");
        }
        if (e.getSource() == mulButton) {
            num1 = Double.parseDouble(textfield.getText());
            operator = '*';
            textfield.setText("");
        }
        if (e.getSource() == divButton) {
            num1 = Double.parseDouble(textfield.getText());
            operator = '/';
            textfield.setText("");
        }

        if (e.getSource() == binaryButton) {
            enableBinaryButtons();
        } else if (e.getSource() == decimalButton) {
            enableDecimalButtons();
        } else if (e.getSource() == hexadecimalButton) {
            enableHexadecimalButtons();
        }

        if (e.getSource() == equButton) {
            num2 = Double.parseDouble(textfield.getText());

            switch (operator) {
                case '+' -> result = num1 + num2;
                case '-' -> result = num1 - num2;
                case '*' -> result = num1 * num2;
                case '/' -> {
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {

                        textfield.setText("Error: Division by zero");
                        return;
                    }
                }
                default -> {
                }
            }
            textfield.setText(String.valueOf(result));
            num1 = result;
        }
        if (e.getSource() == clrButton) {
            textfield.setText("");
        }
        if (e.getSource() == delButton) {
            String string = textfield.getText();
            if (!string.isEmpty()) {
                textfield.setText(string.substring(0, string.length() - 1));
            }
        }
        if (e.getSource() == negButton) {
            double temp = Double.parseDouble(textfield.getText());
            temp *= -1;
            textfield.setText(String.valueOf(temp));
        }
    }




    private void enableBinaryButtons() {
        for (int i = 0; i < 10; i++) {
            numberButtons[i].setEnabled(i < 2);
        }

        for (int i = 10; i < 16; i++) {
            numberButtons[i].setEnabled(false);
        }

        addButton.setEnabled(true);
        subButton.setEnabled(false);
        mulButton.setEnabled(false);
        divButton.setEnabled(false);
        decButton.setEnabled(false);

        for (JButton functionButton : functionButtons) {
            functionButton.setEnabled(false);
        }
    }




    private void enableDecimalButtons() {
        for (int i = 0; i < 10; i++) {
            if (i > 0 && i < 10) {
                numberButtons[i].setEnabled(true);
            } else {
                numberButtons[i].setEnabled(false);
            }
        }

        for (int i = 10; i < 16; i++) {
            numberButtons[i].setEnabled(false);
        }

        addButton.setEnabled(true);
        subButton.setEnabled(true);
        mulButton.setEnabled(true);
        divButton.setEnabled(true);
        decButton.setEnabled(true);

        for (JButton functionButton : functionButtons) {
            functionButton.setEnabled(true);
        }
    }



    private void enableHexadecimalButtons() {
        for (int i = 0; i < 10; i++) {
            numberButtons[i].setEnabled(true);
        }
        for (int i = 10; i < 16; i++) {
            numberButtons[i].setEnabled(true);
        }
        decButton.setEnabled(true);
        for (JButton functionButton : functionButtons) {
            functionButton.setEnabled(true);
        }
    }


}

