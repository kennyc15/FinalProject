import java.util.Iterator;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TST<Value> 
{
	
 private Node root; // root of trie
 
 private class Node
 {
  private Node[] next;
 char c; // character
 Node left, mid, right; // left, middle, and right subtries
 Value val; // value associated with string
 }
 
 //public static String iterableToString(Iterable<String> str){
	//   return Stream.of(str).map(String::valueOf).collect(Collectors.joining());
	//}

 
 public Value get(String key)
 {
 Node x = get(root, key, 0);
 if (x == null) return null;
 return (Value) x.val;
 }
 
 private Node get(Node x, String key, int d)
 {
 if (x == null) return null;
 char c = key.charAt(d);
 if (c < x.c) return get(x.left, key, d);
 else if (c > x.c) return get(x.right, key, d);
 else if (d < key.length() - 1)
 return get(x.mid, key, d+1);
 else return x;
 }
 
 public void put(String key, Value val)
 { root = put(root, key, val, 0); }
 
 private Node put(Node x, String key, Value val, int d)
 {
 char c = key.charAt(d);
 if (x == null) { x = new Node(); x.c = c; }
 if (c < x.c) x.left = put(x.left, key, val, d);
 else if (c > x.c) x.right = put(x.right, key, val, d);
 else if (d < key.length() - 1)
 x.mid = put(x.mid, key, val, d+1);
 else x.val = val;
 return x;
 }
 
 public Iterable<String> keys() 
 { return keysWithPrefix(""); }
 
 public Iterable<String> keysWithPrefix(String pre) 
 {
  Queue<String> q = new Queue<String>();
  collect(get(root, pre, 0), pre, q);
  return q; 
 }
 private void collect(Node x, String pre,
  Queue<String> q) 
 {
  if (x == null) return;
  if (x.val != null) {q.enqueue(pre);
  for (char c = 0; c < 256; c++) {
  collect(x.next[c], pre + c, q); 
  }
  }
 }
 
 public class Queue<String> implements Iterable<String> { 
 
  private Node first; // link to least recently added node
  private Node last; // link to most recently added node
  private int N; // number of items on the queue
  private class Node
  { // nested class to define nodes
  String item;
  Node next;
  }
  public boolean isEmpty() { return first == null; } // Or: N == 0.
  public int size() { return N; }
  
  public void enqueue(String item)
  { // Add item to the end of the list.
  Node oldlast = last;
  last = new Node();
  last.item = item;
  last.next = null;
  if (isEmpty()) first = last;
  else oldlast.next = last;
  N++;
  }
@Override
public Iterator<String> iterator()
{ return new ListIterator(); }
private class ListIterator implements Iterator<String>
{
private Node current = first;
public boolean hasNext()
{ return current != null; }

public String next()
{
String item = current.item;
current = current.next; 
return item;
}

} 
 
  } 


	 public static void main (String[] args) {
		 
}
}