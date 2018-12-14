
public class ResponseAttachmentsItems {

	private String UserName;

    private String ErrorStatusCode;

    private String UploadedText;

    private String UploadedFileLength;

    private String ExpirationDate;

    private String LastUpdatedByUserName;

    private String Url;

    private String LastUpdateDate;

    private String FileUrl;

    private String Uri;

    private String UploadedFileContentType;

    private String ContentRepositoryFileShared;

    private String DatatypeCode;

    private String CreatedByUserName;

    private String LastUpdatedBy;

    private Long AttachedDocumentId;

    private String DmFolderPath;

    private String UploadedFileName;

    private String Description;

    private String CategoryName;

    private Links[] links;

    private String CreationDate;

    private String ErrorStatusMessage;

    private String Title;

    private String FileName;

    private String CreatedBy;

    public String getUserName ()
    {
        return UserName;
    }

    public void setUserName (String UserName)
    {
        this.UserName = UserName;
    }

    public String getLastUpdatedByUserName ()
    {
        return LastUpdatedByUserName;
    }

    public void setLastUpdatedByUserName (String LastUpdatedByUserName)
    {
        this.LastUpdatedByUserName = LastUpdatedByUserName;
    }

    public String getUrl ()
    {
        return Url;
    }

    public void setUrl (String Url)
    {
        this.Url = Url;
    }

    public String getLastUpdateDate ()
    {
        return LastUpdateDate;
    }

    public void setLastUpdateDate (String LastUpdateDate)
    {
        this.LastUpdateDate = LastUpdateDate;
    }

    
    public String getContentRepositoryFileShared ()
    {
        return ContentRepositoryFileShared;
    }

    public void setContentRepositoryFileShared (String ContentRepositoryFileShared)
    {
        this.ContentRepositoryFileShared = ContentRepositoryFileShared;
    }

    public String getDatatypeCode ()
    {
        return DatatypeCode;
    }

    public void setDatatypeCode (String DatatypeCode)
    {
        this.DatatypeCode = DatatypeCode;
    }

    public String getCreatedByUserName ()
    {
        return CreatedByUserName;
    }

    public void setCreatedByUserName (String CreatedByUserName)
    {
        this.CreatedByUserName = CreatedByUserName;
    }

    public String getLastUpdatedBy ()
    {
        return LastUpdatedBy;
    }

    public void setLastUpdatedBy (String LastUpdatedBy)
    {
        this.LastUpdatedBy = LastUpdatedBy;
    }

   
    public String getDescription ()
    {
        return Description;
    }

    public void setDescription (String Description)
    {
        this.Description = Description;
    }

    public String getCategoryName ()
    {
        return CategoryName;
    }

    public void setCategoryName (String CategoryName)
    {
        this.CategoryName = CategoryName;
    }

    public Links[] getLinks ()
    {
        return links;
    }

    public void setLinks (Links[] links)
    {
        this.links = links;
    }

    public String getCreationDate ()
    {
        return CreationDate;
    }

    public void setCreationDate (String CreationDate)
    {
        this.CreationDate = CreationDate;
    }

       public String getTitle ()
    {
        return Title;
    }

    public void setTitle (String Title)
    {
        this.Title = Title;
    }

   
    public String getCreatedBy ()
    {
        return CreatedBy;
    }

    public void setCreatedBy (String CreatedBy)
    {
        this.CreatedBy = CreatedBy;
    }

    
    
    public String getErrorStatusCode() {
		return ErrorStatusCode;
	}

	public void setErrorStatusCode(String errorStatusCode) {
		ErrorStatusCode = errorStatusCode;
	}

	public String getUploadedText() {
		return UploadedText;
	}

	public void setUploadedText(String uploadedText) {
		UploadedText = uploadedText;
	}

	public String getUploadedFileLength() {
		return UploadedFileLength;
	}

	public void setUploadedFileLength(String uploadedFileLength) {
		UploadedFileLength = uploadedFileLength;
	}

	public String getExpirationDate() {
		return ExpirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		ExpirationDate = expirationDate;
	}

	public String getFileUrl() {
		return FileUrl;
	}

	public void setFileUrl(String fileUrl) {
		FileUrl = fileUrl;
	}

	public String getUri() {
		return Uri;
	}

	public void setUri(String uri) {
		Uri = uri;
	}

	public String getUploadedFileContentType() {
		return UploadedFileContentType;
	}

	public void setUploadedFileContentType(String uploadedFileContentType) {
		UploadedFileContentType = uploadedFileContentType;
	}

	public Long getAttachedDocumentId() {
		return AttachedDocumentId;
	}

	public void setAttachedDocumentId(Long attachedDocumentId) {
		AttachedDocumentId = attachedDocumentId;
	}

	public String getDmFolderPath() {
		return DmFolderPath;
	}

	public void setDmFolderPath(String dmFolderPath) {
		DmFolderPath = dmFolderPath;
	}

	public String getUploadedFileName() {
		return UploadedFileName;
	}

	public void setUploadedFileName(String uploadedFileName) {
		UploadedFileName = uploadedFileName;
	}

	public String getErrorStatusMessage() {
		return ErrorStatusMessage;
	}

	public void setErrorStatusMessage(String errorStatusMessage) {
		ErrorStatusMessage = errorStatusMessage;
	}

	public String getFileName() {
		return FileName;
	}

	public void setFileName(String fileName) {
		FileName = fileName;
	}

	@Override
    public String toString()
    {
        return "ClassPojo [UserName = "+UserName+", ErrorStatusCode = "+ErrorStatusCode+", UploadedText = "+UploadedText+", UploadedFileLength = "+UploadedFileLength+", ExpirationDate = "+ExpirationDate+", LastUpdatedByUserName = "+LastUpdatedByUserName+", Url = "+Url+", LastUpdateDate = "+LastUpdateDate+", FileUrl = "+FileUrl+", Uri = "+Uri+", UploadedFileContentType = "+UploadedFileContentType+", ContentRepositoryFileShared = "+ContentRepositoryFileShared+", DatatypeCode = "+DatatypeCode+", CreatedByUserName = "+CreatedByUserName+", LastUpdatedBy = "+LastUpdatedBy+", AttachedDocumentId = "+AttachedDocumentId+", DmFolderPath = "+DmFolderPath+", UploadedFileName = "+UploadedFileName+", Description = "+Description+", CategoryName = "+CategoryName+", links = "+links+", CreationDate = "+CreationDate+", ErrorStatusMessage = "+ErrorStatusMessage+", Title = "+Title+", FileName = "+FileName+", CreatedBy = "+CreatedBy+"]";
    }
}
