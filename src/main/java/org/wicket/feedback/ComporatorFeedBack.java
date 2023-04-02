package org.wicket.feedback;

import org.apache.wicket.feedback.FeedbackMessage;

import java.io.Serializable;
import java.util.Comparator;

public class ComporatorFeedBack implements Comparator<FeedbackMessage>, Serializable {

	@Override
	public int compare(FeedbackMessage arg0, FeedbackMessage arg1) {
		return (arg1.getLevel() - arg0.getLevel());
	}

}
