
public class QuestionResponsesItems {

	private String LastUpdatedBy;

    private String AnswerList;

    private String QuestionCode;

    private String LastUpdateDate;

    private ResponseAttachments[] responseAttachments;

    private String CreatedBy;

    private Long QuestionAnswerId;

    private Links[] links;

    private String CreationDate;

    private String QuestionnaireQuestionId;

    private String AnswerCLOB;

    private String QuestionResponseId;

    public String getLastUpdatedBy ()
    {
        return LastUpdatedBy;
    }

    public void setLastUpdatedBy (String LastUpdatedBy)
    {
        this.LastUpdatedBy = LastUpdatedBy;
    }

    public String getAnswerList()
    {
        return AnswerList;
    }

    public void setAnswerList (String AnswerList)
    {
        this.AnswerList = AnswerList;
    }

    public String getQuestionCode ()
    {
        return QuestionCode;
    }

    public void setQuestionCode (String QuestionCode)
    {
        this.QuestionCode = QuestionCode;
    }

    public String getLastUpdateDate ()
    {
        return LastUpdateDate;
    }

    public void setLastUpdateDate (String LastUpdateDate)
    {
        this.LastUpdateDate = LastUpdateDate;
    }

    public ResponseAttachments[] getResponseAttachments ()
    {
        return responseAttachments;
    }

    public void setResponseAttachments (ResponseAttachments[] responseAttachments)
    {
        this.responseAttachments = responseAttachments;
    }

    public String getCreatedBy ()
    {
        return CreatedBy;
    }

    public void setCreatedBy (String CreatedBy)
    {
        this.CreatedBy = CreatedBy;
    }

    public Long getQuestionAnswerId ()
    {
        return QuestionAnswerId;
    }

    public void setQuestionAnswerId (Long QuestionAnswerId)
    {
        this.QuestionAnswerId = QuestionAnswerId;
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

    public String getQuestionnaireQuestionId ()
    {
        return QuestionnaireQuestionId;
    }

    public void setQuestionnaireQuestionId (String QuestionnaireQuestionId)
    {
        this.QuestionnaireQuestionId = QuestionnaireQuestionId;
    }

    public String getAnswerCLOB ()
    {
        return AnswerCLOB;
    }

    public void setAnswerCLOB (String AnswerCLOB)
    {
        this.AnswerCLOB = AnswerCLOB;
    }

    public String getQuestionResponseId ()
    {
        return QuestionResponseId;
    }

    public void setQuestionResponseId (String QuestionResponseId)
    {
        this.QuestionResponseId = QuestionResponseId;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [LastUpdatedBy = "+LastUpdatedBy+", AnswerList = "+AnswerList+", QuestionCode = "+QuestionCode+", LastUpdateDate = "+LastUpdateDate+", responseAttachments = "+responseAttachments+", CreatedBy = "+CreatedBy+", QuestionAnswerId = "+QuestionAnswerId+", links = "+links+", CreationDate = "+CreationDate+", QuestionnaireQuestionId = "+QuestionnaireQuestionId+", AnswerCLOB = "+AnswerCLOB+", QuestionResponseId = "+QuestionResponseId+"]";
    }
}
