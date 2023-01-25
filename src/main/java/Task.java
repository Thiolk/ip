import java.util.ArrayList;

public class Task {
    private String name;
    private boolean isCompleted;

    public Task(String name, boolean isCompleted) {
        this.name = name;
        this.isCompleted = isCompleted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getStatus() {
        return isCompleted;
    }

    public void setStatus(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public static void checkTaskType(String input, ArrayList<Task> listOfTasks) {
        int endTaskTypeIndex = input.indexOf(" ");
        String taskType = input.substring(0, endTaskTypeIndex);
        switch (taskType) {
        case "todo":
            addTodoToTasksList(input, listOfTasks);
            break;
        case "deadline":
            addDeadlineToTasksList(input, listOfTasks);
            break;
        case "event":
            addEventToTasksList(input, listOfTasks);
            break;
        default:
            return;
        }
    }

    public static void addTodoToTasksList(String input, ArrayList<Task> listOfTasks) {
        int nameStartIndex = input.indexOf(" ");
        String name = input.substring(nameStartIndex + 1);
        Task todo = new Todo(name, false);
        listOfTasks.add(todo);
        printAddTaskMessage(todo, listOfTasks);
    }

    public static void addDeadlineToTasksList(String input, ArrayList<Task> listOfTasks) {
        int nameStartIndex = input.indexOf(" ");
        int nameEndIndex = input.indexOf("/by");
        String name = input.substring(nameStartIndex + 1, nameEndIndex -1);
        String dueDate = input.substring(nameEndIndex + 4);
        Task deadline = new Deadline(name, false, dueDate);
        listOfTasks.add(deadline);
        printAddTaskMessage(deadline, listOfTasks);
    }

    public static void addEventToTasksList(String input, ArrayList<Task> listOfTasks) {
        int nameStartIndex = input.indexOf(" ");
        int nameEndIndex = input.indexOf("/from");
        String name = input.substring(nameStartIndex + 1, nameEndIndex - 1);
        String remainingInfo = input.substring(nameEndIndex + 6);
        int toIndex = remainingInfo.indexOf("/to");
        String start = remainingInfo.substring(0, toIndex - 1);
        String end = remainingInfo.substring(toIndex + 4);
        Task event = new Event(name, false, start, end);
        listOfTasks.add(event);
        printAddTaskMessage(event, listOfTasks);
    }

    public String toString() {
        if (this.isCompleted) {
            return "[X] " + this.name;
        }
        return "[ ] " + this.name;
    }

    public static void listTasks(ArrayList<Task> listOfTasks) {
        for (int i = 0; i < listOfTasks.size(); i++) {
                System.out.println((i + 1) + "." + listOfTasks.get(i));
        }
    }

    public static void printUpdateStatusMessage(boolean isDone, Task currentTask) {
        if (isDone) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println("  " + currentTask);
    }

    public static void printAddTaskMessage(Task task, ArrayList<Task> listOfTasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + listOfTasks.size() + " task in the list.");
    }
}
