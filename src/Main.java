/* Programmer: JAAR
*  Assignment Chapter 6: Question 8
*  Purpose: Asks the user for quantities of items being purchased and displays
*  the subtotal, sales tax, and total.
*  Date modified: 11/01/2024
*  IDE/Toool used: IntelliJ
*/

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Company items list:
        RetailItem[] techItems = new RetailItem[]{
            new RetailItem("Smartphone", 1, 300, 499.99),
            new RetailItem("Laptop", 2, 800, 1299.99),
            new RetailItem("Wireless Headphones", 3, 50, 99.99),
            new RetailItem("Smartwatch", 4, 150, 249.99),
            new RetailItem("Tablet", 5, 250, 399.99)
        };
        intro(techItems);
        CashRegister order = new CashRegister();
        int i; // Validates if the user placed another order.
        do {
            int item = 0;
            try {
                // Asks for the item number:
                item = getNumber(techItems.length + 1,
                        "What item did the customer get?",
                        "Enter an item number"
                ); // does not adjust for zero index.
                // Checks if the item number has already been inputted:
                if (item-- == -1){ // checks if the item number is equal to
                    // -1 otherwise, 1 is subtracted from the itemNumber to
                    // adjust it for the list element.
                    continue;
                }
                if (order.checkItems(techItems[item])){
                    throw new IllegalArgumentException();
                }
                // Asks for the quantity ordered:
                int stock = 10;
                int quantity = getNumber(
                        stock,
                "How many " + techItems[item].getDescription() +
                        " did the customer purchase?",
                        "Invalid Quantity! Current Stock is " + stock
                );
                if (quantity == -1) { // if the quantity input is cancelled,
                    // the item is removed from the order.
                    order.removeItem(techItems[item]);
                    continue;
                }
                order.nextItem(techItems[item], quantity);
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null,
                        "You already added "
                                + techItems[item].getDescription() + " to the" +
                                " customer order."
                        , "Error", JOptionPane.WARNING_MESSAGE
                        );
            } finally {
                i = JOptionPane.showConfirmDialog(null,
                        "Did the customer order another item?"
                );
            }
        } while (i == JOptionPane.YES_OPTION);
        System.out.println();
        System.out.println();
        // Prints the receipt
        System.out.println(order);
    }

    // Prints the items sold by the company
    // Param:
    //  - RetailItem[] techItems: the list of all items sold by the company
    public static void intro(RetailItem[] techItems){
        System.out.println("Which items did the customer order from our " +
                "catalog?: ");
        for (int i = 0; i < techItems.length; i++) {
            System.out.printf("    %d: %s\n",
                    i + 1, techItems[i].getDescription());
        }
    }

    // Asks the user to enter a number that satisfies the question otherwise
    // throws an error.
    // If the user exits or cancels returns -1.
    // Param:
    //  - int range
    // Return:
    //  - int input
    public static int getNumber(int range, String question, String error) {
        int input;
        do {
            try {
                String response = JOptionPane.showInputDialog(
                        question);
                if (response == null) {
                    return -1;
                }
                input = Integer.parseInt(response);
                if (input < 1 || range < input ) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,
                        error, "Error",
                        JOptionPane.WARNING_MESSAGE);
                continue;
            }
            return input;
        } while (true);
    }
}