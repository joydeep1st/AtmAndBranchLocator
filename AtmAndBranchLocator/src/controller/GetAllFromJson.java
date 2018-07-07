package controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import Utility.ConfigProvider;
import Utility.ServiceUtility;
import api.barclays.ATM;
import api.barclays.BarclaysApi;
import api.barclays.Brand;
import api.barclays.Datum;
import api.barclays.Location;
import api.barclays.PostalAddress;
import bean.HistoryBean;
import bean.LoginBean;
import dao.HistoryDao;

public class GetAllFromJson extends HttpServlet {
	
	private static String json = "";
	private ObjectMapper mapper = new ObjectMapper();
	private List<String> locations = new ArrayList<String>();
	private List<String> postalCodes = new ArrayList<String>();
	private String locationAsString = "";
	private String postalCodesAsString = "";
	
	private static String radioType = "";
	private static String prevSelectedLocality = "";
	private static String zips = "";
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String locs = "";
		if(!(request.getParameter("atm")==null)) {
			radioType = request.getParameter("atm");			
		}
		
		switch(radioType.toLowerCase()) {
		case "atm":
			/*String allLocations = getAllLocations();*/			
			locs = locs + "Bristol" +",";
			locs = locs + "Cristol" +",";
			locs = locs + "Dristol" +",";
			locs = locs + "Tristol" +",";
			locs = locs + "Gristol" +",";
			locs = locs + "Wristol" +",";
			locs = locs + "Qristol" +",";
			locs = locs + "Zristol" +",";
			locs = locs + "fristol";
			request.setAttribute("locations", locs);
			break;
		case "branch":
			locs = locs + "BranchBristol" +",";
			locs = locs + "BranchCristol" +",";
			locs = locs + "BranchDristol" +",";
			locs = locs + "BranchTristol" +",";
			locs = locs + "BranchGristol" +",";
			locs = locs + "BranchWristol" +",";
			locs = locs + "BranchQristol" +",";
			locs = locs + "BranchZristol" +",";
			locs = locs + "Branchfristol";
			request.setAttribute("locations", locs);
			break;
		}
		
		String selectedLocality = request.getParameter("locality");
		if(!(selectedLocality==null)) {		
			/*String postalCodes = getPostalCodes(selectedLocality);*/
			prevSelectedLocality = selectedLocality;
			zips = zips + "12AB34" +",";
			zips = zips + "13BC45" +",";
			zips = zips + "14YU65" +",";
			zips = zips + "15OP45" +",";
			zips = zips + "16AV23" +",";
			zips = zips + "17LK45" +",";
			zips = zips + "18JK67" +",";
			zips = zips + "19FG56" +",";
			zips = zips + "20ZS89";
			request.setAttribute("zips", zips);
			request.setAttribute("prevSelectedLocality", selectedLocality);
		}
		
		request.setAttribute("radiobutton", radioType);
		String zipcode = request.getParameter("zipcode");
		if(!(zipcode==null)) {
			/*String address = getDetailsForLocation(selectedLocality,zipcode);*/
			String address = "Bristol" + "::" + "TownName" + "::" + "St. Thomas Road" +":;:" + "X: -10233234 Y: -345345";
			request.setAttribute("zipcode", zipcode);
			request.setAttribute("address", address);
			request.setAttribute("prevSelectedLocality", prevSelectedLocality);
			request.setAttribute("zips", zips);
			LoginBean lb = new LoginBean();
			HttpSession session;
			session = request.getSession();
			lb.setUser_name(session.getAttribute("USERNAME").toString());
			HistoryBean hb = new HistoryBean();
			hb.setBank_name("Barclays");
			hb.setType(radioType);
			hb.setLocality(prevSelectedLocality);
			hb.setZip(zipcode);
			hb.setAddress(address.split(":;:")[0]);
			hb.setCoordinates(address.split(":;:")[1]);
			HistoryDao.updateHistory(lb, hb);
		}
		
