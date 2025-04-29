class Process {
    int processId;
    int burstTime;
    int priority;
    Process next;

    public Process(int processId, int burstTime, int priority) {
        this.processId = processId;
        this.burstTime = burstTime;
        this.priority = priority;
        this.next = null;
    }
}

class RoundRobinScheduler {
    private Process head;
    private Process tail;
    private int timeQuantum;

    public RoundRobinScheduler(int timeQuantum) {
        this.timeQuantum = timeQuantum;
    }

    public void addProcess(int processId, int burstTime, int priority) {
        Process newProcess = new Process(processId, burstTime, priority);
        if (head == null) {
            head = newProcess;
            newProcess.next = head;
            tail = head;
        } else {
            tail.next = newProcess;
            newProcess.next = head;
            tail = newProcess;
        }
    }

    public void removeProcess(int processId) {
        if (head == null) return;
        if (head.processId == processId) {
            if (head == tail) {
                head = tail = null;
                return;
            }
            tail.next = head.next;
            head = head.next;
            return;
        }
        Process prev = head;
        Process curr = head.next;
        while (curr != head && curr.processId != processId) {
            prev = curr;
            curr = curr.next;
        }
        if (curr != head) {
            prev.next = curr.next;
            if (curr == tail) {
                tail = prev;
            }
        }
    }

    public void simulate() {
        if (head == null) {
            System.out.println("No processes to schedule");
            return;
        }

        Process current = head;
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;
        int processCount = 0;


        Process temp = head;
        do {
            processCount++;
            temp = temp.next;
        } while (temp != head);

        System.out.println("Round Robin Scheduling with time quantum " + timeQuantum);

        while (processCount > 0) {
            System.out.println("Executing Process " + current.processId +
                    " (Burst Time: " + current.burstTime + ")");

            if (current.burstTime > timeQuantum) {
                current.burstTime -= timeQuantum;
                totalTurnaroundTime += timeQuantum;
                current = current.next;
            } else {
                totalTurnaroundTime += current.burstTime;
                int waitingTime = totalTurnaroundTime - (current.burstTime +
                        (processCount - 1) * timeQuantum);
                totalWaitingTime += waitingTime;

                System.out.println("Process " + current.processId + " completed");
                removeProcess(current.processId);
                processCount--;
                if (processCount > 0) {
                    current = current.next;
                }
            }

            displayProcesses();
        }

        System.out.println("Average Waiting Time: " + (double)totalWaitingTime / processCount);
        System.out.println("Average Turnaround Time: " + (double)totalTurnaroundTime / processCount);
    }

    public void displayProcesses() {
        if (head == null) {
            System.out.println("No processes in the scheduler");
            return;
        }
        System.out.print("Process Queue: ");
        Process temp = head;
        do {
            System.out.print("P" + temp.processId + "(" + temp.burstTime + ") ");
            temp = temp.next;
        } while (temp != head);
        System.out.println();
    }
}