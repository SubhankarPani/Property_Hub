package property_hub.property_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import property_hub.property_management.model.Property;
import property_hub.property_management.repository.PropertyRepository;

import java.util.List;

@Controller
public class PropertyListingController {

    @Autowired
    private PropertyRepository propertyRepo;

    @GetMapping("/property-listing")
    public String propertyListingPage(@RequestParam(value = "location", required = false) String location, Model model) {
        List<Property> properties;

        if (location != null && !location.isEmpty()) {
            properties = propertyRepo.findByLocationContainingIgnoreCase(location); // Search by location
        } else {
            properties = propertyRepo.findAll(); // Fetch all properties
        }

        model.addAttribute("properties", properties);
        model.addAttribute("searchedLocation", location); // Preserve the search query
        return "propertyListing";
    }

}
