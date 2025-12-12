package property_hub.property_management.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import property_hub.property_management.model.User;
import property_hub.property_management.repository.UserRepository;


import java.util.Optional;

@Controller
public class IndexController {

    @Autowired
    private UserRepository userRepo;

    // Show the Sign-Up page
    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "signup"; // Return the signup.html view
    }

    // Handle User Registration
    @PostMapping("/register")
    public String userRegistration(@ModelAttribute User user, Model model) {
        // Check if email is already registered
        if (userRepo.findByEmail(user.getEmail()).isPresent()) {
            model.addAttribute("errorMessage", "Email is already registered!");
            return "signup"; // Return to sign-up page if email exists
        }

        // Save the user to the database (password in plain text)
        userRepo.save(user);

        // If user is a Seller, redirect to the property details form
        if ("Seller".equals(user.getRole())) {
            return "redirect:/property-form";  // Redirect to the property details form page
        }

        // If user is a Buyer, redirect to the property listing page
        return "redirect:/property-listing";  // Redirect to property listing page for buyers
    }

    // Show the Login page
    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // Return the login.html view
    }

    // Handle Login
    @PostMapping("/login")
    public String userLogin(@ModelAttribute User user, Model model, HttpSession session) {
        Optional<User> existingUser = userRepo.findByEmail(user.getEmail());

        // Check if user exists and the password matches
        if (existingUser.isPresent() && existingUser.get().getPassword().equals(user.getPassword())) {
            // Store the email in the session for later use
            session.setAttribute("email", existingUser.get().getEmail());

            model.addAttribute("message", "Welcome back, " + existingUser.get().getUsername() + "!");

            // Redirect based on user role
            if ("Seller".equals(existingUser.get().getRole())) {
                return "redirect:/property-form";  // Redirect to property details form for sellers
            } else {
                return "redirect:/property-listing";  // Redirect to property listing page for buyers
            }
        } else {
            model.addAttribute("errorMessage", "Invalid credentials, please try again.");
            return "login"; // Return to login page with error message
        }
    }

    // Handle Logout (optional, if you want to handle session invalidation)
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Invalidate the session
        return "redirect:/login"; // Redirect to login page after logging out
    }
}
