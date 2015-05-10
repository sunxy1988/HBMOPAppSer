package com.hbmop.app.webserviceImpl;

import java.util.List;

import com.hbmop.app.model.Building;
import com.hbmop.app.service.BuildingService;
import com.hbmop.app.webservice.BuildingWebService;

public class BuildingWebSerImpl implements BuildingWebService{
	private BuildingService buildingService; 
	

	public BuildingService getBuildingService() {
		return buildingService;
	}


	public void setBuildingService(BuildingService buildingService) {
		System.out.println("buildingService");
		this.buildingService = buildingService;
	}


	public List<Building> findAllBuilding() {
		List<Building> bb = buildingService.findAllBuilding();
		for (Building building : bb) {
			System.out.println(building.getBuilding_code());
		}
		return bb;
	}



	@Override
	public List<Building> findBuilding(String area, String coverType,
			String keyWord, int currentPage) {
		return buildingService.findBuilding( area, coverType, keyWord,  currentPage);
	}


	@Override
	public List<Building> findByPropertyCode(String propertyCode) {
		return buildingService.findByProperty_code(propertyCode);
	}


	@Override
	public List<Building> findByChildPropertyCode(String childPropertyCode) {
		return buildingService.findByChild_property_code(childPropertyCode);
	}


	@Override
	public Building findByBuildingCode(String buildingCode) {
		return buildingService.findBuildingInformation(buildingCode);
	}

}
