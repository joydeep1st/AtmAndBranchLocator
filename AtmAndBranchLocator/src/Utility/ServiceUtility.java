package Utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.AuthState;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.NTCredentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.ProxyAuthenticationStrategy;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.jsonschema2pojo.DefaultGenerationConfig;
import org.jsonschema2pojo.GenerationConfig;
import org.jsonschema2pojo.Jackson2Annotator;
import org.jsonschema2pojo.SchemaGenerator;
import org.jsonschema2pojo.SchemaMapper;
import org.jsonschema2pojo.SchemaStore;
import org.jsonschema2pojo.SourceType;
import org.jsonschema2pojo.rules.RuleFactory;

import com.sun.codemodel.JCodeModel;

//import ofi.webui.core.LoadProperties;


public class ServiceUtility {

	private String requesturl = null;
	private HttpUriRequest httpUrlRequest = null;
	private HttpResponse httpResponse = null;
	public String tempfilepath = null;
	private URL jsonurl = null;
	//private File outputPojoDirectory;

	public ServiceUtility(String requesturl) throws MalformedURLException{
		this.requesturl = requesturl;
		this.jsonurl = new URL(requesturl);
	}

	
	private CloseableHttpClient client() {
		NTCredentials ntCreds = new NTCredentials("256151", "cts*apr2016","PC307666", "cts" );

		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials( new AuthScope("proxy.cognizant.com", 6050), ntCreds );
		HttpClientBuilder clientBuilder = HttpClientBuilder.create();

		clientBuilder.useSystemProperties();
		clientBuilder.setProxy(new HttpHost("proxy.cognizant.com", 6050));
		clientBuilder.setDefaultCredentialsProvider(credsProvider);
		clientBuilder.setProxyAuthenticationStrategy(new ProxyAuthenticationStrategy());

		CloseableHttpClient client = clientBuilder.build();
		return client;
	}
	
