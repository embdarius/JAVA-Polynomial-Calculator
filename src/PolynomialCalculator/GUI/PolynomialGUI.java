package PolynomialCalculator.GUI;

import PolynomialCalculator.Control.PolynomialController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PolynomialGUI extends JFrame {
    private JTextField polynomialField1;
    private JTextField polynomialField2;
    private JTextArea resultField;

    private JButton multiplicateButton;
    private JButton substractButton;
    private JButton divideButton;
    private JButton moduloButton;
    private JButton addButton;
    private JButton exitButton;
    private JButton deriveButton;
    private JButton integrateButton;

    private JButton one;
    private JButton two;
    private JButton three;
    private JButton four;
    private JButton five;
    private JButton six;
    private JButton seven;
    private JButton eight;
    private JButton nine;
    private JButton zero;
    private JButton plus;
    private JButton minus;
    private JButton divide;
    private JButton multiplicate;
    private JButton power;
    private JButton point;
    private JButton X;
    private JButton del;

    private PolynomialController polynomialController = new PolynomialController();

    public PolynomialGUI(PolynomialController controller){


        setTitle("Polynomial Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        polynomialField1 = new JTextField(25);
        polynomialField2 = new JTextField(25);
        resultField = new JTextArea(5, 30);
        resultField.setEditable(false);

        // Initialize buttons
        initializeButtons();
        addActionListeners();

        // Insert Panel
        JPanel insertPanel = new JPanel();
        insertPanel.setLayout(new GridLayout(2, 2));
        insertPanel.add(new JLabel("Polynomial 1: "));
        insertPanel.add(polynomialField1);
        insertPanel.add(new JLabel("Polynomial 2: "));
        insertPanel.add(polynomialField2);
        add(insertPanel, BorderLayout.NORTH);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 2));
        buttonPanel.add(multiplicateButton);
        buttonPanel.add(substractButton);
        buttonPanel.add(divideButton);
        buttonPanel.add(addButton);
        buttonPanel.add(deriveButton);
        buttonPanel.add(integrateButton);

        // Button Panel 2
        JPanel buttonPanel2 = new JPanel();
        buttonPanel2.setLayout(new GridLayout(2, 9));
        addButtonsToPanel(buttonPanel2);

        // Combine Button Panels
        JPanel combinedButtonPanel = new JPanel(new BorderLayout());
        combinedButtonPanel.add(buttonPanel, BorderLayout.NORTH);
        combinedButtonPanel.add(buttonPanel2, BorderLayout.CENTER);
        add(combinedButtonPanel, BorderLayout.CENTER);

        // Result Panel
        JPanel resultPanel = new JPanel();
        resultPanel.add(resultField);
        add(resultPanel, BorderLayout.SOUTH);
    }

    private void initializeButtons() {
        multiplicateButton = new JButton("Multiply");
        substractButton = new JButton("Subtract");
        addButton = new JButton("Add");
        divideButton = new JButton("Divide");
        deriveButton = new JButton("Derive");
        exitButton = new JButton("Exit");
        integrateButton = new JButton("Integrate");

        one = new JButton("1");
        two = new JButton("2");
        three = new JButton("3");
        four = new JButton("4");
        five = new JButton("5");
        six = new JButton("6");
        seven = new JButton("7");
        eight = new JButton("8");
        nine = new JButton("9");
        zero = new JButton("0");
        multiplicate = new JButton("*");
        minus = new JButton("-");
        divide = new JButton("/");
        plus = new JButton("+");
        power = new JButton("^");
        point = new JButton(".");
        X = new JButton("X");
        del = new JButton("del");
    }

    private void addButtonsToPanel(JPanel panel) {
        panel.add(one);
        panel.add(two);
        panel.add(three);
        panel.add(four);
        panel.add(five);
        panel.add(six);
        panel.add(seven);
        panel.add(eight);
        panel.add(nine);
        panel.add(zero);
        panel.add(plus);
        panel.add(minus);
        panel.add(divide);
        panel.add(multiplicate);
        panel.add(power);
        panel.add(point);
        panel.add(X);
        panel.add(del);
    }
    public String getPolynomial1FieldText(){
        return this.polynomialField1.getText();
    }
    public String getPolynomial2FieldText(){
        return this.polynomialField2.getText();
    }
    public void updateResultField(String result){
        this.resultField.setText(result);
    }

    private void addActionListeners(){
        multiplicateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    polynomialController.multiplyPolynomials();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    polynomialController.addPolynomials();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        substractButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    polynomialController.substractPolynomials();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        deriveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    polynomialController.derivePolynomial();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        integrateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    polynomialController.integratePolynomial();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        divideButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    polynomialController.dividePolynomials();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

    }

}
