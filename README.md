# BoundedBST
Bounded Binary Search Tree (BoundedBST)
Matthew Lutz

Overview
This project implements a bounded Binary Search Tree (BST) using parallel arrays. Each node in the tree holds an integer key and an associated name. The tree does not allow duplicates.

Features
BoundedBST(int size): Constructor to initialize an empty BST with a given size.

full(): Checks if the BST is full.

insert(int k, String s): Inserts a node with the specified key k and name s into the BST.

print(): Prints a comma-delimited list of pairs (key, name) of values in the BST in ascending order of the key.

remove(int k): Removes the node with the specified key k from the BST.

findName(int k): Returns the name associated with the key k.

Private Helper Methods
getNextIndex(): Determines the next available index to insert a node. It either increments and returns the maxIndexUsed or retrieves the next available index from the free list.

freeNode(int n): Frees a node, clears its content, and adds it to the free list.

predecessorKey(int r): Finds the rightmost key of the left child, useful when removing a node with two children.

Data Structure
Vertices are maintained in parallel arrays: key[], name[], left[], and right[].

free: Points to the first node in the free list.

root: Points to the root of the BST.

maxIndexUsed: Indicates the maximum index used in the parallel arrays.

Usage
To use this class:

Create an instance of BoundedBST with the desired size.
Use the insert, remove, and findName methods to manipulate and query the BST.
Use the print method to get a visual representation of the BST.
