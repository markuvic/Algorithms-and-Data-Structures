/**
 * TaskListNode.java
 * Created for CSC 115 Assignment Two.
 * TaskListNode Object contains the object task
 */
class TaskListNode {
    Task task;
    TaskListNode next;

    TaskListNode(Task task) {
        this.task = task;
        this.next = null;
    }
}
