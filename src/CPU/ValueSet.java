package CPU;

/**
 * Created by aend on 25.01.17.
 */
public class ValueSet
{
    private String cat;
    private double val;

    public ValueSet(String cat, double val)
    {
        this.cat=cat;
        this.val=val;
    }

    public String getCat()
    {
        return cat;
    }

    public double getVal()
    {
        return val;
    }

}
