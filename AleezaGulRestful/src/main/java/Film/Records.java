package Film;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Records")


public class Records {
	@XmlElement (name="film")
	
	private List<Film> Records; 
	
	public Records() {}
	public Records (List<Film> Records ) {
		this.Records=Records;
	}
	public List<Film> getRecords(){
		return Records;
	}
	
	public void setRecords(List<Film>Records){
		this.Records=Records;
		
	}
}
