/**@author Chengxiang xiong (v00838781)
 * Date: 12th June 2015
 * File name:TaskListRefBased
 * This file is provided or the file TaskSim.java
 */
public class TaskListRefBased implements TaskList {
    
	private TaskListNode head;
	
	public TaskListRefBased() {
		head=null;
    }


    public int getLength() {
        TaskListNode curr=head;
		int count=0;
		
		while(curr!=null){
			curr=curr.next;
			count++;
			}
		return count;
		
    }
	
    public boolean isEmpty() {
		if(head == null){
			return true;
		}else{
			return false;
		}
    }

    public Task removeHead() {
		Task result=head.task;
		TaskListNode curr = head;
		if(isEmpty()){
			return null;
		}else if(curr.next==null){
			return result;
		}
		else{
			head=curr.next;
			curr=null;
			return result;
		}
    }
      
    public Task remove(Task t) {
		TaskListNode delete =  new TaskListNode(t);
		TaskListNode prev = null;
		TaskListNode curr = head;
		
		while (curr != null) {
			
			if ((delete.task.priority==curr.task.priority) && (delete.task.number==curr.task.number)) {
				break;
			} else {
				prev = curr;
				curr = curr.next;
			}
		}
		if (curr == null) {
			return null;
		}
		
		if (prev == null) {
			head = curr.next;
		} else {
			prev.next = curr.next;
		}
		
		return t;
    }


    public void insert(Task t) {
        
		
		TaskListNode newNode =  new TaskListNode(t);
		TaskListNode prev = null;
		TaskListNode curr = head;
		// Test if the list is empty
		if( isEmpty()){
			newNode.next=head;
			head=newNode;
			return;
		}
				
		//Comparing the priority of the node in the list
		while (curr != null) {
			if(newNode.task.priority<=curr.task.priority){
				prev = curr;
				curr = curr.next;
			}else{
				break;
			} 
		}
		
		//Inserting the new node to the list
		newNode.next = curr;
		if (prev == null) {
			head = newNode;
		} else {
			prev.next = newNode;
		}

    }


    public Task retrieve(int i) {

		TaskListNode curr=head;
		
		if(isEmpty()){
			return null;
		}
		if(i>=getLength()){
			return null;
		}
		
		for(int count=0;count<i;++count){
			curr=curr.next;
		}
		return curr.task;
    }
}