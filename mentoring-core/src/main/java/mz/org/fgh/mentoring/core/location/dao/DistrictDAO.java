/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.location.dao;

import java.util.List;

import mz.co.mozview.frameworks.core.dao.GenericDAO;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.org.fgh.mentoring.core.location.model.District;
import mz.org.fgh.mentoring.core.location.model.Province;

/**
 * @author Stélio Moiane
 *
 */
public interface DistrictDAO extends GenericDAO<District, Long> {

	String NAME = "mz.org.fgh.mentoring.core.location.dao.DistrictDAO";

	public static class QUERY {
		public static final String findByProvince = "SELECT d FROM District d where d.province = :province and d.lifeCycleStatus = :lifeCycleStatus";
		public static final String findByProvinceAndName = "SELECT d FROM District d WHERE d.province = :province AND d.district = :district AND d.lifeCycleStatus = :lifeCycleStatus";
	}

	public static class QUERY_NAME {
		public static final String findByProvince = "District.findByProvince";
		public static final String findByProvinceAndName = "District.findByProvinceAndName";
	}

	List<District> findByProvince(final Province province, final LifeCycleStatus lifeCycleStatus);

	District findByProvinceAndName(final Province province, final String district,
	        final LifeCycleStatus lifeCycleStatus);
}
