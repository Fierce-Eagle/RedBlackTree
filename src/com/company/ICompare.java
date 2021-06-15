package com.company;

public interface ICompare<T>
{
     int Compare(T data, T data1);
}

class ComparerData implements ICompare<com.company.Data>
{
    int field;
    public  ComparerData(int field)
    {
        this.field = field;
    }
    @Override
    public int Compare(Data data, Data data1)
    {
        if (field == 0)
            return data.getField(field).compareTo(data1.getField(field));
        else
            return Integer.parseInt(data.getField(field)) - Integer.parseInt(data1.getField(field));
    }
}