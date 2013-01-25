package substrate.tracker.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement(name = "collection")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({ProjectDTO.class})
public class CollectionDTO<T> {
    @XmlElement(name = "item")
    @XmlElementWrapper(name = "items")
    private List<T> items;

    public void addItem(final T item) {
        getItems().add(item);
    }

    public List<T> getItems() {
        if (items == null) {
            items = new ArrayList<>();
        }

        return items;
    }
}
