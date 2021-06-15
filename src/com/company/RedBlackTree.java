package com.company;

public class RedBlackTree <T>
{

    private final int RED = 0;
    private final int BLACK = 1;


    private class Node
    {

        T key;
        int color = BLACK;
        Node left = nullNode, right = nullNode, parent = nullNode;

        Node(T key) {
            this.key = key;
        }
    }

    private final Node nullNode = new Node(null);
    private Node root = nullNode;

    /**
     * Печать дерева
     *
     * @param data
     *          интерфейс печати объекта класса
     */
    public void PrintData(IPrint data)
    {
        printTree(root, data);
    }
    private void printTree(Node node, IPrint data)
    {
        if (node == nullNode)
        {
            return;
        }
        printTree(node.left, data);
        System.out.print(((node.color==RED)?"Color:   Red ":"Color: Black ") + "\tKey: "+ data.Print(node.key) + " \tParent: " + data.Print(node.parent.key) + "\n");
        printTree(node.right, data);
    }

    private Node findNode(Node findNode, Node node, ICompare compare)
    {
        if (root == nullNode)
        {
            return null;
        }

        if (compare.Compare(findNode.key, node.key) < 0)
        {
            if (node.left != nullNode)
            {
                return findNode(findNode, node.left, compare);
            }
        }
        else if (compare.Compare(findNode.key, node.key) > 0)
        {
            if (node.right != nullNode)
            {
                return findNode(findNode, node.right, compare);
            }
        }
        else if (findNode.key == node.key)
        {
            return node;
        }
        return null;
    }

    /**
     * Добавление узла в дерево
     *
     * @param key
     *         объект класса для добавления
     * @param compare
     *         интерфейс сравнения одного объекта с другим
     */
    public void Add(T key, ICompare compare)
    {
        Node node = new Node (key);
        Node temp = root;
        if (root == nullNode)
        {
            root = node;
            node.color = BLACK;
            node.parent = nullNode;
        }
        else
        {
            node.color = RED;
            while (true)
            {
                if (compare.Compare(node.key, temp.key) < 0)
                {
                    if (temp.left == nullNode)
                    {
                        temp.left = node;
                        node.parent = temp;
                        break;
                    }
                    else
                    {
                        temp = temp.left;
                    }
                }
                else
                {
                    if (temp.right == nullNode)
                    {
                        temp.right = node;
                        node.parent = temp;
                        break;
                    }
                    else
                    {
                        temp = temp.right;
                    }
                }
            }
            fixTree(node);
        }
    }

    /**
     * Восстановление баланса
     * @param node
     *          узел
     */
    private void fixTree(Node node)
    {
        while (node.parent.color == RED)
        {
            Node uncle = nullNode;
            if (node.parent == node.parent.parent.left)
            {
                uncle = node.parent.parent.right;

                if (uncle != nullNode && uncle.color == RED)
                {
                    node.parent.color = BLACK;
                    uncle.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                    continue;
                }
                if (node == node.parent.right)
                {
                    // Двойной поворот
                    node = node.parent;
                    rotateLeft(node);
                }
                node.parent.color = BLACK;
                node.parent.parent.color = RED;

                rotateRight(node.parent.parent);
            }
            else
                {
                uncle = node.parent.parent.left;
                if (uncle != nullNode && uncle.color == RED)
                {
                    node.parent.color = BLACK;
                    uncle.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                    continue;
                }
                if (node == node.parent.left)
                {
                    // Двойной поворот
                    node = node.parent;
                    rotateRight(node);
                }
                node.parent.color = BLACK;
                node.parent.parent.color = RED;

                rotateLeft(node.parent.parent);
            }
        }
        root.color = BLACK;
    }

    void rotateLeft(Node node)
    {
        if (node.parent != nullNode)
        {
            if (node == node.parent.left)
            {
                node.parent.left = node.right;
            }
            else
            {
                node.parent.right = node.right;
            }
            node.right.parent = node.parent;
            node.parent = node.right;

            if (node.right.left != nullNode)
            {
                node.right.left.parent = node;
            }
            node.right = node.right.left;
            node.parent.left = node;
        }
        else
        {
            Node right = root.right;
            root.right = right.left;
            right.left.parent = root;
            root.parent = right;
            right.left = root;
            right.parent = nullNode;
            root = right;
        }
    }

    void rotateRight(Node node)
    {
        if (node.parent != nullNode)
        {
            if (node == node.parent.left)
            {
                node.parent.left = node.left;
            }
            else
            {
                node.parent.right = node.left;
            }

            node.left.parent = node.parent;
            node.parent = node.left;
            if (node.left.right != nullNode)
            {
                node.left.right.parent = node;
            }
            node.left = node.left.right;
            node.parent.right = node;
        }
        else
        {
            Node left = root.left;
            root.left = root.left.right;
            left.right.parent = root;
            root.parent = left;
            left.right = root;
            left.parent = nullNode;
            root = left;
        }
    }






    /**
     * Поиск минимального поддерева
     * @param subTreeRoot
     *        корень поддерева
     * @return
     *        возвращает минимальное поддерево минимального поддерева
     */
    Node treeMinimum(Node subTreeRoot)
    {
        while(subTreeRoot.left!= nullNode)
        {
            subTreeRoot = subTreeRoot.left;
        }
        return subTreeRoot;
    }
}
