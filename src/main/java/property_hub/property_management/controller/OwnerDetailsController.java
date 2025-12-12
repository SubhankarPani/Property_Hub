package property_hub.property_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import property_hub.property_management.model.Property;
import property_hub.property_management.repository.PropertyRepository;

import java.util.Optional;

@Controller
public class OwnerDetailsController {

    @Autowired
    private PropertyRepository propertyRepo;

    // Handle showing the owner's details page
    @GetMapping("/owner-details/{id}")
    public String showOwnerDetails(@PathVariable("id") Integer propertyId, Model model) {
        // Fetch the property based on its ID
        Optional<Property> property = propertyRepo.findById(propertyId);

        if (property.isPresent()) {
            Property propertyDetails = property.get();
            model.addAttribute("property", propertyDetails);
            model.addAttribute("ownerName", propertyDetails.getOwnerName());
            model.addAttribute("ownerEmail", propertyDetails.getOwnerEmail());
            model.addAttribute("ownerPhone", propertyDetails.getOwnerPhone());
            // Add any other details you want to display for the owner
            return "ownerDetails"; // Display the owner details page
        } else {
            model.addAttribute("errorMessage", "Property not found.");
            return "error"; // Show an error page if property not found
        }
    }
}
