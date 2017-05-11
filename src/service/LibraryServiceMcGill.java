package service;

import java.io.File;
import java.io.IOException;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.sun.jersey.spi.resource.Singleton;

import server.LibraryServer;
import server.UDPSocket;

/**
 * The Class LibraryServiceMcGill.
 * 
 * @author Haiyang Sun
 */
@Path("/library-service-mcgill")
@Singleton

public class LibraryServiceMcGill extends LibraryServer {

	/**
	 * Instantiates a new library service mc gill.
	 */
	public LibraryServiceMcGill() {
		
		super();
		this.setNameOfServer(ConstantValue.MCGILL);
		this.setPortOfUDP(ConstantValue.MCGILL_UDP_PORT);
		this.setIpAddress(ConstantValue.MCGILL_IP_ADDRESS);
		
		this.setSocket(new UDPSocket(this));
		this.getSocket().start();	
		
		initializeTestingData();
		
		System.out.println(this.getNameOfServer() + " REST service is up!");
		
		try{
			File f = new File(this.getNameOfServer() +"_log.txt");
			if(!f.exists())
				f.createNewFile();
		}catch(IOException e) {
			e.getMessage();
		}
		
	}
	
	/**
	 * @see server.LibraryServer#createAccount(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Path("/{firstName}-{lastName}-{emailAddress}-{phoneNumber}-{username}-{password}-{eduInstitution}")
	@GET
	@Produces("text/plain")
	public String createAccount(@PathParam("firstName")String firstName, @PathParam("lastName")String lastName,
			@PathParam("emailAddress")String emailAddress, @PathParam("phoneNumber")String phoneNumber, @PathParam("username")String username,
			@PathParam("password")String password, @PathParam("eduInstitution")String eduInstitution) {
		
		return super.createAccount(firstName, lastName, emailAddress, phoneNumber, username, password, eduInstitution);
	}
	
	/**
	 * @see server.LibraryServer#reserveBook(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
//	@Override
//	@Path("/reserve-book/{username}-{password}-{bookName}-{authorName}")
//	@GET
//	@Produces("text/plain")
//	public String reserveBook(@PathParam("username")String username, @PathParam("password")String password,
//			@PathParam("bookName")String bookName, @PathParam("authorName")String authorName)  {
//		
//		return super.reserveBook(username, password, bookName, authorName);
//		
//	}
	
	/**
	 * @see server.LibraryServer#reserveInterLibrary(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Path("/{username}-{password}-{bookName}-{authorName}")
	@GET
	@Produces("text/plain")
	public String reserveInterLibrary(@PathParam("username")String username, @PathParam("password")String password,
			@PathParam("bookName")String bookName, @PathParam("authorName")String authorName) {
		

		return super.reserveInterLibrary(username, password, bookName, authorName);
	}
	
	/**
	 * @see server.LibraryServer#getNonRetuners(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Path("/{adminUsername}-{adminPassword}-{eduInstitution}-{numDays}")
	@GET
	@Produces("text/plain")
	
	public String getNonRetuners(@PathParam("adminUsername")String adminUsername, @PathParam("adminPassword")String adminPassword, @PathParam("eduInstitution")String eduInstitution, @PathParam("numDays")String numDays) {
		
		return super.getNonRetuners(adminUsername, adminPassword, eduInstitution, numDays);
	}
	
	/**
	 * @see server.LibraryServer#setDuration(java.lang.String, java.lang.String, int)
	 */
	@Override
	@Path("/{username}-{bookName}-{numOfDays}")
	@GET
	@Produces("text/plain")
	public String setDuration(@PathParam("username")String username, @PathParam("bookName")String bookName, @PathParam("numOfDays")int numOfDays) {
		
		return super.setDuration(username, bookName, numOfDays);
	}


}
