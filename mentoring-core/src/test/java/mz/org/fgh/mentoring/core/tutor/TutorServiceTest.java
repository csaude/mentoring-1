/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.tutor;

import javax.inject.Inject;

import org.junit.Test;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.co.mozview.frameworks.core.fixtureFactory.util.TestUtil;
import mz.org.fgh.mentoring.core.career.service.CareerService;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.TutorTemplate;
import mz.org.fgh.mentoring.core.tutor.dao.TutorDAO;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;
import mz.org.fgh.mentoring.core.tutor.service.TutorService;

/**
 * @author Stélio Moiane
 *
 *
 */
public class TutorServiceTest extends AbstractSpringTest {

	@Inject
	private TutorService tutorService;

	@Inject
	private TutorDAO tutorDAO;

	private Tutor tutor;

	@Inject
	private CareerService carrerService;

	@Override
	public void setUp() throws BusinessException {

		this.tutor = EntityFactory.gimme(Tutor.class, TutorTemplate.VALID);
		
		this.carrerService.createCareer(this.getUserContext(), this.tutor.getCareer());

	}

	@Test
	public void shouldCreateTutor() throws BusinessException {

		this.tutorService.createTutor(this.getUserContext(), this.tutor);

		TestUtil.assertCreation(this.tutor);
	}

	@Test
	public void shouldUpdateTutor() throws BusinessException {

		this.tutorService.createTutor(this.getUserContext(), this.tutor);

		final Tutor tutorUpdate = this.tutorDAO.findById(this.tutor.getId());

		this.tutorService.updateTutor(this.getUserContext(), tutorUpdate);

		TestUtil.assertUpdate(tutorUpdate);

	}

}
