package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main
{

    public static void main(String[] args) throws IOException
    {
	// write your code here
        RedBlackTree<Data> tree1 = new RedBlackTree<>();
        RedBlackTree<Data> tree2 = new RedBlackTree<>();

        System.out.print("Количество записей в 1 дереве: ");
        int count1 = (new Scanner(System.in)).nextInt();
        System.out.print("Количество записей во 2 дереве: ");
        int count2 = (new Scanner(System.in)).nextInt();

        System.out.print("№ сортируемого поля: ");
        int searchField = (new Scanner(System.in)).nextInt();

        File file = new File("input.txt");
        if (file.exists())
            file.delete();

        float firstTreeTime = SomeFunction(count1, tree1, searchField);
        float secondTreeTime = SomeFunction(count2, tree2, searchField);
        if (firstTreeTime > secondTreeTime)
        {
            System.out.println("Дерево 1:");
            tree1.PrintData(new PrintData());
        }
        else
        {
            System.out.println("Дерево 2:");
            tree2.PrintData(new PrintData());
        }
        System.out.println("\n" + "______________________________________________");
        System.out.println("Средняя оценка:");
        System.out.println("Дерево 1: " + firstTreeTime);
        System.out.println("Дерево 2: " + secondTreeTime);
    }

    private static float SomeFunction(int count, RedBlackTree tree, int searchField) throws IOException
    {
        ICompare compare = new ComparerData(searchField);

        int sum = 0;
        for (int i = 0; i < count; i++)
        {
            Data data = new Data();
            data.Init();
            sum += data.AverageMark();

            data.AddStringInFile(new FileWriter("input.txt", true));
            tree.Add(data, compare);
        }

        return sum / count;
    }
}
