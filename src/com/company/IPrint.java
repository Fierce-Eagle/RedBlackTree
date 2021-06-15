package com.company;

public interface IPrint<T>
{
    String Print(T data);
}

class PrintData implements IPrint<com.company.Data>
{
    @Override
    public String Print(Data data)
    {
        if (data == null)
            return "null";
        String string = "";
        for (int i = 0; i < 7; i++)
        {
            string += data.getField(i) + " ";
        }
        return string;
    }
}
