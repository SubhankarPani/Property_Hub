package property_hub.property_management.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import property_hub.property_management.model.Property;
import property_hub.property_management.service.PropertyService;

import java.util.List;

@Controller
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    // Helper method to retrieve logged-in user's email from the session
    private String getLoggedInUserEmail(HttpSession session) {
        return (String) session.getAttribute("email"); // Retrieve the email from session
    }

    // Show the home page
    @GetMapping("/")
    public String home() {
        return "index";  // Assuming 'index.html' is your home/landing page template
    }

    // Show the property form for adding new properties
    @GetMapping("/property-form")
    public String showPropertyForm(Model model, HttpSession session) {
        String sellerEmail = getLoggedInUserEmail(session); // Get logged-in user's email from session
        if (sellerEmail == null) {
            model.addAttribute("errorMessage", "You must be logged in to add a property.");
            return "login"; // Redirect to login page if not logged in
        }

        model.addAttribute("property", new Property());
        return "propertyForm";
    }

    // Submit the property details to save it to the database
    @PostMapping("/submit-property")
    public String submitProperty(@ModelAttribute Property property, Model model, HttpSession session) {
        String sellerEmail = getLoggedInUserEmail(session); // Get logged-in user's email from session
        if (sellerEmail == null) {
            model.addAttribute("errorMessage", "You must be logged in to submit a property.");
            return "login"; // Redirect to login page if not logged in
        }

        // Set the email of the logged-in user as the owner email
        property.setOwnerEmail(sellerEmail);

        // Save the property to the database
        propertyService.saveProperty(property);

        // Redirect to a thank you page with a success message
        model.addAttribute("message", "Property details submitted successfully!");
        return "thankyou";
    }

    // Show the list of properties for the seller
    @GetMapping("/seller-property-list")
    public String showSellerPropertyList(Model model, HttpSession session) {
        String sellerEmail = getLoggedInUserEmail(session); // Get logged-in user's email from session

        if (sellerEmail == null) {
            model.addAttribute("message", "You must be logged in to view your properties.");
            return "login"; // Redirect to login page if not logged in
        }

        // Get properties for the logged-in seller using their email
        List<Property> properties = propertyService.getPropertiesByEmail(sellerEmail);

        model.addAttribute("properties", properties);
        return "sellerPropertyList";
    }

    // Show the form for modifying a specific property
    @GetMapping("/modify-property/{id}")
    public String showModifyForm(@PathVariable("id") Integer id, Model model, HttpSession session) {
        String sellerEmail = getLoggedInUserEmail(session); // Get logged-in user's email from session

        if (sellerEmail == null) {
            model.addAttribute("message", "You must be logged in to modify a property.");
            return "login"; // Redirect to login page if not logged in
        }

        // Fetch the property by its ID and check if the logged-in user is the owner
        Property property = propertyService.getPropertyById(id);

        if (property == null || !property.getOwnerEmail().equals(sellerEmail)) {
            model.addAttribute("errorMessage", "You are not authorized to modify this property.");
            return "error"; // Redirect to an error page if the user is not the owner
        }

        model.addAttribute("property", property);
        return "propertyForm";  // Same form for adding and modifying properties
    }

    // Update an existing property
    @PostMapping("/update-property")
    public String updateProperty(@ModelAttribute Property updatedProperty, @RequestParam("oldPropertyId") Integer oldPropertyId, Model model, HttpSession session) {
        String sellerEmail = getLoggedInUserEmail(session); // Get logged-in user's email from session
        if (sellerEmail == null) {
            model.addAttribute("errorMessage", "You must be logged in to update a property.");
            return "login"; // Redirect to login page if not logged in
        }

        // Set the email of the logged-in user as the owner email
        updatedProperty.setOwnerEmail(sellerEmail);

        // Call the service method to update the property and remove the old one
        propertyService.updateAndCreateNewProperty(updatedProperty, oldPropertyId);

        model.addAttribute("message", "Property details updated successfully!");
        return "thankyou"; // Redirect to a thank you page after update
    }

    // Delete a property
    @PostMapping("/delete-property/{id}")
    public String deleteProperty(@PathVariable("id") Integer id, Model model, HttpSession session) {
        String sellerEmail = getLoggedInUserEmail(session); // Get logged-in user's email from session

        if (sellerEmail == null) {
            model.addAttribute("message", "You must be logged in to delete a property.");
            return "login"; // Redirect to login page if not logged in
        }

        // Fetch the property by its ID and check if the logged-in user is the owner
        Property property = propertyService.getPropertyById(id);

        if (property == null || !property.getOwnerEmail().equals(sellerEmail)) {
            model.addAttribute("errorMessage", "You are not authorized to delete this property.");
            return "error"; // Redirect to an error page if the user is not the owner
        }

        // Delete the property from the database
        propertyService.deleteProperty(id);

        // Redirect to the list of properties with a success message
        model.addAttribute("message", "Property deleted successfully!");
        return "redirect:/seller-property-list"; // Redirect to the property list page after deletion
    }
}
