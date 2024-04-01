//결과 제출시 : 삭제
package _5_H_SearchStructures;

//결과 제출시 : 학번 이름 기재하기

//결과 제출시 : 클래스 명은 Main
public class SearchStructuresHomework1 {
	
	public static void main(String[] args) throws Throwable {
		int[] input = getInput();
		
		TreeDataStack treeData = new TreeDataStack();
		NodeStack result = new NodeStack();
		
		for (int item : input) {
			if (treeData.isLeftThanPeek(item)) 
				treeData.push(item);
			else {
				int parent = treeData.findParrent(item);
				while (treeData.peek() < parent)
					result.push(treeData.pop());
				treeData.push(item);
			}
		}
		while (!treeData.isEmpty())
			result.push(treeData.pop());
		
		System.out.print(result.getString());
	}
	
	static int[] getInput() throws Throwable {
		java.util.ArrayList<Integer> items = new java.util.ArrayList<Integer>(100);
		
		java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
		String number;
		while ((number = br.readLine()) != null)
			items.add(Integer.parseInt(number));
		
		int[] result = new int[items.size()];
		for (int i = 0; i < result.length; i++)
			result[i] = items.get(i);
		
		return result;
	}
}

class TreeDataStack extends NodeStack{
	
	public int findParrent(int key) {
		if (isEmpty())
			return -1;
		
		int parent = stack[0];
		int child;
		for (int i = 1; i <= top; i++) {
			child = stack[i];
			if ((key < parent) != (child < parent))
				return parent;
			parent = child;
		}
		return parent;
	}
	
	public boolean isLeftThanPeek(int key) throws Throwable {
		if (isEmpty())
			return true;
		return key < peek();
	}
}

class NodeStack {
	
	final int STACK_SIZE = 100;
	int size = STACK_SIZE;
	protected int[] stack = new int[size];
	protected int top = -1;
	
	public void push(int item) {
		stackResize();
		stack[++top] = item;
	}
	
	public boolean isEmpty() {
		return (top < 0);
	}
	
	public int pop() throws Exception {
		if (isEmpty())
			throw new Exception("stack Empty");
		
		int result = stack[top--];
		stackResize();
		return result;
	}
	
	public int peek() throws Exception {
		if (isEmpty())
			throw new Exception("stack Empty");
		return stack[top];
	}
	
	void stackResize() {
		if (size <= top + 1) {
			int[] newStack = new int[size *= 2];
			for (int i = 0; i < stack.length; i++)
				newStack[i] = stack[i];
			stack = newStack;
		}
		else if (top*4 <= size
				&& STACK_SIZE <= size/2) {
			int[] newStack = new int[size /= 2];
			for (int i = 0; i <= top; i++)
				newStack[i] = stack[i];
			stack = newStack;
		}
	}
	
	public String getString() {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i <= top; i++)
			str.append(stack[i]).append('\n');
		return str.toString();
	}
}
