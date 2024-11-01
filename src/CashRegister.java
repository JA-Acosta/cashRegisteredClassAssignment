// The CashRegister class simulates a sale of retail items and calculates
// relevant data.

import java.util.HashMap;
import java.util.Map;

public class CashRegister {
    // Represents each item and the amounts of each item that were ordered.
    private Map<RetailItem, Integer> itemsOrdered;
    private static int orderNumber = 0; // tracks all orders made
    private double tax = .06; // sales tax at order time.

    CashRegister() {
        itemsOrdered = new HashMap<>();
        orderNumber++;
    }

    // Creates the list containing the RetailItem quantity ordered.
    CashRegister(RetailItem item, int amtOrdered) {
        this();
        itemsOrdered.put(item, amtOrdered);
    }

    // setters:

    // adds the next item to the list of items ordered
    // Param:
    //  - RetailItem item: the item that is being added to the order
    //  - amtOrdered: the number of items that were ordered.
    public void nextItem(RetailItem item, int amtOrdered) {
        itemsOrdered.put(item, amtOrdered);
    }

    public void removeItem(RetailItem item) {
        itemsOrdered.remove(item);
    }

    // getters:
    // Returns a list of the items that were ordered and the quantity of
    // each item ordered.
    public String getItems() {
        String str = "";
        for (Map.Entry<RetailItem, Integer> item: itemsOrdered.entrySet()) {
            str += String.format("%3d : %-20s  %3d X $%5.2f\n",
                    item.getKey().getItemNumber(),
                    item.getKey().getDescription(),
                    item.getValue(), item.getKey().getRetailCost()
                    );
            if ( item.getValue() != 1 ) { // includes the item subtotal
                str += String.format("                             $%7.2f\n",
                        item.getValue() * item.getKey().getRetailCost());
            }
        }
        return str;
    }

    public boolean checkItems(RetailItem item) {
        return itemsOrdered.keySet().contains(item);
    }

    public double getSubtotal() {
        double runningTotal = 0;
        for (Map.Entry<RetailItem, Integer> item : itemsOrdered.entrySet()) {
            runningTotal += item.getKey().getRetailCost() * item.getValue();
        }
        return runningTotal;
    }

    public double getTax() {
        return tax * getSubtotal();
    }

    public double getTotal() {
        return getSubtotal() * (1 + tax);
    }

    public String toString() {
        String str = "Receipt\n" + getItems() +
                "__________________________________________\n";
        str += String.format("Subtotal:                      $%8.2f\n" +
                "Tax:                           $%8.2f\n" +
                "Total:                         $%8.2f\n",
                getSubtotal(), getTax(), getTotal());
        return str;
    }
}
