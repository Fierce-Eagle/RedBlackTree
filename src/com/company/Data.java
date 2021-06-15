package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Data
{
    private int markBook, mark1, mark2, mark3, mark4, mark5;
    private String family;
    private String[] stringArray;
    //
    // Конструктор
    //
    public void Init()
    {
        getFamily();
        markBook = getRandDigit(4);
        mark1 = getRandDigit(2);
        mark2 = getRandDigit(2);
        mark3 = getRandDigit(2);
        mark4 = getRandDigit(2);
        mark5 = getRandDigit(2);
        String string = family + " " +  markBook + " " + mark1 + " " + mark2 + " " + mark3 + " " + mark4 + " " + mark5;
        stringArray = string.split(" ");
    }
    //
    // Запись в файл
    //
    public void AddStringInFile(FileWriter writer)
    {
        try
        {
            writer.write(family + " " +  markBook + " " + mark1 + " " + mark2 + " " + mark3 + " " + mark4 + " " + mark5 + "\r\n");
            writer.close();

        } catch (IOException e)
        {
            e.getMessage();
        }

    }
    //
    // Чтение из файла
    //
    public void ReadStringFromFile(FileReader reader, int position) throws IOException
    {
        BufferedReader read = new BufferedReader(reader);
        for (int i = 0; i < position; i++)
        {
            read.readLine();
        }



        String str = read.readLine();
        stringArray =  str.split(" ");

        family = stringArray[0];
        markBook = Integer.parseInt(stringArray[1]);
        mark1 = Integer.parseInt(stringArray[2]);
        mark2 = Integer.parseInt(stringArray[3]);
        mark3 = Integer.parseInt(stringArray[4]);
        mark4 = Integer.parseInt(stringArray[5]);
        mark5 = Integer.parseInt(stringArray[6]);
        reader.close();



    }
    //
    // Возврат нужного поля
    //
    public String getField(int i)
    {
        return stringArray[i];
    }
    //
    // Средняя оценка
    //
    public float AverageMark()
    {
        return (mark1 + mark2 + mark3 + mark4 + mark5) / 5;
    }
    //
    // Рандомное число
    //
    private int getRandDigit(int lenght)
    {
        int digit = 1;
        for (int i = 0; i < lenght; i++)
        {
            digit *= 10;
        }
        return (int)(Math.random() * digit);
    }
    //
    // Фамилия
    //
    private void getFamily()
    {
        //char[] buffer = new char[10];
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader("family.txt"));
            int leigh = getRandDigit(1);
            for (int i = 0; i < leigh; i++)
            {
                reader.readLine();
            }
            family = reader.readLine();


        } catch (Exception e)
        {
            e.getMessage();
        }
    }
}
