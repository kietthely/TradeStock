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
		
	}
	public DSEListGeneric(NodeGeneric<T> head_) {
		head = head_;
		size += 1;
	}
	
	//Takes a list then adds each element into a new list
	public DSEListGeneric(DSEListGeneric<T> other) { // Copy constructor. 
		if (other.head == null) {
			head = null;
			tail = null;
			return;
		}
		NodeGeneric<T> current = other.head;
		NodeGeneric<T> headNode = new NodeGeneric<T>(null, null , current.get());
		NodeGeneric<T> currCopiedNode = headNode;
		while (current.next != null) {
			current = current.next;
			NodeGeneric<T> newChildNode = new NodeGeneric<T>(null, null, current.get());
			currCopiedNode.next = newChildNode;
			currCopiedNode = currCopiedNode.next;
			size +=1;
		}
		head = headNode;
		tail = currCopiedNode;
	}

	//remove and return the item at the parameter's index
	public T remove(int index) {
		T data;
		if (index < 0 || index >= size) {
	        throw new IndexOutOfBoundsException("Invalid index: " + index);
	    }
		if (head == null)
        {
            return null;
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
                if(head != null) {
                	head.prev = null;
                }
            }
            else if (current == tail)
            {
            	
                tail = current.prev;
                tail.next = null;
            }
            else
            {
                current.prev.next = current.next;
                current.next.prev = current.prev;
            }
             data =  current.get();
            //Delete the middle node
            current = null;
            size --;
        }
		return data;
	}

	//returns the index of the String parameter 
	public int indexOf(Object obj) {
		boolean found= false;
		NodeGeneric<T> current = head;
		int index = 0;
		if (current.get().equals(obj))
			return index;
		while (current.next != null && !current.get().equals(obj)) {
			current = current.next;
			if (current.get().equals(obj)) {
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
	
	//returns item at parameter's index
	public T get(int index) {
		T out = null;
		int i = 0;
		if (index > size && index < 0) {
			return out;
		}
		if (index == 0) {
			out = head.get();
		}
		else if (index == size - 1) {
			out = tail.get();
		}
		else {
			
			NodeGeneric<T> current = head;
			while (current.next != null && i < index) {
				i++;
				current = current.next;

				
			}
			if (current != null) {
				out = current.get();
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

		NodeGeneric<T> temp = head.next;
		out += " " + head.get();
		int i = 0;
		while (i < size) {
			temp = temp.next;
			out+= temp.get();
			i++;
		}
		return out.substring(1);
	}

	//add the parameter item at of the end of the list
	public boolean add(T obj) {
		NodeGeneric<T> newNode = new NodeGeneric<T>(null,null, obj);
		
		size++;
		
		if (head == null)
		{
			head = tail = newNode;
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
		return true;
	}

	//add item at parameter's index
	public boolean add(int index, T obj) {
		NodeGeneric<T> newNode = new NodeGeneric<T>(null, null, obj);

		size++;
		
		if (index == 0)
		{
			newNode.next = head;
			head.prev = newNode;
			head = newNode;
			
			if (tail == null)
			{
				tail = newNode;
			}
			
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
	public boolean contains(Object obj) {
		boolean found = false;
		NodeGeneric<T> current = head;

	    while (!current.get().equals(obj) 
	               && current.next != null)
	    {

	      current = current.next;
	    }
	    found = current.get().equals(obj);

	    return found;
	}

	//removes the parameter's item form the list
	public boolean remove(Object obj) {

        if (head == null || obj == null)
            return false;
 
        NodeGeneric<T> current = head;
        if (head.get().equals(obj))
            head = current.next;
 
        if (current.next != null)
            current.next.prev = current.prev;
 
        if (current.prev != null)
            current.prev.next = current.next;


 
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
