package org.example.GUI;

import org.example.Logic.Operations;
import org.example.Model.Polynomial;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI extends JFrame implements ActionListener {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JPanel pane = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    private JButton button = new JButton("OK");
    private JTextField polynom1 = new JTextField(20);
    private JTextField polynom2 = new JTextField(20);
    private JLabel label = new JLabel("result");
    private JComboBox operationsComboBox = new JComboBox<>();

    private Operations operations = new Operations();

    public UI(String name) {
        super(name);
        c.gridx = 0;
        c.gridy = 0;
        pane.add(polynom1, c);

        c.gridx = 0;
        c.gridy = 1;
        pane.add(polynom2, c);

        c.gridx = 0;
        c.gridy = 2;
        pane.add(label, c);

        String[] operations = new String[]{"Add", "Substract", "Multiply",
                "Division", "Derivate", "Integrate"};
        this.operationsComboBox = new JComboBox(operations);

        c.gridx = 1;
        c.gridy = 0;
        pane.add(operationsComboBox, c);

        c.gridx = 1;
        c.gridy = 1;
        pane.add(button, c);
        button.addActionListener(this);

        this.add(pane);

    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub
        Object source = arg0.getSource();
        if(source == button){

            Polynomial p1 = new Polynomial().parsing(polynom1.getText());
            Polynomial p2 = new Polynomial().parsing(polynom2.getText());
            String operation = operationsComboBox.getSelectedItem().toString();
            System.out.println(operation);
            switch (operation) {
                case "Add":
                    label.setText((operations.addPolinoms(p1, p2).toString()));
                    break;
                case "Substract":
                    label.setText((operations.substractPolinoms(p1, p2).toString()));
                    break;
                case "Multiply":
                    label.setText((operations.multiplyPolinoms(p1, p2).toString()));
                    break;
                case "Derivate":
                    label.setText((operations.derivatePolynomial(p1).toString()));
                    break;
                case "Integrate":
                    label.setText((operations.integratePolynomial(p1).toString()));
                    break;
                case "Division":
                    label.setText(operations.divisionPolynoms(p1,p2));
                    break;
            }

        }

    }


}
