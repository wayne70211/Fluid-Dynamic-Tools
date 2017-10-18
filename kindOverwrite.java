package address.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "kind")
public class kindOverwrite {

	private List<kind> kind;

	@XmlElement(name = "kind")
	public List<kind> getkind() {
		return kind;
	}

	public void setkind(List<kind> kind) {
		this.kind = kind;
	}
}