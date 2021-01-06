import java.io.*;
import java.util.*;

public class gcpc {
    public static void main(String[] args) {
        AVLTree AVLTree = new AVLTree();
        FastIO fio = new FastIO();
        int noTeams = fio.nextInt();
        int noEvents = fio.nextInt();
        Team[] teams = new Team[noTeams + 1];
        for (int i = 1 ; i <= noTeams ; i++) {
            Team team = new Team(i,0,0);
            teams[i] = team;
            AVLTree.insert(team);
        }

        for (int i = 0 ; i < noEvents ; i++) {
            int id = fio.nextInt();
            int penalty = fio.nextInt();
            AVLTree.delete(teams[id]);
            teams[id].noSolved += 1;
            teams[id].noPenalty += penalty;
            AVLTree.insert(teams[id]);
            fio.println(noTeams - AVLTree.rank(teams[1]) + 1);
        }
        fio.close();
    }
}


class Team implements Comparable<Team> {
    int id;
    int noSolved;
    int noPenalty;

    public Team(int id, int noSolved, int noPenalty) {
        this.id = id;
        this.noSolved = noSolved;
        this.noPenalty = noPenalty;
    }


    public int compareTo(Team other) {
        if (this.noSolved != other.noSolved) {
            return this.noSolved - other.noSolved;
        } else {
            if (this.noPenalty != other.noPenalty) {
                return other.noPenalty - this.noPenalty;
            } else {
                return other.id - this.id;
            }
        }
    }
}



 class AVLTree {
        public BSTVertex root;

        public AVLTree() { root = null; }

        public Team search(Team v) {
            BSTVertex res = search(root, v);
            return res == null ? null : res.key;
        }

        // helper method to perform search
        public BSTVertex search(BSTVertex T, Team v) {
            if (T == null) {       // not found
                return null;
            }
            else if (v.compareTo(T.key) == 0) {        // found
                return T;
            }
            else if (v.compareTo(T.key) > 0)  {       // search to the right
                return search(T.right, v);
            }
            else {
                return search(T.left, v);        // search to the left
            }
        }


        // public method called to find Minimum key value in BST
        public Team findMin() { return findMin(root); }

        // helper method to perform findMin
        // Question: What happens if BST is empty?
        public Team findMin(BSTVertex T) {
            if (T == null) {                       //if BST is empty
                return null;
            } else if (T.left == null) {
                return T.key;                    // this is the min
            }
            else {
                return findMin(T.left);           // go to the left
            }
        }



        // public method to find successor to given value v in BST.
        public Team successor(Team v) {
            BSTVertex vPos = search(root, v);
            return vPos == null ? null : successor(vPos);
        }

        // helper recursive method to find successor to for a given vertex T in BST
        public Team successor(BSTVertex T) {
            if (T.right != null) {                     // this subtree has right subtree
                return findMin(T.right);  // the successor is the minimum of right subtree
            } else {
                BSTVertex par = T.parent;
                BSTVertex cur = T;
                // if par(ent) is not root and cur(rent) is its right children
                while ((par != null) && (cur.key.id == par.right.key.id)) {
                    cur = par;                                         // continue moving up
                    par = cur.parent;
                }
                return par == null ? null : par.key;           // this is the successor of T
            }
        }


        // public method called to perform inorder traversal
        public void inorder() {
            inorder(root);
            System.out.println();
        }

        // helper method to perform inorder traversal
        public void inorder(BSTVertex T) {
            if (T == null) {
                return;
            }
            inorder(T.left);                               // recursively go to the left
            System.out.printf("%d ", T.key.id);                      // visit this BST node
            inorder(T.right);                             // recursively go to the right
        }

        // public method called to insert a new key with value v into BST
        public void insert(Team v) { root = insert(root, v); }

        // helper recursive method to perform insertion of new vertex into BST
        public BSTVertex insert(BSTVertex T, Team v) {
            if (T == null) {
                return new BSTVertex(v);          // insertion point is found
            }

            if (v.compareTo(T.key) > 0) {                                      // search to the right
                T.right = insert(T.right, v);
                T.right.parent = T;

            }
            else if (v.compareTo(T.key) < 0) {                                                 // search to the left
                T.left = insert(T.left, v);
                T.left.parent = T;
            }
            //inserting something already present


            if (T != null) {
                T = balance(T);
            }
            return T;                                          // return the updated BST
        }



        // public method to delete a vertex containing key with value v from BST
        public void delete(Team v) { root = delete(root, v); }            // because root might be the one being deleted.

        // helper recursive method to perform deletion
        public BSTVertex delete(BSTVertex T, Team v) {
            if (T == null) {
                return T;              // cannot find the item to be deleted
            }
            if (v.compareTo(T.key) > 0) {
                T.right = delete(T.right, v);      // search to the right
            }
            else if (v.compareTo(T.key) < 0) {                               // search to the left
                T.left = delete(T.left, v);
            }
            else {                                            // this is the node to be deleted when T.key == v
                if (T.left == null && T.right == null)                   // this is a leaf
                    T = null;                                      // simply erase this node
                else if (T.left == null && T.right != null) {   // only one child at right
                    T.right.parent = T.parent;
                    T = T.right;                                                 // bypass T
                }
                else if (T.left != null && T.right == null) {    // only one child at left
                    T.left.parent = T.parent;
                    T = T.left;                                                  // bypass T
                }
                else {                                 // has two children, find successor
                    Team successorV = successor(v);
                    T.key = successorV;         // replace this key with the successor's key
                    T.right = delete(T.right, successorV);      // delete the old successorV
                }
            }

            if (T != null) {
                T = balance(T);
            }

            return T;                                          // return the updated BST
        }


        public BSTVertex balance(BSTVertex T) {
            updateHeight(T);
            updateSize(T);

            int thisbalanceFactor = balanceFactor(T);
            int childBalanceFactor = 0;
            // right-something case
            if (thisbalanceFactor == -2) {
                childBalanceFactor= balanceFactor(T.right);
                if (childBalanceFactor >= -1 && childBalanceFactor <= 0) {
                    T = leftRotate(T);
                } else if (childBalanceFactor == 1) {
                    T.right = rightRotate(T.right);
                    T = leftRotate(T);
                }
                // left-something case
            } else if (thisbalanceFactor == 2) {
                childBalanceFactor= balanceFactor(T.left);
                if (childBalanceFactor >= 0 && childBalanceFactor <= 1) {
                    T = rightRotate(T);
                } else if (childBalanceFactor == -1) {
                    T.left = leftRotate(T.left);
                    T = rightRotate(T);

                }
            }

            return T;

        }

        public int balanceFactor(BSTVertex T) {
            if (T == null) {
                return 0;
            } else {
                int leftHeight = T.left == null ? -1 : T.left.height;
                int rightHeight = T.right == null ? -1 : T.right.height;
                int balanceFactor = leftHeight - rightHeight;
                return balanceFactor;
            }
        }

        public void updateHeight(BSTVertex T) {
            // update height
            int leftHeight = T.left == null ? -1 : T.left.height;
            int rightHeight = T.right == null ? -1 : T.right.height;
            T.height = Math.max(leftHeight, rightHeight) + 1;
        }

        public void updateSize(BSTVertex T) {
            //update size
            int leftSize = T.left == null ? 0 : T.left.size;
            int rightSize = T.right == null ? 0 : T.right.size;
            T.size = leftSize + rightSize + 1;
        }


        public BSTVertex leftRotate(BSTVertex T) {
            if (T == null) {
                return null;
            }
            BSTVertex w = T.right;
            if (w == null) {
                return null;
            }

            w.parent = T.parent;
            if (T.parent == null) {
                root = w;
            }

            T.parent = w;
            T.right = w.left;
            if (w.left != null) {
                w.left.parent = T;
            }
            w.left = T;
            updateHeight(T);
            updateSize(T);
            updateHeight(w);
            updateSize(w);

            return w;

        }

        public BSTVertex rightRotate(BSTVertex T) {
            if (T == null) {
                return null;
            }
            BSTVertex w = T.left;
            if (w == null) {
                return null;
            }

            w.parent = T.parent;
            if (T.parent == null) {
                root = w;
            }

            T.parent = w;
            T.left = w.right;
            if (w.right != null) {
                w.right.parent = T;
            }
            w.right = T;
            updateHeight(T);
            updateSize(T);
            updateHeight(w);
            updateSize(w);
            return w;

        }

        public int rank(Team v) {
            return rank(root,v);
        }

        public int rank(BSTVertex T, Team v) {
            if (v.compareTo(T.key) == 0) {
                int leftSize = T.left == null ? 0 : T.left.size;
                return leftSize + 1;
            } else if (v.compareTo(T.key) < 0) {
                return rank(T.left, v);
            } else {
                int leftSize = T.left == null ? 0 : T.left.size;
                return leftSize + 1 + rank(T.right, v);
            }
        }


    }




    // Every vertex in this AVL is a Java Class
    class BSTVertex {
        BSTVertex(Team v) { key = v; parent = left = right = null; height = 0; size = 1;}
        // all these attributes remain public to slightly simplify the code
        public BSTVertex parent, left, right;
        public Team key;
        public int height; // will be used in lecture on AVL
        public int size; // will be used in lecture on AVL

    }



