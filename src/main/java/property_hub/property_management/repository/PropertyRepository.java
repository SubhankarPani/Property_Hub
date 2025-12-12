package property_hub.property_management.repository;

import org.springframework.data.repository.CrudRepository;
import property_hub.property_management.model.Property;

import java.util.List;

public interface PropertyRepository extends CrudRepository<Property, Integer> {
    List<Property> findAll();  // To get all properties
    // Custom query to find properties by seller email
    List<Property> findByOwnerEmail(String ownerEmail);
    List<Property> findByLocationContainingIgnoreCase(String location);

}
