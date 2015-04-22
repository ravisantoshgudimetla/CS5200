package edu.neu.cs5200.asst6.xslt;

import java.util.List;
import edu.neu.cs5200.asst6.orm.models.*;
import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)

public class SiteList {
	
	@XmlElement(name="site")
	private List<Site> sites;

	public SiteList() {
		super();
	}

	public SiteList(List<Site> sites) {
		super();
		this.sites = sites;
	}

	public List<Site> getSites() {
		return sites;
	}

	public void setSites(List<Site> sites) {
		this.sites = sites;
	}
	
}
