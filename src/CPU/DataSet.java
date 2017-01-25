package CPU;

import ROM.Config;

import java.util.ArrayList;

/**
 * Created by aend on 25.01.17.
 */
public class DataSet
{
    private String keyword;
    private int[] values;
    private String[] categories = Config.CATEGORIES;

    public DataSet(String keyword, int[]values)
    {
        this.keyword=keyword;
        this.values=values;
    }

    public String getKeyword()
    {
        return keyword;
    }

    public int[] getValues()
    {
        return values;
    }

    public int getValueByCategory(String cat)
    {
        for (int i=0; i<categories.length; i++)
        {
            if (categories[i].equals(cat))
            {
                return values[i];
            }
        }

        return -1;

    }
}
