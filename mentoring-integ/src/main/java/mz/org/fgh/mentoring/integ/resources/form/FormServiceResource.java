/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.resources.form;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.JResponse;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.org.fgh.mentoring.core.form.model.Form;

/**
 * @author Stélio Moiane
 *
 */
public interface FormServiceResource {

	String NAME = "mz.org.fgh.mentoring.integ.resources.form.FormServiceResource";

	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public JResponse<Form> createForm(final FormBeanResource formBeanResource) throws BusinessException;

	@PUT
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public JResponse<Form> updateForm(final FormBeanResource formBeanResource) throws BusinessException;

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public JResponse<List<Form>> findBySelectedFilter(@QueryParam("code") final String code,
	        @QueryParam("name") final String name,
	        @QueryParam("programmaticAreaCode") final String programmaticAreaCode) throws BusinessException;

	@GET
	@Path("sample-forms")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	JResponse<List<Form>> findSampleIndicatorForms() throws BusinessException;
}
