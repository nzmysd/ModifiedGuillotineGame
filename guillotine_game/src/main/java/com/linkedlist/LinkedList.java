package com.linkedlist;
import java.util.NoSuchElementException;

/** 
 * A class to represent a linked list of nodes.
 */
public class LinkedList<T> implements Iterable<T> {
  /** the first node of the list, or null if the list is empty */
  private LLNode<T> firstNode;
  
  /**
   * Creates an initially empty linked list
   */
  public LinkedList() {
    firstNode = null;
  }
  
  /**
   * Returns the first node.
   */
  public LLNode<T> getFirstNode() {
    return firstNode;
  }
  
  /**
   * Changes the front node.
   * @param node  the node that will be the first node of the new linked list
   */
  public void setFirstNode(LLNode<T> node) {
    this.firstNode = node;
  }
  
  /**
   * Return whether the list is empty
   * @return true if the list is empty
   */
  public boolean isEmpty() {
    return (getFirstNode() == null);
  }
  
  /**
   * Add an element to the front of the linked list
   */
  public void addToFront(T element) {
    setFirstNode(new LLNode<T>(element, getFirstNode()));
  }
  
  /**
   * Removes and returns the element at the front of the linked list
   * @return the element removed from the front of the linked list
   * @throws NoSuchElementException if the list is empty
   */
  public T removeFromFront() {
    if (isEmpty())
      throw new NoSuchElementException();
    else {
      T save = getFirstNode().getElement();
      setFirstNode(getFirstNode().getNext());
      return save;
    }
  }
  
  /**
   * Returns the length of the linked list
   * @return the number of nodes in the list
   */
  public int length() {
    int count = 0;
    LLNode<T> nodeptr = getFirstNode();
    while (nodeptr != null) {
      count++;
      nodeptr = nodeptr.getNext();
    }
    return count;
  }
  
  /**
   * Adds an element to the end of the linkd list
   * @param element the element to insert at the end
   */
  public void addToEnd(T element) {
    if (isEmpty())
      addToFront(element);
    else {
      LLNode<T> nodeptr = getFirstNode();
      while (nodeptr.getNext() != null) 
        nodeptr = nodeptr.getNext();
      nodeptr.setNext(new LLNode<T>(element, null));
    }
  }
  
  /**
   * moves the first node of the list back n places leaving the 
   * rest of the list nodes in place.
   * The second node of the list becomes the new first node.
   * If there are not at least n+1 nodes in the list, the method does not 
   * change the list.
   * @param n is the number of nodes we move the first node back by.
   */
  public void moveBack(int n) {
    if (length() < n+1) return;
    LLNode<T> firstNode = getFirstNode();
    LLNode<T> curr = firstNode;
    setFirstNode(firstNode.getNext());
    for(int i=0;i<n;i++){
      curr = curr.getNext();
    }
    firstNode.setNext(curr.getNext());
    curr.setNext(firstNode);
  }
  
  /**
   * moves the first node to become the last node of the list, 
   * and the second node of the list is now the new first node, 
   * and the rest of the nodes are unchanged 
   * (so the previous first node is now the second node).
   */
  public void moveFirstToLast() {
    LLNode<T> firstNode = getFirstNode();
    LLNode<T> curr = firstNode;
    setFirstNode(firstNode.getNext());
    while(curr.getNext()!=null){
      curr = curr.getNext();
    }
    firstNode.setNext(curr.getNext());
    curr.setNext(firstNode);
  }
  
  /*
   * the last node of the list is now the first node, 
   * and the rest of the nodes of the list are unchanged.
   */
  public void moveLastToFirst() {
    LLNode<T> firstNode = getFirstNode();
    LLNode<T> curr = firstNode;
    while(curr.getNext().getNext()!=null){
      curr = curr.getNext();
    }
    (curr.getNext()).setNext(firstNode);
    setFirstNode(curr.getNext());
    curr.setNext(null);
  }
  
  /*
   * reverses all the nodes of the list.
   */
  public void reverseList() {
    LLNode<T> prev = null;
    LLNode<T> curr = getFirstNode();
    LLNode<T> next = null;
    
    while(curr!=null){
      next = curr.getNext();
      curr.setNext(prev);
      prev = curr;
      curr = next;
    }
    setFirstNode(prev);
  }
  
  /*
   * reverses the first k nodes of the linked list. 
   * The rest of the list is unchanged. 
   * If there are not at least k nodes in the list, 
   * the method makes no changes.
   * @param k is the number of nodes that are reversed. 
   */
  public void reverseFirstK(int k) {
    if (length() < k) return;
    LLNode<T> prev = null;
    LLNode<T> curr = getFirstNode();
    LLNode<T> firstNode = curr;
    LLNode<T> next = null;
    for(int i=0;i<k;i++){
      next = curr.getNext();
      curr.setNext(prev);
      prev = curr;
      curr = next;
    }
    setFirstNode(prev);
    firstNode.setNext(curr);
  }
  
  /**
   * Return an iterator for this list
   * @return the iterator for the list
   */
  @Override
  public LinkedListIterator<T> iterator() {
    return new LinkedListIterator<T>(getFirstNode());
  }
  
  /* Static methods and generics: 
   *   Generic types only go with instance methods and fields
   *   If I want a generic here, I must declare it in the method header,
   *   before the return type 
   */
  
  /**
   * Prints the contents of a list to System.out.
   * @param list the list to print
   */
  public static <S> void printList(LinkedList<S> list) {
    for (S element : list) {
      System.out.print(element);
      System.out.print(" ");
    }
    System.out.println();
  }
  
  /* Generic types and wildcards:
   *    If we don't care what the generic type is because we don't use that type 
   *     (other than calling Object methods on it)
   *    we can use a wildcard that means we don't care what the generic type is 
   */
  
  /**
   * Prints the contents of a linked list to System.out.
   * @param list the linked list to print
   */
  public static void printList2(LinkedList<?> list) {
    for (Object element : list) {
      System.out.print(element);
      System.out.print(" ");
    }
    System.out.println();
  }
  
  /**
   * Generates and returns a string representation of this line of cards.
   * @return a string representation of this line of cards.
   */
  @Override
  public String toString() {
    String reString = "";
    LLNode<T> curr = getFirstNode();
    int i=0;
    while(curr!=null){
      T currCard = curr.getElement();
      reString += (i+1) + ". " + currCard.toString() + "\n";
      curr = curr.getNext();
      i++;
    }
    return reString;
    
  }
  
}
