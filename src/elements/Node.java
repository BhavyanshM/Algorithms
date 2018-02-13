package elements;

public class Node{
	public int x;
	public int y;
	public int n;
	public Node next;
	public Node prev;
	
	public Node(int a, int b, int c){
		this.x = a;
		this.y = b;
		this.n = c;
	}
	
	public Node(int a, int b){
		this.x = a;
		this.y = b;
		this.n = 0;
	}
	
	public void setNext(Node a){
		this.next = a;
	}
	
	public void setPrev(Node a){
		this.prev = a;
	}
	
	public Node getNext(){
		return this.next;
	}
	
	public Node getPrev(){
		return this.prev;
	}
	
	public String toString(){
		return "(" + this.x + ", " + this.y + ")"; 
	}
}