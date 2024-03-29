/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.tutored;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import org.junit.Test;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.co.mozview.frameworks.core.fixtureFactory.util.TestUtil;
import mz.org.fgh.mentoring.core.career.service.CareerService;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.TutorTemplate;
import mz.org.fgh.mentoring.core.fixturefactory.TutoredTemplate;
import mz.org.fgh.mentoring.core.tutored.dao.TutoredDAO;
import mz.org.fgh.mentoring.core.tutored.model.Tutored;
import mz.org.fgh.mentoring.core.tutored.service.TutoredService;

/**
 * @author Stélio Moiane
 *
 *
 */
public class TutoredServiceTest extends AbstractSpringTest {

	@Inject
	private TutoredService tutoredService;

	@Inject
	private TutoredDAO tutoredDAO;

	private Tutored tutored;

	@Inject
	private CareerService carrerService;

	@Override
	public void setUp() throws BusinessException {
		this.tutored = EntityFactory.gimme(Tutored.class, TutoredTemplate.VALID);
		this.carrerService.createCareer(this.getUserContext(), this.tutored.getCareer());
	}

	@Test
	public void shouldCreateTutored() throws BusinessException {

		this.tutored.setEmail(null);

		this.tutoredService.createTutored(this.getUserContext(), this.tutored);

		TestUtil.assertCreation(this.tutored);
	}

	@Test
	public void shouldUpdateTutored() throws BusinessException {

		this.tutoredService.createTutored(this.getUserContext(), this.tutored);

		final Tutored updateTutored = this.tutoredDAO.findById(this.tutored.getId());

		this.tutoredService.updateTutored(this.getUserContext(), updateTutored);

		TestUtil.assertUpdate(updateTutored);

	}

	@Test
	public void shouldSyncronizeTutoreds() throws BusinessException {

		final int elements = 30;
		final List<Tutored> tutoreds = EntityFactory.gimme(Tutored.class, elements, TutorTemplate.VALID);

		for (final Tutored tutored : tutoreds) {
			final String uuid = UUID.randomUUID().toString().replace("-", "");
			tutored.setUuid(uuid);
			this.carrerService.createCareer(this.getUserContext(), tutored.getCareer());
		}

		final List<Tutored> synckedTutoreds = this.tutoredService.syncronizeTutoreds(this.getUserContext(), tutoreds);

		assertFalse(synckedTutoreds.isEmpty());
		assertEquals(elements, synckedTutoreds.size());

		for (final Tutored tutored : synckedTutoreds) {
			assertEquals(0, tutored.getVersion());
		}
	}
}
