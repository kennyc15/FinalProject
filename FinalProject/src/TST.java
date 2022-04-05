import java.util.LinkedList;
import java.util.Queue;

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
 
 public Iterable<String> keysWithPrefix(String prefix) {
		if (prefix == null) {
			throw new IllegalArgumentException("keysWithPrefix() has null argument");
		}
		Queue<String> q = new LinkedList<String>();
		Node x = get(root, prefix, 0);
		if (x == null) return q;
		if (x.val != null) q.add(prefix);
		collect(x.mid, new StringBuilder(prefix), q);
		return q;
	}
 
 private void collect(Node x, StringBuilder prefix, Queue<String> q) {
		if (x == null) return;
		collect(x.left,  prefix, q);
		if (x.val != null) q.add(prefix.toString() + x.c);
		collect(x.mid,   prefix.append(x.c), q);
		prefix.deleteCharAt(prefix.length() - 1);
		collect(x.right, prefix, q);
	}


}