
public class Links {

	    private String name;

	    private Properties properties;

	    private String rel;

	    private String href;

	    private String kind;

	    public String getName ()
	    {
	        return name;
	    }

	    public void setName (String name)
	    {
	        this.name = name;
	    }

	    public Properties getProperties ()
	    {
	        return properties;
	    }

	    public void setProperties (Properties properties)
	    {
	        this.properties = properties;
	    }

	    public String getRel ()
	    {
	        return rel;
	    }

	    public void setRel (String rel)
	    {
	        this.rel = rel;
	    }

	    public String getHref ()
	    {
	        return href;
	    }

	    public void setHref (String href)
	    {
	        this.href = href;
	    }

	    public String getKind ()
	    {
	        return kind;
	    }

	    public void setKind (String kind)
	    {
	        this.kind = kind;
	    }

	    @Override
	    public String toString()
	    {
	        return "ClassPojo [name = "+name+", properties = "+properties+", rel = "+rel+", href = "+href+", kind = "+kind+"]";
	    }
					
}
