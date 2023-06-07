package unisa.dse.a2.students;

import unisa.dse.a2.interfaces.List;

/**
 * @author The Kiet Ly
 *
 */
public class DSEList implements List {
	
	public Node head;
	private Node tail;
	private int size;
	public DSEList() {
		
	}
	public DSEList(Node head_) {
		head = head_;
		size += 1;
	}
	
	//Takes a list then adds each element into a new list
	public DSEList(DSEList other) { // Copy constructor. 
		if (other.head == null) {
			head = null;
			tail = null;
			return;
		}
		Node current = other.head;
		Node headNode = new Node(null, null ,new String (current.getString()));
		Node currCopiedNode = headNode;
		while (current.next != null) {
			current = current.next;
			Node newChildNode = new Node(null, null, new String (current.getString()));
			currCopiedNode.next = newChildNode;
			currCopiedNode = currCopiedNode.next;
			size +=1;
		}
		head = headNode;
		tail = currCopiedNode;
				
	}
	
	/**
	 * 	remove the String at the parameter's index
	 */
	public String remove(int index) {
		String data = "";
		if (head == null)
        {
            return "Nothing to remove";
        }
        else
        {
            Node current = head;
            int currPosition = index;
            for (int i = 0; i < currPosition; i++)
            {
                current = current.next;
            }
            if (current == head)
            {
                head = current.next;
            }
            else if (current == null)
            {
                current = current.prev;
            }
            else
            {
                current.prev.next = current.next;
                current.next.prev = current.prev;
            }
            data = current.getString();
            //Delete the middle node
            current = null;
        }
		return data;
	}

	//returns the index of the String parameter 
	public int indexOf(String obj) {
		boolean found= false;
		Node current = head;
		int index = 0;
		if (current.getString().equals(obj))
			return index;
		while (current.next != null && !current.getString().equals(obj)) {
			current = current.next;
			if (current.getString().equals(obj)) {
				found = true;
			}
			index ++;
		}
		if (found) {
			return index;
		}else {
			return -1;
		}
	}
	
	//returns String at parameter's index
	public String get(int index) {
		String out = "";
		int i = 0;
		if (index > size && index < 0) {
			return out;
		}
		if (index == 0) {
			out += head.getString();
		}
		else if (index == size - 1) {
			out += tail.getString();
		}
		else {
			
			Node current = head;
			while (current.next != null && i < index) {
				i++;
				current = current.next;

				
			}
			if (current != null) {
				out+= current.getString();
			}
		}
		return out;

	}

	//checks if there is a list
	public boolean isEmpty() {
		boolean isEmpty = false;
		if (head == null) {
			isEmpty = true;
		} else {
			isEmpty = false;
		}
		return isEmpty;
	}
		

	//return the size of the list
	public int size() {
		return size;
	}
	
	//Take each element of the list a writes them to a string 
	@Override
	public String toString() {
		String out = "";

		Node temp = head.next;
		out += " " + head.getString();
		int i = 0;
		while (i < size) {
			temp = temp.next;
			out+= temp.getString();
			i++;
		}
		return out.substring(1);
		
	}

	//add the parameter String at of the end of the list
	public boolean add(String obj) {
		Node newNode = new Node(null,null, obj);
		
		size++;
		
		if (head == null)
		{
			head = tail = newNode;
			return true;
		}
		
		Node temp = head;
		while (temp.next != null)
		{
			temp = temp.next;
		}
		
		temp.next = newNode;
		newNode.prev = temp;
		
		if (tail == temp)
		{
			tail = newNode;
		}
		return true;
	}

	//add String at parameter's index
	public boolean add(int index, String obj){
		Node newNode = new Node(null, null, obj);

		size++;
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("the index is out of range");
		}
		// insert at head, O(1)
		if (index == 0)
		{	
			if(head!= null) {
			newNode.next = head;
			head.prev = newNode;
			}
			head = newNode;
			
			if (tail == null)
			{
				tail = newNode;
			}
			
			return true;
		}
		// insert at tail, O(1)
		else if (index == size -1) {
			newNode.prev = tail;
			tail.next = newNode;
			tail = newNode;
			return true;
		}
		int i = 1;
		
		Node temp = head.next;
		while (i < index)
		{
			temp = temp.next;
			i++;
		}
		

		newNode.next = temp;
		newNode.prev = temp.prev;
		temp.prev.next = newNode;
		temp.prev = newNode;
		return true;
	}

	//searches list for parameter's String return true if found
	public boolean contains(String obj) {
		boolean found = false;
	    Node current = head;

	    while (!current.getString().equals(obj) 
	               && current.next != null)
	    {

	      current = current.next;
	    }
	    found = current.getString().equals(obj);

	    return found;
	}

	//removes the parameter's String form the list
	public boolean remove(String obj) {

        if (head == null || obj == null)
            return false;
 
        Node current = head;
        if (head.getString().equals(obj))
            head = current.next;
 
        if (current.next != null)
            current.next.prev = current.prev;
 
        if (current.prev != null)
            current.prev.next = current.next;


 
        return true;
	}
	
	@Override
	public int hashCode() {
		return toString().hashCode();
	}

	@Override
	public boolean equals(Object other) {
		boolean isEqual = true;
		if (this == other)
			return true;

		// check if they are the same class
		if (other == null || !(other instanceof DSEList)) {
			return false;
		}
		// check strings in the same order
		Node t_current = this.head;
		Node o_current = ((DSEList) other).head;
		while (t_current != null && o_current != null) {
			if (!t_current.getString().equals(o_current.getString())) {
				return false;
			}
			t_current = t_current.next;
			o_current = o_current.next;
		}
		// end of iteration
		return t_current == null && o_current == null;
	}
	
}
