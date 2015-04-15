package edu.neu.cs5200.asst5.orm.dao;

import java.util.List;
import javax.persistence.*;
import edu.neu.cs5200.asst5.orm.models.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("/site")
public class SiteDAO {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("Asst-5");
	EntityManager em = factory.createEntityManager();

	
	@POST
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
	}
	
	//findsiteByID
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Site findSite(@PathParam("id")Integer id)
	{
		return em.find(Site.class,id);
	}
	
	
	@GET	
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	//findAllSites
	public List<Site> findAllSites()
	{
		Query query=em.createQuery("select site from Site site ");
		return (List<Site>)query.getResultList();
	}
	
	//updateSite
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
	}
	public static void main(String[] args){
		
		SiteDAO dao = new SiteDAO();
		//Site site= new Site(null,"Site4",45.00,65.00);
	   //site=dao.createSite(site);
	  //  System.out.println(site.getId());
		//Site site=dao.findSite(3);
		//System.out.println(site.getName());
	
		
		
		//dao.removeSite(4);
		
		//List<Site> sites = dao.findAllSites();
		//	for(Site site: sites)
		//	{
		//		System.out.println(site.getName());
	 	 //   }
	//		List<Site> updatedsites = dao.updateSite(3, site);;
	//		for(Site updatedsite: updatedsites)
	//		{
	//			System.out.println(updatedsite.getName());
	// 	    }
		
		List<Site> updatedsites = dao.removeSite(3);
			for(Site updatedsite: updatedsites)
			{
				System.out.println(updatedsite.getName());
	 	    }
			
	}
	


	
	
}