	private HttpResponse buildHttpresponse(){
		try {
			
			/*HttpHost proxy = new HttpHost("proxy.cognizant.com",6050);
			Credentials credentials = new UsernamePasswordCredentials("CTS\527369","Tanudbhaba!2018");
			AuthScope authScope = new AuthScope("proxy.cognizant.com", 6050);
			CredentialsProvider credsProvider = new BasicCredentialsProvider();
			credsProvider.setCredentials(authScope, credentials);
			HttpClient client = HttpClientBuilder.create().setProxy(proxy).setDefaultCredentialsProvider(credsProvider).build();*/
			httpUrlRequest = new HttpGet(requesturl);
			//httpResponse = HttpClientBuilder.create().build().execute(httpUrlRequest);
			httpResponse = this.client().execute(httpUrlRequest);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return httpResponse;
	}

	private HttpResponse buildHttpresponse(String headertype, String headervalue){
		try {
			httpUrlRequest = new HttpGet(requesturl);
			httpUrlRequest.addHeader(headertype, headervalue);
			httpResponse = HttpClientBuilder.create().build().execute(httpUrlRequest);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return httpResponse;
	}


	public void convertPojoFromJson(File opDir, String pkgName, String className){
		try {
			this.convert2JSON(opDir, pkgName, className);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void convertPojoFromJson(boolean header, File opDir, String pkgName, String className){
		try {
			this.convert2JSON(tempfilepath, opDir, pkgName, className);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Create Object and pass the url..
	 * send the response or execute the request
	 * Verify the response code is equal to 200
	 * 
	 * @return
	 */
	public boolean testResponseStatusCode(){
		boolean isServiceHealthGood = false;
		isServiceHealthGood = this.buildHttpresponse().getStatusLine().getStatusCode() == HttpStatus.SC_OK;
		return isServiceHealthGood;
	}

	/**
	 * Create Object and pass the url..
	 * send the response or execute the request
	 * Verify the response code is equal to 200
	 * 
	 * @return
	 * @param
	 * headertype : Type of header need to add in Request
	 * headervalue : The value of Header need to send in request
	 */
	public boolean testResponseStatusCode(String headertype, String headervalue){
		boolean isServiceHealthGood = false;
		isServiceHealthGood = this.buildHttpresponse(headertype, headervalue).getStatusLine().getStatusCode() == HttpStatus.SC_OK;
		return isServiceHealthGood;
	}

	/**
	 * Convert the response to a String format
	 * Then it will convert the JSON file in to POJO class
	 * @return
	 */
	public  String getJSONasString(boolean isRequiredPojoClass){
		String getJSONasString = null;
		try{
			if(this.testResponseStatusCode()){
				getJSONasString = EntityUtils.toString(httpResponse.getEntity());
				//this.convertPojoFromJson();
			}
		}catch(ClientProtocolException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}catch(JSONException e){
			e.printStackTrace();
		}
		return getJSONasString;
	}

	/**
	 * Convert the response to a String format
	 * Then it will convert the JSON file in to POJO class
	 * @return
	 * @param
	 * headertype : Type of header need to add in Request
	 * headervalue : The value of Header need to send in request
	 */
	public  String getJSONasString(boolean isRequiredPojoClass, String headertype, String headervalue){
		String getJSONasString = null;
		try{
			if(this.testResponseStatusCode(headertype, headervalue)){
				getJSONasString = EntityUtils.toString(httpResponse.getEntity());
				try{
					File jsonfile = File.createTempFile("sampleJson", "json");
					jsonfile.deleteOnExit();
					BufferedWriter out = new BufferedWriter(new FileWriter(jsonfile));
					out.write(getJSONasString);
					tempfilepath = jsonfile.getAbsolutePath();
					System.out.println(tempfilepath);
					out.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}catch(ClientProtocolException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}catch(JSONException e){
			e.printStackTrace();
		}
		return getJSONasString;
	}



	/**
	 * Convert the response to a String format
	 * Then it will convert the JSON file in to POJO class
	 * @return
	 * @param
	 * headertype : Type of header need to add in Request
	 * headervalue : The value of Header need to send in request
	 */
	public  String getJSONasString(){
		String getJSONasString = null;
		try{
			if(this.testResponseStatusCode()){
				getJSONasString = EntityUtils.toString(httpResponse.getEntity());
				try{
					File jsonfile = File.createTempFile("sampleJson", "json");
					jsonfile.deleteOnExit();
					BufferedWriter out = new BufferedWriter(new FileWriter(jsonfile));
					out.write(getJSONasString);
					tempfilepath = jsonfile.getAbsolutePath();
					System.out.println(tempfilepath);
					out.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}catch(ClientProtocolException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}catch(JSONException e){
			e.printStackTrace();
		}
		return getJSONasString;
	}




	/**
	 * 
	 * Then it will convert the JSON file in to POJO class
	 * @return
	 *  @param
	 * headertype : Type of header need to add in Request
	 * headervalue : The value of Header need to send in request
	 */
	public  String getJSONasString(String headertype, String headervalue){
		String getJSONasString = null;
		try{
			if(this.testResponseStatusCode(headertype, headervalue)){
				getJSONasString = EntityUtils.toString(httpResponse.getEntity());
			}
		}catch(ClientProtocolException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}catch(JSONException e){
			e.printStackTrace();
		}
		return getJSONasString;
	}

	/**
	 * 
	 * Then it will convert the JSON file in to POJO class
	 * @return
	 */
	public  String getJSONasStringAnother(){
		String getJSONasString = null;
		try{
			if(this.testResponseStatusCode()){
				getJSONasString = EntityUtils.toString(httpResponse.getEntity());
			}
		}catch(ClientProtocolException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}catch(JSONException e){
			e.printStackTrace();
		}
		return getJSONasString;
	}

	/*private File getOutputPojoDirectory(){
		File file;
		LoadProperties loadProperties = new LoadProperties();
		file = new File(loadProperties.getProperties("SERVICEPOJOPKG"));
		return file;
	}

	private String getClassName(){
		LoadProperties loadProperties = new LoadProperties();
		return loadProperties.getProperties("MASTERSERVICECLASS");
	}

	private String getPackageName(){
		LoadProperties loadProperties = new LoadProperties();
		return loadProperties.getProperties("PACKAGENAME");
	}*/

	/**
	 * Create Java POJO Class as per the JSON 
	 * 
	 * @throws IOException
	 */
	protected void convert2JSON(File opDir, String pkgName, String className) throws IOException{  
		JCodeModel codeModel = new JCodeModel();  
		URL source = jsonurl; 
		GenerationConfig config = new DefaultGenerationConfig() {  
			@Override  
			public boolean isGenerateBuilders() { // set config option by overriding method  
				return true;  
			}  
			public SourceType getSourceType(){  
				return SourceType.JSON;  
			}  
		};  
		try{
			SchemaMapper mapper = new SchemaMapper(new RuleFactory(config, new Jackson2Annotator(config), new SchemaStore()), new SchemaGenerator());  
			mapper.generate(codeModel, className, pkgName, source);  
			codeModel.build(opDir);
		}catch(Exception e){
			System.out.println(e);
		}
	}

	/**
	 * Create Java POJO Class as per the JSON 
	 * 
	 * @throws IOException
	 */
	protected void convert2JSON(String path, File opDir, String pkgName, String className) throws IOException{  
		JCodeModel codeModel = new JCodeModel();  
		URL source = new URL("file:///"+path); 
		GenerationConfig config = new DefaultGenerationConfig() {  
			@Override  
			public boolean isGenerateBuilders() { // set config option by overriding method  
				return true;  
			}  
			public SourceType getSourceType(){  
				return SourceType.JSON;  
			}  
		};  
		try{
			SchemaMapper mapper = new SchemaMapper(new RuleFactory(config, new Jackson2Annotator(config), new SchemaStore()), new SchemaGenerator());  
			mapper.generate(codeModel, className, pkgName, source);  
			codeModel.build(opDir);  
		}catch(Exception e){
			System.out.println(e);
		}
	}

}
