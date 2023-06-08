package unisa.dse.a2.students;

import unisa.dse.a2.interfaces.ListGeneric;

/**
 * @author simont
 *
 */
public class DSEListGeneric<T> implements ListGeneric<T> {
	
	public NodeGeneric<T> head;
	private NodeGeneric<T> tail;
	private int size;

	public DSEListGeneric() {
		head = null;
		tail = null;
		size = 0;
	}
	public DSEListGeneric(NodeGeneric<T> head_) {
		if(head_ == null) {
			throw new NullPointerException("Empty node!");
		}
		// multiple nodes
		if(head_.next != null) {
			head = head_;
			size = 1;
		    NodeGeneric<T> current = head_;
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
	public DSEListGeneric(DSEListGeneric<T> other) { // Copy constructor. 
		size = 0;
		if (other.head == null) {
			head = null;
			tail = null;

		}
		else {
			NodeGeneric<T> current = other.head;
			NodeGeneric<T> headNode = new NodeGeneric<T>(null, null ,current.get());
			NodeGeneric<T> currCopiedNode = headNode;
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
					NodeGeneric<T> newChildNode = new NodeGeneric<T>(null, currCopiedNode, current.get());
					currCopiedNode.next = newChildNode;
					currCopiedNode = currCopiedNode.next;
					size += 1;
				}
				head = headNode;
				tail = currCopiedNode;
			}
		
					
		}
	}

	//remove and return the item at the parameter's index
	public T remove(int index) {
		T data;
		if (head == null)
        {
            throw new NullPointerException("No data at this index");
        }
		
		else if(index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Index out of bound!");
		}
        else
        {
            NodeGeneric<T> current = head;
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
            data = current.get();
            //Delete the middle node
            current = null;
            size --;
        }
		return data;
	}

	//returns the index of the String parameter 
	public int indexOf(T obj) {
		if (obj == null) {
			throw new NullPointerException("Null Object");
		}
		NodeGeneric<T> current = head;
		int index = 0;
		while (current != null) {
	        if (current.get().equals(obj)) {
	            return index; 
	        }
	        
	        current = current.next;
	        index++;
	    }
		return -1;}
	
	
	//returns item at parameter's index
	public T get(int index) {
		NodeGeneric<T> current = head;


		int i = 0;
		if (index >= size || index < 0) {
			return null;
		}
		// select head
		if (index == 0) {
			return head.get();
		} //select tail
		else if (index == size - 1) {
			return tail.get();
		}
		else {
		    while (i != index) {
		        current = current.next;
		        i++;
		    }
		}



	    return current.get();
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
		NodeGeneric<T> temp = head;
		out += " " + temp.get();
		int i = 1;
		while (i < size && temp.next != null) {
			temp = temp.next;
			out += " "+ temp.get();
			i++;
		}
		return out.substring(1);
	}

	//add the parameter item at of the end of the list
	public boolean add(T obj) {
		NodeGeneric<T> newNode = new NodeGeneric<T>(null,null, obj);
		if (obj == null) {
			throw new NullPointerException("Null object");
		}
		
		if (head == null)
		{
			head = tail = newNode;
			size++;
			return true;
		}
		
		NodeGeneric<T> temp = head;
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

	//add item at parameter's index
	public boolean add(int index, T obj) {
		NodeGeneric<T> newNode = new NodeGeneric<T>(null, null, obj);
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
		
		NodeGeneric<T> temp = head.next;
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
	public boolean contains(T obj) {
		if (obj == null) {throw new NullPointerException("Null object");}
		 NodeGeneric<T> current = head;

		    while (current != null) {
		        if (current.get().equals(obj)) {
		            return true; 

		        }
		        current = current.next;
		    }

		    return false;
	}

	//removes the parameter's item form the list
	public boolean remove(T obj) {
		if (obj == null) {
			throw new NullPointerException("Null object!");
		}
        if (head == null || obj == null)
            return false;
 
        NodeGeneric<T> current = head;
        while(current != null) {
        	if (current.get().equals(obj)) {
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
		return 0;
	}

	@Override
	public boolean equals(Object other) {
		boolean isEqual = false;
		if (this == other)
			return true;

		// check if they are the same class
		if (other == null || !(other instanceof DSEListGeneric)) {
			return false;
		}
		if(other instanceof DSEListGeneric) {
			// check strings in the same order
			DSEListGeneric<?> otherList = (DSEListGeneric<?>) other;
		    NodeGeneric<T> tCurrent = this.head;
		    NodeGeneric<?> oCurrent = otherList.head;
			 
			while (tCurrent != null && oCurrent != null) {
				if (!tCurrent.get().equals(oCurrent.get())) {
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
