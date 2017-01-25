package BUS;

import java.sql.ResultSet;

/**
 * Created by aend on 22.01.17.
 */
public interface PerceptronListener
{
    public void onCalculationFinished(String cat, double result);
}
