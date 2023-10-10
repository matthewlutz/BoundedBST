// Matthew Lutz, CS340, Project Three


import java.util.*;
import java.io.*;

public class BoundedBST {
	//Implements an BST tree of (int, name) pairs stored in parallel arrays
	//There are no duplicates in the tree
	private int root; //the index of the root node
 	private int free; //the first node in the free list
 	private int left[];
 	private int key[];
 	private String name[];
 	private int right[];
 	private int maxIndexUsed;
 	
 	public BoundedBST(int size) {
 		root = -1;
 		free = -1;
 		left = new int[size];
 		key = new int[size];
 		name = new String[size];
 		right = new int[size];
 		maxIndexUsed = -1;
 	}
 	
 	public boolean full() {
 		return maxIndexUsed == key.length-1 && free == -1;
 	}
 	
 	/*
 	 * Private method to find the next available index to insert a node. If there are no freed nodes,the method increments and returns
 	 *  the maxIndexUsed. Otherwise, it retrieves the next available index from the free list.
 	 */
 	private int getNextIndex() {
 		if(free == -1) {
 	        return ++maxIndexUsed;
 	    } else {
 	        int temp = free;
 	        free = left[free];
 	        return temp;
 	    }
 	}
 	
 	
 	//Frees a node by adding it to the free list and clearing its content.
 	public void freeNode(int n) {
 		left[n] = free;
 	    key[n] = -1;         // Set key to -1
 	    name[n] = null;      // Clear the name
 	    free = n;
 	}
 	
 	public void insert(int k, String s) {
 		//PRE: the tree is not full
 		//if k is not in the tree insert (k,s) otherwise the tree does not change
 		//if space is available in the free list then the first item on the free list
 		//must be used to store the new node otherwise use the space at maxIndexUsed+1
 		if(root == -1) {
 			root = getNextIndex();
 			key[root] = k;
 			name[root] = s;
 			left[root] = -1;
 			right[root] = -1;
 		}else {
 			insert(root,k,s);
 		}
 	}
 	
 	//Private recursive method
 	private void insert(int currNode, int k, String s) {
 		if(k == key[currNode]) {
 			return;
 		}else if(k < key[currNode]) {
 			if(left[currNode] == -1) {
 				int index = getNextIndex();
 				key[index] = k;
 	 			name[index] = s;
 	 			left[index] = -1;
 	 			right[index] = -1;
 	 			left[currNode] = index;
 			}else {
 				insert(left[currNode], k, s);
 			}
 		}else {
 			if(right[currNode] == -1) {
 				int index = getNextIndex();
 				key[index] = k;
 	 			name[index] = s;
 	 			left[index] = -1;
 	 			right[index] = -1;
 	 			right[currNode] = index;
 			}else {
 				insert(right[currNode], k, s);
 			}
 		}
 		
 	}
 	
 	
 	
 
 	
 	public void print() {
 		//print a comma delimited list of pairs, (key, name),of values in the tree in
 		//ascending order of the key on one line. a newline should be printed at the end of the
 		//line for example (10,Hodgkin), (25,Pauling), (40,Mendeleev),(50,Pasteur), (60,Avogadro),
 		//70,Boyle), (75,Curie), (80,Rutherford)
 		print(root);
 	}
 	//Private recursive method
 	private void print(int k) {
 		if(k == -1 || k >= key.length) return;
 		
 		print(left[k]);
 		System.out.print("(" + key[k] + "," + name[k] + "),");
 		System.out.println();
 		print(right[k]);
 	}
 	
 	
 	
 	
 	public void remove(int k) {
 		//if k is in the tree remove the node with key k and add the node containing k to the free list
 		//if k is not in the tree do nothing
 		root = remove(root,k);
 	}
 	//Private recursive method
 	private int remove(int r, int k){
 	    if (r == -1) return -1;  // Return -1 for non-existing node
 	    
 	    if (key[r] == k) {
 	        if (left[r] == -1 && right[r] == -1) {
 	        	key[r] = -1;
 	            freeNode(r);
 	            return -1;  // Indicate that the node was removed and the parent should update its pointer
 	        } else if (left[r] == -1) {
 	            int rightChild = right[r];
 	            freeNode(r);
 	            return rightChild;
 	        } else if (right[r] == -1) {
 	            int leftChild = left[r];
 	            freeNode(r);
 	            return leftChild;
 	        } else {
 	            int pre = predecessorKey(r);
 	            key[r] = key[pre];
 	            name[r] = name[pre];
 	            left[r] = remove(left[r], key[pre]);  // Update left child after removing the predecessor
 	        }
 	    } else if (key[r] < k) {
 	        right[r] = remove(right[r], k);  // Update right child after recursive removal
 	    } else {
 	        left[r] = remove(left[r], k);    // Update left child after recursive removal
 	    }

 	    return r;  // Return the current root of the subtree
 	}

 	//Private method to find the replacement key when removing with two children. This finds the rightmost key of the left child
 	private int predecessorKey(int r) {
 		int currKey = left[r];
		if(right[currKey] == -1) {
			return currKey;
		}
		while(right[currKey] != -1) {
			currKey = right[currKey];
		}
		return currKey;
 	}
 	
 	
 	
 	
 	public String findName(int k) {
 		//if k is in the tree return the name associated with k otherwise return null
 		return findName(root, k);
 	}
 	//Private recursive method
 	private String findName(int r, int k) { 
 		if(r == -1) return "";
 		if(key[r] == k) {
 			return name[r];
 		}else if(key[r] < k) {
 			return findName(right[r], k); 			
 		}else {
 			return findName(left[r], k);
 		}
 	}
}
