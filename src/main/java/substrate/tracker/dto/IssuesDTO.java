package substrate.tracker.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "issues")
@XmlAccessorType(XmlAccessType.FIELD)
public class IssuesDTO {
    private List<IssueDTO> issues;
}
