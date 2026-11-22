| Condition | Action |
|---|---|
| New node inserted | Initially RED |
| New node becomes root | Make it BLACK |
| Parent is BLACK | No correction required |
| Parent RED + Uncle RED | Recolor parent and uncle BLACK, grandparent RED |
| Parent RED + Uncle BLACK (LL) | Right rotation + recoloring |
| Parent RED + Uncle BLACK (RR) | Left rotation + recoloring |
| Parent RED + Uncle BLACK (LR) | Left rotation, then right rotation |
| Parent RED + Uncle BLACK (RL) | Right rotation, then left rotation |



| Case | Structure | Rotation |
|---|---|---|
| LL | Left → Left | Right rotation |
| RR | Right → Right | Left rotation |
| LR | Left → Right | Left + Right rotation |
| RL | Right → Left | Right + Left rotation |



Deletion is slightly more complicated.

The algorithm:
1. Deletes the requested node.
2. Checks whether Red-Black properties are violated.
3. Performs recoloring when necessary.
4. Performs rotations when necessary.
5. Ensures the root is BLACK.



| Rule | Condition |
|---|---|
| 1 | Every node is RED or BLACK |
| 2 | Root is always BLACK |
| 3 | NIL leaves are always BLACK |
| 4 | RED cannot have RED children |
| 5 | Every path has equal BLACK-node count |




I understand your confusion now. You're thinking:

"If I'm inserting values in ascending order (10, 20, 30, 40, 50, 60, 70, 80), why does the tree suddenly move 40 to the root? Why does the left side become wider?"

The answer is: Red-Black Tree does not try to grow only towards the right. It tries to maintain balance while preserving BST ordering.

Let's follow the actual insertion sequence.
1. Insert 10, 20, 30
Insert 10:
10(B)

Insert 20:
10(B)
    \
    20(R)

Insert 30:

Initially:
10(B)
    \
    20(R)
        \
        30(R)

Here, we have a Right-Right violation.

Perform LEFT ROTATION around 10:
       20(B)
      /     \
   10(R)   30(R)

Notice: 20 moves to the root. The tree is not required to preserve the original root.
2. Insert 40, 50
Insert 40:
       20(B)
      /     \
   10(R)   30(R)
              \
              40(R)

Parent and uncle are RED.

Recolor:
       20(B)
      /     \
   10(B)   30(B)
              \
              40(R)

Insert 50:
       20(B)
      /     \
   10(B)   30(B)
              \
              40(R)
                 \
                 50(R)

Right-Right violation.

Left rotation around 30:
       20(B)
      /     \
   10(B)   40(B)
           /   \
        30(R) 50(R)

3. Insert 60, 70
Insert 60:
       20(B)
      /     \
   10(B)   40(B)
           /   \
        30(R) 50(R)
                  \
                  60(R)

Parent and uncle are RED.

Recolor:
       20(B)
      /     \
   10(B)   40(R)
           /   \
        30(B) 50(B)
                  \
                  60(R)

Insert 70:

Initially:
       20(B)
      /     \
   10(B)   40(R)
           /   \
        30(B) 50(B)
                  \
                  60(R)
                     \
                     70(R)

Right-Right violation.

Left rotation around 50:
       20(B)
      /     \
   10(B)   40(R)
           /   \
        30(B) 60(B)
              /   \
           50(R) 70(R)

4. Insert 80 — THIS IS WHERE YOU ARE CONFUSED
Insert 80:
       20(B)
      /     \
   10(B)   40(R)
           /   \
        30(B) 60(B)
              /   \
           50(R) 70(R)
                      \
                      80(R)

Now check:
- New node = 80(R)
- Parent = 70(R)
- Uncle = 50(R)
- Grandparent = 60(B)
Parent and uncle are both RED.

So recolor:
- 70 → BLACK
- 50 → BLACK
- 60 → RED
Now:
       20(B)
      /     \
   10(B)   40(R)
           /   \
        30(B) 60(R)
              /   \
           50(B) 70(B)
                      \
                      80(R)

But now another violation exists!

40 is RED and its child 60 is RED.

We must fix that too.

Now check again:
- Parent = 40(R)
- Uncle = 10(B)
- Grandparent = 20(B)
This is a Right-Right case.

Perform LEFT ROTATION around 20 and recolor.

Final tree:
              40(B)
             /     \
          20(R)    60(R)
          /  \     /  \
       10(B)30(B)50(B)70(B)
                             \
                             80(R)

5. Now understand WHY the left side becomes wider
Look at these two trees:

Before the final rotation:
       20(B)
      /     \
   10(B)   40(R)
           /   \
        30(B) 60(R)

After the final rotation:
          40(B)
         /     \
      20(R)    60(R)
      /  \     /  \
   10(B)30(B)50(B)70(B)
                         \
                         80(R)

The algorithm moves 40 to the root because the right subtree became too deep and violated the RED-parent rule.

After rotation:
- 20 becomes the left child of 40.
- 60 becomes the right child of 40.
- 10 and 30 remain under 20.
- 50 and 70 remain under 60.
Nothing is randomly moved. The BST ordering is preserved.

For example: