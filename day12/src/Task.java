class Task {
    int taskId;
    String taskName;
    int priority;
    String dueDate;
    Task next;

    public Task(int taskId, String taskName, int priority, String dueDate) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.priority = priority;
        this.dueDate = dueDate;
        this.next = null;
    }
}

class TaskScheduler {
    private Task head;
    private Task current;

    public void addAtBeginning(int taskId, String taskName, int priority, String dueDate) {
        Task newTask = new Task(taskId, taskName, priority, dueDate);
        if (head == null) {
            head = newTask;
            newTask.next = head;
            current = head;
            return;
        }
        Task last = head;
        while (last.next != head) {
            last = last.next;
        }
        newTask.next = head;
        last.next = newTask;
        head = newTask;
    }

    public void addAtEnd(int taskId, String taskName, int priority, String dueDate) {
        Task newTask = new Task(taskId, taskName, priority, dueDate);
        if (head == null) {
            head = newTask;
            newTask.next = head;
            current = head;
            return;
        }
        Task last = head;
        while (last.next != head) {
            last = last.next;
        }
        last.next = newTask;
        newTask.next = head;
    }

    public void addAtPosition(int pos, int taskId, String taskName, int priority, String dueDate) {
        if (pos == 1) {
            addAtBeginning(taskId, taskName, priority, dueDate);
            return;
        }
        Task newTask = new Task(taskId, taskName, priority, dueDate);
        Task temp = head;
        for (int i = 1; i < pos - 1 && temp.next != head; i++) {
            temp = temp.next;
        }
        newTask.next = temp.next;
        temp.next = newTask;
    }

    public void removeById(int taskId) {
        if (head == null) return;
        if (head.taskId == taskId) {
            if (head.next == head) {
                head = null;
                current = null;
                return;
            }
            Task last = head;
            while (last.next != head) {
                last = last.next;
            }
            last.next = head.next;
            head = head.next;
            return;
        }
        Task prev = head;
        Task curr = head.next;
        while (curr != head && curr.taskId != taskId) {
            prev = curr;
            curr = curr.next;
        }
        if (curr != head) {
            prev.next = curr.next;
        }
    }

    public void viewAndMoveNext() {
        if (current == null) {
            System.out.println("No tasks in scheduler");
            return;
        }
        System.out.println("Current Task - ID: " + current.taskId + ", Name: " + current.taskName +
                ", Priority: " + current.priority + ", Due: " + current.dueDate);
        current = current.next;
    }

    public void displayAll() {
        if (head == null) return;
        Task temp = head;
        do {
            System.out.println("Task ID: " + temp.taskId + ", Name: " + temp.taskName +
                    ", Priority: " + temp.priority + ", Due: " + temp.dueDate);
            temp = temp.next;
        } while (temp != head);
    }

    public Task searchByPriority(int priority) {
        if (head == null) return null;
        Task temp = head;
        do {
            if (temp.priority == priority) {
                return temp;
            }
            temp = temp.next;
        } while (temp != head);
        return null;
    }
}