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
		head = null;
		tail = null;
		size = 0;
	}
	// insert by head node
	public DSEList(Node head_) {
		if(head_ == null) {
			throw new NullPointerException("Empty node!");
		}
		// multiple nodes
		if(head_.next != null) {
			head = head_;
			size = 1;
		    Node current = head_;
		    while (current.next != null) {
		        size++;
		        current = current.next;
		    }
		    tail = current;
		} else {

			head = head_;
			tail = head_;
			size += 1;	
		}
	}
	
	//Takes a list then adds each element into a new list
	public DSEList(DSEList other) { // Copy constructor. 
		size = 0;
		if (other.head == null) {
			head = null;
			tail = null;

		}
		else {
			Node current = other.head;
			Node headNode = new Node(null, null ,new String (current.getString()));
			Node currCopiedNode = headNode;
			if (other.size() == 1) { // if only 1 node
				head = headNode;
				tail = headNode;
				size += 1;
			}
			else {
				size +=1;
				// multiple nodes
				while (current.next != null) {
					current = current.next;
					Node newChildNode = new Node(null, currCopiedNode, new String (current.getString()));
					currCopiedNode.next = newChildNode;
					currCopiedNode = currCopiedNode.next;
					size += 1;
				}
				head = headNode;
				tail = currCopiedNode;
			}
		
					
		}
		
	}
	
	/**
	 * 	remove the String at the parameter's index
	 */
	public String remove(int index) {
		String data = "";
		if (head == null)
        {
            throw new NullPointerException("No data at this index");
        }
		
		else if(index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Index out of bound!");
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
            else if (current == tail)
            {
                tail = current.prev;
            }
            else
            {
                current.prev.next = current.next;
                current.next.prev = current.prev;
            }
            data = current.getString();
            //Delete the middle node
            current = null;
            size --;
        }
		return data;
	}

	//returns the index of the String parameter 
	public int indexOf(String obj) {
		if (obj == null) {
			throw new NullPointerException("Null Object");
		}
		Node current = head;
		int index = 0;
		while (current != null) {
	        if (current.getString().equals(obj.toString())) {
	            return index; 
	        }
	        
	        current = current.next;
	        index++;
	    }
		return -1;
	}
	
	//returns String at parameter's index
	public String get(int index) {
		Node current = head;


		int i = 0;
		if (index >= size || index < 0) {
			return null;
		}
		// select head
		if (index == 0) {
			return head.getString();
		} //select tail
		else if (index == size - 1) {
			return tail.getString();
		}
		else {
		    while (i != index) {
		        current = current.next;
		        i++;
		    }
		}



	    return current.getString();

	}

	//checks if there is a list
	public boolean isEmpty() {
		return head == null;
	}
		

	//return the size of the list
	public int size() {
		return size;
	}
	
	//Take each element of the list a writes them to a string 
	@Override
	public String toString() {
		String out = "";
		if (head == null) {return "";}
		Node temp = head;
		out += " " + temp.getString();
		int i = 1;
		while (i < size && temp.next != null) {
			temp = temp.next;
			out += " "+ temp.getString();
			i++;
		}
		return out.substring(1);
		
	}

	//add the parameter String at of the end of the list
	public boolean add(String obj) {
		Node newNode = new Node(null,null, obj);
		if (obj == null) {
			throw new NullPointerException("Null object");
		}
		
		if (head == null)
		{
			head = tail = newNode;
			size++;
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
		size++;
		return true;
	}

	//add String at parameter's index
	public boolean add(int index, String obj){
		Node newNode = new Node(null, null, obj);
		if (obj == null) {
			throw new NullPointerException("Null object!");
		}
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
		if (obj == null) {throw new NullPointerException("Null object");}
		 Node current = head;

		    while (current != null) {
		        if (current.getString().equals(obj)) {
		            return true; 

		        }
		        current = current.next;
		    }

		    return false;
	}

	//removes the parameter's String form the list
	public boolean remove(String obj) {
		if (obj == null) {
			throw new NullPointerException("Null object!");
		}
        if (head == null || obj == null)
            return false;
 
        Node current = head;
        while(current != null) {
        	if (current.getString().equals(obj)) {
                if (current == head) {
                    head = current.next;
                }
                if (current == tail) {
                    tail = current.prev;
                }
                if (current.next != null) {
                    current.next.prev = current.prev;
                }
                if (current.prev != null) {
                    current.prev.next = current.next;
                }
                size --;
                return true;
            }
            current = current.next;

        }
        

 
        return true;
	}
	
	@Override
	public int hashCode() {
		return toString().hashCode();
	}

	@Override
	public boolean equals(Object other) {
		boolean isEqual = false;
		if (this == other)
			return true;

		// check if they are the same class
		if (other == null || !(other instanceof DSEList)) {
			return false;
		}
		if(other instanceof DSEList) {
			// check strings in the same order
			DSEList otherList = (DSEList) other;
		    Node tCurrent = this.head;
		    Node oCurrent = otherList.head;
			 
			while (tCurrent != null && oCurrent != null) {
				if (!tCurrent.getString().equals(oCurrent.getString())) {
					return false;
				}
				tCurrent = tCurrent.next;
				oCurrent = oCurrent.next;
			}
			isEqual = tCurrent == null && oCurrent == null;
		}
		
		// end of iteration
		return isEqual;
	}
	
}
