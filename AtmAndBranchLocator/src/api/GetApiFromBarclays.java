package api;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Utility.ConfigProvider;
import Utility.ServiceUtility;
import api.barclays.ATM;
import api.barclays.BarclaysApi;
import api.barclays.Brand;
import api.barclays.Datum;

public class GetApiFromBarclays {
	
	private static String json = "";
	private ObjectMapper mapper = new ObjectMapper();
	
	public void getPojoForBarclays() {
		try {
			ServiceUtility serviceUtility = new ServiceUtility(ConfigProvider.api_Barclays_atm);
			json = serviceUtility.getJSONasString();
			if(serviceUtility.testResponseStatusCode()) {
				try {
					BarclaysApi barclaysApi = mapper.readValue(json, BarclaysApi.class);
					//get All the Datas
					List<Datum> datum = barclaysApi.getData();
					for(int i = 0; i< datum.size(); i++) {
						//get all the Brands
						List<Brand> brand = datum.get(i).getBrand();
						/*System.out.println("Brand Name: " + bra);*/
						for(int j=0; j< brand.size(); j++) {
							//Get the Brank name
							System.out.println("Brand Name: " + brand.get(j).getBrandName());
							//get All the ATMS
							List<ATM> atm = brand.get(j).getATM();
							for(int k=0; k<atm.size(); k++) {
								System.out.println("All ATM Identifications: " + atm.get(k).getIdentification());
								//Get all ATM Services
								List<String> services = atm.get(k).getATMServices();
								for(String service : services) {
									System.out.println("All ATM Service: " + service);
								}
								List<String> supportedLan = atm.get(k).getSupportedLanguages();
								for(String language : supportedLan) {
									System.out.println("Language: " + language);
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
	}

}
