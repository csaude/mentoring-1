/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.resources.form;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.formquestion.model.FormQuestion;

/**
 * @author Stélio Moiane
 *
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FormBeanResource {

	private UserContext userContext;
	private Form form;
	private final Set<FormQuestion> formQuestions = new HashSet<>();

	public UserContext getUserContext() {
		return this.userContext;
	}

	public Form getForm() {
		return this.form;
	}

	public Set<FormQuestion> getFormQuestions() {
		return Collections.unmodifiableSet(this.formQuestions);
	}
}
