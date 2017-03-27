package com.Sam;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by samagbeh on 3/26/17.
 */
public class CCValidator extends JFrame {
    private JPanel rootPanel;
    private JTextField creditCardNumberTextField;
    private JButton validateButton;
    private JButton quitButton;
    private JLabel validMessageLabel;

    CCValidator() {
        super("Credit Card Validator");
        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);


        validateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String ccNumber = creditCardNumberTextField.getText();

                boolean valid = isVisaCreditCardNumberValid(ccNumber);

                if (valid) {
                    validMessageLabel.setText("Credit card number is valid");
                } else {
                    validMessageLabel.setText("Credit card number is NOT valid");
                }

            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private static boolean isVisaCreditCardNumberValid(String cc) {

        int[] numArray = new int[cc.length()]; // creates int[] with the length of the card number entered
        int x = 0; //initializes the counter
        // values will get stored in an array
        //if the remainder of the counter/2 is 0, the card number digit should be multiplied by two
        for (x = 0; x < cc.length(); x++) {
            if (x % 2 == 0) {
                numArray[x] = Integer.parseInt(String.valueOf(cc.charAt(x))) * 2;
                // if the multiplied number is a two digit number, 1 should be added to the remainder of the multiplied number/10
                if (numArray[x] > 9)
                    numArray[x] = 1 + numArray[x] % 10;
                // numbers that are not to be doubled will simply get added to the array
            } else
                numArray[x] = Integer.parseInt(String.valueOf(cc.charAt(x)));
        }
        //initialized the sum counter
        int sum = 0;
        //sums the values in the array
        int y = 0;
        for (y = 0; y < numArray.length; y++) {
            sum += numArray[y];


        }

        if (numArray.length != 16) { //checks to see if there are 16 digits in the array
            return false;

        } else if (numArray[0] != 8) { // checks to see if the first digit starts with a 4
            return false;

        } else if (sum % 10 != 0) { // checks to see if sum has a 0 remainder
            return false;

        } else { // if there are 16 digits in the array, the first digit starts with 4 and the sum of the digits in the array is 0, the card is valid
            return true;
        }

    }

}