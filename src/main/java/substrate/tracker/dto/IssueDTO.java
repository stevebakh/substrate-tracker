package substrate.tracker.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "issue")
@XmlAccessorType(XmlAccessType.FIELD)
public class IssueDTO {
    private Long id;
}
