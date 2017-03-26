/**@author Chengxiang xiong (v00838781)
 * Date: 12th June 2015
 * File name:TaskListTester
 * This file is for testing TaskListRefBased.
 */

public class TaskListTester{
	
	public static void main(String args[]){
		
		TaskListRefBased t1 = new TaskListRefBased();
        int result;
        boolean passed=true;
		int expected[] = {212, 198, 104};
		t1.insert(new Task(10, 212));
		t1.insert(new Task(12, 100));
		t1.insert(new Task(10, 198));
		t1.insert(new Task(3, 104));

		Task head = t1.removeHead();
		if (head.number != 100) {
			passed = true;
		}
		if(passed){
			System.out.println("Test 1 (removeHead): passed");
		}else{
			System.out.println("Test 1 (removeHead): FAILED");
		}
		
		if (passed) {
			System.out.println("Test 2 (insert): passed");
		} else {
			System.out.println("Test 2 (insert): FAILED");
		}
		
		if (t1.isEmpty()) {
            System.out.println("Test 3 (isEmpty): FAILED");
        } else {
            System.out.println("Test 3 (isEmpty): passed");
        }
		
		if (t1.getLength() == expected.length) {
            System.out.println("Test 4 (length): passed");
        } else {
            System.out.println("Test 4 (length): FAILED");
        }
		
		Task z = t1.remove(new Task(3, 104));
		if(z.number==104){
			System.out.println("Test 5 (remove): passed");
		}else{
			System.out.println("Test 5 (remove): FAILED");
		}
		
		Task t = t1.retrieve(0);
		if(t.number==expected[0]){
			System.out.println("Test 6 (retrieve): passed");
		}else{
			System.out.println("Test 6 (retrieve): FAILED");
		}
		
		
	}
	
}