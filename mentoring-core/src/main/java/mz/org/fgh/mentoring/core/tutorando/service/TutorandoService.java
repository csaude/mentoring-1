/*
 * MozView Technologies, Lda. 2010 - 2016
 */
package mz.org.fgh.mentoring.core.tutorando.service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;
import mz.org.fgh.mentoring.core.tutorando.model.Tutorando;

/**
 * @author Eusebio Jose Maposse
 *
 */
public interface TutorandoService {

	String NAME = "mz.org.fgh.mentoring.core.tutorando.service.TutorandoService";

	Tutor createTutor(final UserContext userContext, final Tutorando tutorando) throws BusinessException;

	Tutor updateTutor(final UserContext userContext, final Tutorando tutorandor) throws BusinessException;
}
