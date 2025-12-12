package property_hub.property_management.service;

import property_hub.property_management.model.Property;
import property_hub.property_management.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepo;

    // Save or update property
    public Property saveProperty(Property property) {
        return propertyRepo.save(property);
    }

    // Update property by creating a new entry and deleting the old one
    public void updateAndCreateNewProperty(Property updatedProperty, Integer oldPropertyId) {
        // Create a new property with updated details
        propertyRepo.save(updatedProperty);

        // Delete the old property using the provided ID
        propertyRepo.deleteById(oldPropertyId);
    }

    // Get properties by owner's email
    public List<Property> getPropertiesByEmail(String email) {
        return propertyRepo.findByOwnerEmail(email);
    }

    // Get a property by ID
    public Property getPropertyById(Integer id) {
        return propertyRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid property ID"));
    }

    // Delete property by ID
    public void deleteProperty(Integer id) {
        Property property = getPropertyById(id);
        propertyRepo.delete(property);
    }
}
