/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.answer.model;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import mz.co.mozview.frameworks.core.model.GenericEntity;
import mz.org.fgh.mentoring.core.answer.dao.AnswerDAO;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.indicator.model.Indicator;
import mz.org.fgh.mentoring.core.mentorship.model.Mentorship;
import mz.org.fgh.mentoring.core.question.model.Question;

/**
 * @author Stélio Moiane
 *
 */
@NamedQueries({
        @NamedQuery(name = AnswerDAO.QUERY_NAME.fetchByMentorishipUuid, query = AnswerDAO.QUERY.fetchByMentorishipUuid) })
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE", discriminatorType = DiscriminatorType.STRING, length = 15)
@Table(name = "ANSWERS")
public abstract class Answer extends GenericEntity {

	private static final long serialVersionUID = 1L;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FORM_ID", nullable = false)
	private Form form;

	@XmlTransient
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MENTORSHIP_ID")
	private Mentorship mentorship;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "QUESTION_ID", nullable = false)
	private Question question;

	@XmlTransient
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "INDICATOR_ID")
	private Indicator indicator;

	public abstract void setValue(String value);

	public abstract String getValue();

	public Form getForm() {
		return this.form;
	}

	public void setForm(final Form form) {
		this.form = form;
	}

	public Mentorship getMentorship() {
		return this.mentorship;
	}

	public void setMentorship(final Mentorship mentorship) {
		this.mentorship = mentorship;
	}

	public Question getQuestion() {
		return this.question;
	}

	public void setQuestion(final Question question) {
		this.question = question;
	}

	public void setIndicator(final Indicator indicator) {
		this.indicator = indicator;
	}

	public Indicator getIndicator() {
		return this.indicator;
	}
}
