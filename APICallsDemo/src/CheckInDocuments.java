
public class CheckInDocuments {

		private String limit;

	    private String hasMore;

	    private String count;

	    //private CheckInDocumentsItems[] items;

	    private Links[] links;

	    private String offset;

	    public String getLimit ()
	    {
	        return limit;
	    }

	    public void setLimit (String limit)
	    {
	        this.limit = limit;
	    }

	    public String getHasMore ()
	    {
	        return hasMore;
	    }

	    public void setHasMore (String hasMore)
	    {
	        this.hasMore = hasMore;
	    }

	    public String getCount ()
	    {
	        return count;
	    }

	    public void setCount (String count)
	    {
	        this.count = count;
	    }

	   /* public CheckInDocumentsItems[] getItems ()
	    {
	        return items;
	    }

	    public void setItems (CheckInDocumentsItems[] items)
	    {
	        this.items = items;
	    }*/

	    public Links[] getLinks ()
	    {
	        return links;
	    }

	    public void setLinks (Links[] links)
	    {
	        this.links = links;
	    }

	    public String getOffset ()
	    {
	        return offset;
	    }

	    public void setOffset (String offset)
	    {
	        this.offset = offset;
	    }

	    @Override
	    public String toString()
	    {
	        return "ClassPojo [limit = "+limit+", hasMore = "+hasMore+", count = "+count+", items = "+/*items+*/", links = "+links+", offset = "+offset+"]";
	    }
				
				
}