		getServletContext().getRequestDispatcher("/JSP/welcome.jsp").forward(request, response);
	}
	
	public String getAllLocations() {		
		try {
			locations = new ArrayList<String>();
			ServiceUtility serviceUtility = new ServiceUtility(ConfigProvider.api_Barclays_atm);
			json = serviceUtility.getJSONasString();
			if(serviceUtility.testResponseStatusCode()) {
				try {
					BarclaysApi barclaysApi = mapper.readValue(json, BarclaysApi.class);
					List<Datum> datum = barclaysApi.getData();
					for(int i = 0; i< datum.size(); i++) {
						List<Brand> brand = datum.get(i).getBrand();
						for(int j=0; j< brand.size(); j++) {
							List<ATM> atm = brand.get(j).getATM();
							for(int k=0; k<atm.size(); k++) {
								Location location = atm.get(k).getLocation();								
								PostalAddress postalAddress = location.getPostalAddress();
								if(checkIfUniqueTown(postalAddress.getTownName())) {
									locations.add(postalAddress.getTownName());
								}								
							}
						}
					}
				} catch (IOException e) {
					System.out.println(e);
				}
			}
		} catch (MalformedURLException e) {
			System.out.println(e);
		}
		for(int i=0;i<locations.size();i++) {
			if(i==locations.size()-1) {
				locationAsString = locationAsString + locations.get(i) + ",";
			}
			else {
				locationAsString = locationAsString + locations.get(i);
			}
		}
		locationAsString = locationAsString + "]";
		return locationAsString;
	}
	
	public boolean checkIfUniqueTown(String townName) {
		boolean flag = true;
		for(int i=0;i<locations.size();i++) {
			if(townName.equals(locations.get(i).toString())) {
				flag=false;
				break;
			}
		}
		return flag;		
	}
	
	public String getPostalCodes(String loc) {		
		try {
			ServiceUtility serviceUtility = new ServiceUtility(ConfigProvider.api_Barclays_atm);
			json = serviceUtility.getJSONasString();
			if(serviceUtility.testResponseStatusCode()) {
				try {
					BarclaysApi barclaysApi = mapper.readValue(json, BarclaysApi.class);
					List<Datum> datum = barclaysApi.getData();
					for(int i = 0; i< datum.size(); i++) {
						List<Brand> brand = datum.get(i).getBrand();
						for(int j=0; j< brand.size(); j++) {
							List<ATM> atm = brand.get(j).getATM();
							for(int k=0; k<atm.size(); k++) {
								Location location = atm.get(k).getLocation();
								if(!(loc==null)) {
									if(location.getPostalAddress().getTownName().equals(loc)) {
										postalCodes.add(location.getPostalAddress().getPostCode());
									}
								}
								else {
									postalCodes.add(location.getPostalAddress().getPostCode());
								}								
							}
						}
					}
				} catch (IOException e) {
					System.out.println(e);
				}
			}
		} catch (MalformedURLException e) {
			System.out.println(e);
		}
		for(int i=0;i<postalCodes.size();i++) {
			if(i==postalCodes.size()-1) {
				postalCodesAsString = postalCodesAsString + postalCodes.get(i) + ",";
			}
			else {
				postalCodesAsString = postalCodesAsString + postalCodes.get(i);
			}
		}
		postalCodesAsString = postalCodesAsString + "]";
		return postalCodesAsString;
	}
	
	public String getDetailsForLocation(String loc, String zipCode) {		
		String retValue = "";
		try {
			ServiceUtility serviceUtility = new ServiceUtility(ConfigProvider.api_Barclays_atm);
			json = serviceUtility.getJSONasString();
			if(serviceUtility.testResponseStatusCode()) {
				try {
					BarclaysApi barclaysApi = mapper.readValue(json, BarclaysApi.class);
					List<Datum> datum = barclaysApi.getData();
					for(int i = 0; i< datum.size(); i++) {
						List<Brand> brand = datum.get(i).getBrand();
						for(int j=0; j< brand.size(); j++) {
							List<ATM> atm = brand.get(j).getATM();
							for(int k=0; k<atm.size(); k++) {
								Location location = atm.get(k).getLocation();
								if(!(loc==null)) {
									if(location.getPostalAddress().getTownName().equals(loc)) {
										String postalCode = location.getPostalAddress().getPostCode();
										if(postalCode.equals(zipCode)) {
											String address = location.getPostalAddress().getStreetName() + "::" + location.getPostalAddress() + "::" + location.getPostalAddress().getAddressLine();
											String coordinates = location.getPostalAddress().getGeoLocation().toString();
											retValue = address + ":;:" + coordinates;
										}										
									}
								}								
							}
						}
					}
				} catch (IOException e) {
					System.out.println(e);
				}
			}
		} catch (MalformedURLException e) {
			System.out.println(e);
		}
		return retValue;
	}
	
}
