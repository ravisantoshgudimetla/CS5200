package edu.neu.cs5200.asst6.orm.dao;

import java.util.List;
import java.io.File;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;




import javax.persistence.*;

import edu.neu.cs5200.asst6.orm.models.*;
import edu.neu.cs5200.asst6.xslt.*;

//import javax.ws.rs.*;
//import javax.ws.rs.core.MediaType;


//@Path("/site")
public class SiteDAO {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("Asst-6");
	EntityManager em = factory.createEntityManager();

	
	/*//@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	//createSite
	public List<Site> createSite(Site site) {
		em.getTransaction().begin();
		em.persist(site);
		em.getTransaction().commit();
		List<Site> sites=findAllSites();
		sites.add(site);
		return sites;
	}*/
	
	//findsiteByID
	/*@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	*/
	public Site findSite(Integer id)
	{
		return em.find(Site.class,id);
	}
	
	
	/*@GET	
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
*/	//findAllSites
	public List<Site> findAllSites()
	{
		Query query=em.createQuery("select site from Site site ");
		return (List<Site>)query.getResultList();
	}
	
	/*//updateSite
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Site> updateSite(@PathParam("id")Integer id,Site site)
	{
		site=findSite(id);
		site.setName("hello");
		em.getTransaction().begin();
		em.merge(site);
		em.getTransaction().commit();
		return findAllSites();
	}
	
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	
	public List<Site> removeSite(@PathParam("id") int i) {
		Site site=findSite(i);
		em.getTransaction().begin();
		em.remove(site);
		em.getTransaction().commit();
		return findAllSites();
	}*/
	
	
	public void exportSiteToXmlFile(SiteList sites, String xmlFileName) {
		File xmlFile = new File(xmlFileName);
		try {
			JAXBContext jaxb = JAXBContext.newInstance(SiteList.class);
			Marshaller marshaller = jaxb.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(sites, xmlFile);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void convertXmlFileToOutputFile(
			String directorsXmlFileName,
			String outputFileName,
			String xsltFileName)
	{
		File inputXmlFile = new File(directorsXmlFileName);
		File outputXmlFile = new File(outputFileName);
		File xsltFile = new File(xsltFileName);
		
		StreamSource source = new StreamSource(inputXmlFile);
		StreamSource xslt    = new StreamSource(xsltFile);
		StreamResult output = new StreamResult(outputXmlFile);
		
		TransformerFactory factory = TransformerFactory.newInstance();
		try {
			Transformer transformer = factory.newTransformer(xslt);
			transformer.transform(source, output);
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args){
		
		SiteDAO dao = new SiteDAO();
	//	Site site= new Site(null,"Site4",45.00,65.00);
	  // List<Site> sites=dao.createSite(site);
	  //  System.out.println(site.getId());
		//Site site=dao.findSite(3);
		//System.out.println(site.getName());
	   
		
		
		//dao.removeSite(4);
		
		 List<Site> sites = dao.findAllSites();
			for(Site site: sites)
			{
				System.out.println(site.getName());
	 	    }
			SiteList sitelist=new SiteList();
			sitelist.setSites(sites);
			dao.exportSiteToXmlFile(sitelist,"xml/sites.xml");
			dao.convertXmlFileToOutputFile("xml/sites.xml", "xml/sites.html", "xml/sites2html.xslt");
			dao.convertXmlFileToOutputFile("xml/sites.xml", "xml/equipments.html", "xml/sites2equipment.xslt");
	//		List<Site> updatedsites = dao.updateSite(3, site);
	//		for(Site updatedsite: updatedsites)
	//		{
	//			System.out.println(updatedsite.getName());
	// 	    }
		
		//List<Site> updatedsites = dao.removeSite(3);
		//	for(Site updatedsite: updatedsites)
		//	{
			//	System.out.println(updatedsite.getName());
	 	    //}
	}
}
