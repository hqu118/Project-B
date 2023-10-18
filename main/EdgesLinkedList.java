package main;

public class EdgesLinkedList {
    private Edge head;

    public EdgesLinkedList() {
        head = null;
    }

    /**
     * This method adds an edge as the start edge of the list
     *
     * @param edge to prepend
     */
    public void prepend(Edge edge) {
        edge.setNext(head); // this edge's next pointer points to the original head
        head = edge; // point head to this edge
    }

    /**
     * This method adds an edge as the end edge of the list
     *
     * @param edge to append
     */
    public void append(Edge edge) {
        if (size() == 0) {
            // if empty list, simply add the edge and the pointer points to null
            head = edge;
            edge.setNext(null);
        } else {
            // locate the last edge
            Edge lastEdge = head;

            while (lastEdge.getNext() != null) {
                lastEdge = lastEdge.getNext();
            }

            // rearrange the pointers
            edge.setNext(null);
            lastEdge.setNext(edge);
        }
    }

    /**
     * This method gets the edge at a given position
     *
     * @param pos: an integer, which is the position
     * @return the Edge at the position pos
     */
    public Edge get(int pos) throws InvalidPositionException {
        if (pos < 0 || pos > size() - 1) {
            throw new InvalidPositionException("Position " + pos + " outside the list boundary");
        }

        int i = 0;
        Edge edge = head;

        while (i != pos) {
            i++; // increments till the value of pos and edge will be set to the edge at that pos
            edge = edge.getNext();
        }

        return edge;

    }

    public int size() {
        int i = 0;
        Edge edge = head;

        // increment i until the the edge reaches null, this i value is the size of the
        // list
        while (edge != null) {
            i++;
            edge = edge.getNext();
        }

        return i;
    }

    /**
     * This method is used for printing the data in the list from head till the last
     * node
     */
    public void print() {
        Edge edge = head;
        while (edge != null) {
            System.out.println(edge);
            edge = edge.getNext();
        }
    }

        /**
     * This method adds an edge at a given position in the List
     *
     * @param pos:  an integer, which is the position
     * @param edge: the edge to add
     * @throws InvalidPositionException
     */
    public void insert(int pos, Edge edge) throws InvalidPositionException {
        if (pos < 0 || pos > size() - 1) {
            throw new InvalidPositionException("Position " + pos + " outside the list boundary");
        }
        
        if(pos == 0) {
        	// if insert to the start of the list, it is just prepending it
        	this.prepend(edge);
        }else {
        	int i = 0;
            Edge edgeInList = head;
            Edge previousEdge = head;
            
            // increment i until it reaches the position value
            while(i != pos) {
            	i++;
            	//previous edge will be the edge at index pos-1
            	previousEdge = edgeInList;
            	//edgeInList edge will be the edge at index pos+1
            	edgeInList = edgeInList.getNext(); 
            }
            
            //rearrange the pointers
            previousEdge.setNext(edge);
            edge.setNext(edgeInList);
        }
    }

    /**
     * This method removes an edge at a given position
     *
     * @param pos: an integer, which is the position
     */
    public void remove(int pos) throws InvalidPositionException {
        if (pos < 0 || pos > size() - 1) {
            throw new InvalidPositionException("Position " + pos + " outside the list boundary");
        }
        
        // if remove from index 0, the head will point to the edge at index 1
        if(pos == 0){
        	Edge edgeInList = head;
        	head = edgeInList.getNext();
        }else {
        	int i = 0;
            Edge edgeInList = head;
            Edge previousEdge = head;
            Edge edgeAfter = head;
            
            //increment i until it reaches the position value
            while(i != pos) {
            	i++;
            	//previous edge is edge at index pos-1
            	previousEdge = edgeInList; 
            	//edgeInList edge is edge at index pos, which is the edge we want to remove
            	edgeInList = edgeInList.getNext();
            }
            
            //edgeAfter is edge at index pos+1
            edgeAfter = edgeInList.getNext();
            
            //rearrange the pointer
            previousEdge.setNext(edgeAfter);
        }
    }
}