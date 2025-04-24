import java.util.*;

class Process {
    int processId;
    int burstTime;
    int remainingTime;
    int priority;
    Process next;

    public Process(int processId, int burstTime, int priority) {
        this.processId = processId;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.priority = priority;
        this.next = null;
    }
}

class RoundRobinScheduler {
    private Process head = null;
    private Process tail = null;
    private final int timeQuantum;

    private final Map<Integer, Integer> waitingTimeMap = new HashMap<>();
    private final Map<Integer, Integer> turnaroundTimeMap = new HashMap<>();
    private final Map<Integer, Integer> startTimeMap = new HashMap<>();
    private final Map<Integer, Integer> finishTimeMap = new HashMap<>();

    public RoundRobinScheduler(int timeQuantum) {
        this.timeQuantum = timeQuantum;
    }

    public void addProcess(int processId, int burstTime, int priority) {
        Process newProcess = new Process(processId, burstTime, priority);
        if (head == null) {
            head = tail = newProcess;
            newProcess.next = head;
        } else {
            tail.next = newProcess;
            tail = newProcess;
            tail.next = head;
        }
    }

    public void removeProcess(int processId) {
        if (head == null) return;

        Process current = head;
        Process prev = tail;

        do {
            if (current.processId == processId) {
                if (current == head) head = head.next;
                if (current == tail) tail = prev;

                prev.next = current.next;

                if (head == current && head == tail) head = tail = null;
                return;
            }
            prev = current;
            current = current.next;
        } while (current != head);
    }

    public void displayProcesses() {
        if (head == null) {
            System.out.println("No processes in the queue.");
            return;
        }

        Process temp = head;
        System.out.println("Current Process Queue:");
        do {
            System.out.println("PID: " + temp.processId + ", Remaining Time: " + temp.remainingTime);
            temp = temp.next;
        } while (temp != head);
    }

    public void simulate() {
        if (head == null) return;

        int currentTime = 0;
        Process current = head;

        while (head != null) {
            if (!startTimeMap.containsKey(current.processId))
                startTimeMap.put(current.processId, currentTime);

            int executedTime = Math.min(timeQuantum, current.remainingTime);
            current.remainingTime -= executedTime;
            currentTime += executedTime;

            if (current.remainingTime == 0) {
                finishTimeMap.put(current.processId, currentTime);
                turnaroundTimeMap.put(current.processId, currentTime);
                removeProcess(current.processId);
                current = head;
            } else {
                current = current.next;
            }

            displayProcesses();
            System.out.println("Time Elapsed: " + currentTime + "\n");
        }

        calculateWaitingTime();
        displayAverageTimes();
    }

    private void calculateWaitingTime() {
        for (Map.Entry<Integer, Integer> entry : turnaroundTimeMap.entrySet()) {
            int pid = entry.getKey();
            int turnaround = entry.getValue();
            int burst = startTimeMap.get(pid) + turnaround - finishTimeMap.get(pid);
            waitingTimeMap.put(pid, turnaround - burst);
        }
    }

    private void displayAverageTimes() {
        double totalWT = 0, totalTAT = 0;
        int n = turnaroundTimeMap.size();

        System.out.println("\nProcess\tWaiting Time\tTurnaround Time");
        for (int pid : turnaroundTimeMap.keySet()) {
            int wt = waitingTimeMap.get(pid);
            int tat = turnaroundTimeMap.get(pid);
            totalWT += wt;
            totalTAT += tat;
            System.out.println(pid + "\t\t" + wt + "\t\t" + tat);
        }

        System.out.printf("\nAverage Waiting Time: %.2f\n", totalWT / n);
        System.out.printf("Average Turnaround Time: %.2f\n", totalTAT / n);
    }
}

public class RoundRobinSchedulerMain {
    public static void main(String[] args) {
        RoundRobinScheduler scheduler = new RoundRobinScheduler(4); // time quantum = 4

        scheduler.addProcess(1, 10, 2);
        scheduler.addProcess(2, 5, 1);
        scheduler.addProcess(3, 8, 3);

        scheduler.simulate();
    }
}
