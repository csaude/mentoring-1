package mz.org.fgh.mentoring.core.tutorprogramaticarea.service;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.NoResultException;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import mz.co.mozview.frameworks.core.email.MailSenderService;
import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.service.AbstractService;
import mz.co.mozview.frameworks.core.util.PropertyValues;
import mz.co.mozview.frameworks.core.util.RandomStringFactory;
import mz.co.mozview.frameworks.core.webservices.adapter.Entry;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.co.mozview.frameworks.core.webservices.service.ClientWS;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;
import mz.org.fgh.mentoring.core.tutor.service.TutorService;
import mz.org.fgh.mentoring.core.tutorprogramaticarea.dao.TutorProgrammaticAreaDAO;
import mz.org.fgh.mentoring.core.tutorprogramaticarea.model.TutorProgrammaticArea;

/**
 * @author Eusebio Jose Maposse
 *
 */
@Service(TutorProgrammaticAreaService.NAME)
public class TutorProgrammaticAreaServiceImpl extends AbstractService implements TutorProgrammaticAreaService {

	@Inject
	private TutorProgrammaticAreaDAO tutorProgramaticAreaDAO;

	@Inject
	private TutorProgrammaticAreaQueryService tutorProgrammaticAreaQueryService;

	@Inject
	private PropertyValues propertyValues;

	@Inject
	private TutorService tutorService;

	@Inject
	private ClientWS client;

	@Inject
	private Environment environment;

	@Inject
	private MailSenderService mailSenderService;

	private static final int PASSWORD_LENTH = 4;

	@Override
	public TutorProgrammaticArea mapTutorToProgramaticArea(final UserContext userContext,
	        final TutorProgrammaticArea tutorProgramaticArea) throws BusinessException {

		final Tutor tutor = tutorProgramaticArea.getTutor();

		try {
			this.tutorProgrammaticAreaQueryService.findTutorMapByTutorAndProgrammaticArea(userContext, tutor,
			        tutorProgramaticArea.getProgrammaticArea());

			throw new BusinessException(
			        this.propertyValues.getPropValues("tutor.was.already.mapped.to.programmatic.area"));
		}
		catch (final NoResultException e) {
		}

		final TutorProgrammaticArea mapTutorProgramaticArea = this.tutorProgramaticAreaDAO.create(userContext.getUuid(),
		        tutorProgramaticArea);

		if (tutorProgramaticArea.isMappedAsUser()) {
			tutor.setAsUser();
			this.tutorService.updateTutor(userContext, tutor);

			final UserContext user = this.getUser(userContext, tutor);

			this.client.postJSON(this.environment.getProperty("account.manager.service.url"), user);

			this.sendTutorEmail(user, tutor);
		}

		return mapTutorProgramaticArea;
	}

	private void sendTutorEmail(final UserContext user, final Tutor tutor) throws BusinessException {

		this.mailSenderService.subject(this.propertyValues.getPropValues("tutor.email.subject"));
		this.mailSenderService.to(tutor.getEmail());
		this.mailSenderService.templateName("/create-user-template.vm");

		final Map<String, Object> params = new HashMap<>();
		params.put("tutor", tutor);
		params.put("password", user.getPassword());

		this.mailSenderService.send(params);
	}

	private UserContext getUser(final UserContext userContext, final Tutor tutor) {

		final UserContext context = new UserContext();
		context.setFullName(tutor.getName() + " " + tutor.getSurname());
		context.setUsername(tutor.getName().toLowerCase() + "." + tutor.getSurname().toLowerCase());
		context.setPassword(RandomStringFactory.generate(PASSWORD_LENTH).toLowerCase());
		context.setEmail(tutor.getEmail());
		context.setPhoneNumber(tutor.getPhoneNumber());
		context.setUuid(userContext.getUuid());
		context.addProperty(Entry.UUID, tutor.getUuid());

		return context;
	}

	@Override
	public TutorProgrammaticArea updateTutorProgramaticArea(final UserContext userContext,
	        final TutorProgrammaticArea tutorProgramaticArea) throws BusinessException {
		return this.tutorProgramaticAreaDAO.create(userContext.getUuid(), tutorProgramaticArea);
	}

}