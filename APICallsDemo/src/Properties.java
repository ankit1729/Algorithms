public class Properties
{
    private String changeIndicator;

    public String getChangeIndicator ()
    {
        return changeIndicator;
    }

    public void setChangeIndicator (String changeIndicator)
    {
        this.changeIndicator = changeIndicator;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [changeIndicator = "+changeIndicator+"]";
    }
}