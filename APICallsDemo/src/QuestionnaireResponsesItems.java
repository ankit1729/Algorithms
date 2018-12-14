import java.sql.Timestamp;

public class QuestionnaireResponsesItems {

		private QuestionResponses questionResponses;
	
	    private String QuestionnaireCode;

	    private String LastUpdatedBy;

	    private String QuestionnaireResponseId;

	    private String AttemptNumber;

	    private Links[] links;

	    private String ParticipantPersonNumber;

	    private String CreationDate;

	    private String QuestionnaireVersionNumber;

	    private String Status;

	    private String LastUpdateDate;

	    private String CreatedBy;

	    private String QuestionnaireId;

	    private Timestamp SubmittedDateTime;
	    
	    
	    
	    public QuestionResponses getQuestionResponses() {
			return questionResponses;
		}

		public void setQuestionResponses(QuestionResponses questionResponses) {
			this.questionResponses = questionResponses;
		}

		public String getQuestionnaireCode ()
	    {
	        return QuestionnaireCode;
	    }

	    public void setQuestionnaireCode (String QuestionnaireCode)
	    {
	        this.QuestionnaireCode = QuestionnaireCode;
	    }

	    public String getLastUpdatedBy ()
	    {
	        return LastUpdatedBy;
	    }

	    public void setLastUpdatedBy (String LastUpdatedBy)
	    {
	        this.LastUpdatedBy = LastUpdatedBy;
	    }

	    public String getQuestionnaireResponseId ()
	    {
	        return QuestionnaireResponseId;
	    }

	    public void setQuestionnaireResponseId (String QuestionnaireResponseId)
	    {
	        this.QuestionnaireResponseId = QuestionnaireResponseId;
	    }

	    public String getAttemptNumber ()
	    {
	        return AttemptNumber;
	    }

	    public void setAttemptNumber (String AttemptNumber)
	    {
	        this.AttemptNumber = AttemptNumber;
	    }

	    public Links[] getLinks ()
	    {
	        return links;
	    }

	    public void setLinks (Links[] links)
	    {
	        this.links = links;
	    }

	    public String getParticipantPersonNumber ()
	    {
	        return ParticipantPersonNumber;
	    }

	    public void setParticipantPersonNumber (String ParticipantPersonNumber)
	    {
	        this.ParticipantPersonNumber = ParticipantPersonNumber;
	    }

	    public String getCreationDate ()
	    {
	        return CreationDate;
	    }

	    public void setCreationDate (String CreationDate)
	    {
	        this.CreationDate = CreationDate;
	    }

	    public String getQuestionnaireVersionNumber ()
	    {
	        return QuestionnaireVersionNumber;
	    }

	    public void setQuestionnaireVersionNumber (String QuestionnaireVersionNumber)
	    {
	        this.QuestionnaireVersionNumber = QuestionnaireVersionNumber;
	    }

	    public String getStatus ()
	    {
	        return Status;
	    }

	    public void setStatus (String Status)
	    {
	        this.Status = Status;
	    }

	    public String getLastUpdateDate ()
	    {
	        return LastUpdateDate;
	    }

	    public void setLastUpdateDate (String LastUpdateDate)
	    {
	        this.LastUpdateDate = LastUpdateDate;
	    }

	    public String getCreatedBy ()
	    {
	        return CreatedBy;
	    }

	    public void setCreatedBy (String CreatedBy)
	    {
	        this.CreatedBy = CreatedBy;
	    }

	    public String getQuestionnaireId ()
	    {
	        return QuestionnaireId;
	    }

	    public void setQuestionnaireId (String QuestionnaireId)
	    {
	        this.QuestionnaireId = QuestionnaireId;
	    }

	    public Timestamp getSubmittedDateTime ()
	    {
	        return SubmittedDateTime;
	    }

	    public void setSubmittedDateTime (Timestamp SubmittedDateTime)
	    {
	        this.SubmittedDateTime = SubmittedDateTime;
	    }

	    @Override
	    public String toString()
	    {
	        return "ClassPojo [QuestionnaireCode = "+QuestionnaireCode+", LastUpdatedBy = "+LastUpdatedBy+", QuestionnaireResponseId = "+QuestionnaireResponseId+", AttemptNumber = "+AttemptNumber+", links = "+links+", ParticipantPersonNumber = "+ParticipantPersonNumber+", CreationDate = "+CreationDate+", QuestionnaireVersionNumber = "+QuestionnaireVersionNumber+", Status = "+Status+", LastUpdateDate = "+LastUpdateDate+", CreatedBy = "+CreatedBy+", QuestionnaireId = "+QuestionnaireId+", SubmittedDateTime = "+SubmittedDateTime+"]";
	    }
				
}
