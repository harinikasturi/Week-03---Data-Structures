class TextState {
    String content;
    TextState next;
    TextState prev;

    public TextState(String content) {
        this.content = content;
        this.next = null;
        this.prev = null;
    }
}

class TextEditor {
    private TextState head;
    private TextState tail;
    private TextState current;
    private int maxStates;
    private int stateCount;

    public TextEditor(int maxStates) {
        this.maxStates = maxStates;
        this.stateCount = 0;
    }

    public void addState(String content) {
        TextState newState = new TextState(content);

        if (head == null) {
            head = tail = current = newState;
            stateCount = 1;
            return;
        }

        if (current.next != null) {
            current.next.prev = null;
            current.next = null;
            tail = current;
        }

        newState.prev = tail;
        tail.next = newState;
        tail = newState;
        current = newState;
        stateCount++;

        if (stateCount > maxStates) {
            head = head.next;
            head.prev = null;
            stateCount--;
        }
    }

    public String undo() {
        if (current == null || current.prev == null) {
            return null;
        }
        current = current.prev;
        return current.content;
    }

    public String redo() {
        if (current == null || current.next == null) {
            return null;
        }
        current = current.next;
        return current.content;
    }

    public String getCurrentState() {
        return current != null ? current.content : "";
    }

    public void displayHistory() {
        System.out.println("Text Editor History (max " + maxStates + " states):");
        TextState temp = head;
        while (temp != null) {
            String prefix = (temp == current) ? "> " : "  ";
            System.out.println(prefix + temp.content);
            temp = temp.next;
        }
    }
}