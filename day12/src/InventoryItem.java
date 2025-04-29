class InventoryItem {
    String itemName;
    String itemId;
    int quantity;
    double price;
    InventoryItem next;

    public InventoryItem(String itemName, String itemId, int quantity, double price) {
        this.itemName = itemName;
        this.itemId = itemId;
        this.quantity = quantity;
        this.price = price;
        this.next = null;
    }
}

class InventoryManagementSystem {
    private InventoryItem head;

    public void addAtBeginning(String itemName, String itemId, int quantity, double price) {
        InventoryItem newItem = new InventoryItem(itemName, itemId, quantity, price);
        newItem.next = head;
        head = newItem;
    }

    public void addAtEnd(String itemName, String itemId, int quantity, double price) {
        InventoryItem newItem = new InventoryItem(itemName, itemId, quantity, price);
        if (head == null) {
            head = newItem;
            return;
        }
        InventoryItem current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newItem;
    }

    public void addAtPosition(int pos, String itemName, String itemId, int quantity, double price) {
        if (pos == 1) {
            addAtBeginning(itemName, itemId, quantity, price);
            return;
        }
        InventoryItem newItem = new InventoryItem(itemName, itemId, quantity, price);
        InventoryItem current = head;
        for (int i = 1; i < pos - 1 && current != null; i++) {
            current = current.next;
        }
        if (current == null) {
            System.out.println("Position out of bounds");
            return;
        }
        newItem.next = current.next;
        current.next = newItem;
    }

    public void removeById(String itemId) {
        if (head == null) return;
        if (head.itemId.equals(itemId)) {
            head = head.next;
            return;
        }
        InventoryItem current = head;
        while (current.next != null && !current.next.itemId.equals(itemId)) {
            current = current.next;
        }
        if (current.next != null) {
            current.next = current.next.next;
        }
    }

    public void updateQuantity(String itemId, int newQuantity) {
        InventoryItem item = searchById(itemId);
        if (item != null) {
            item.quantity = newQuantity;
        }
    }

    public InventoryItem searchById(String itemId) {
        InventoryItem current = head;
        while (current != null) {
            if (current.itemId.equals(itemId)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public InventoryItem searchByName(String itemName) {
        InventoryItem current = head;
        while (current != null) {
            if (current.itemName.equals(itemName)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public double calculateTotalValue() {
        double total = 0;
        InventoryItem current = head;
        while (current != null) {
            total += current.quantity * current.price;
            current = current.next;
        }
        return total;
    }


    public void displayAll() {
        InventoryItem current = head;
        while (current != null) {
            System.out.println("ID: " + current.itemId + ", Name: " + current.itemName +
                    ", Qty: " + current.quantity + ", Price: " + current.price);
            current = current.next;
        }
    }

    public void sortByName() {
        if (head == null || head.next == null) return;
        boolean swapped;
        do {
            swapped = false;
            InventoryItem prev = null;
            InventoryItem curr = head;
            InventoryItem next = head.next;

            while (next != null) {
                if (curr.itemName.compareTo(next.itemName) > 0) {
                    if (prev == null) {
                        head = next;
                    } else {
                        prev.next = next;
                    }
                    curr.next = next.next;
                    next.next = curr;

                    prev = next;
                    swapped = true;
                } else {
                    prev = curr;
                }
                curr = prev.next;
                next = curr.next;
            }
        } while (swapped);
    }

    public void sortByPrice() {
        if (head == null || head.next == null) return;
        boolean swapped;
        do {
            swapped = false;
            InventoryItem prev = null;
            InventoryItem curr = head;
            InventoryItem next = head.next;

            while (next != null) {
                if (curr.price < next.price) {
                    if (prev == null) {
                        head = next;
                    } else {
                        prev.next = next;
                    }
                    curr.next = next.next;
                    next.next = curr;

                    prev = next;
                    swapped = true;
                } else {
                    prev = curr;
                }
                curr = prev.next;
                next = curr.next;
            }
        } while (swapped);
    }
}