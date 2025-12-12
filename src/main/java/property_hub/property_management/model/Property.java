package property_hub.property_management.model;

import jakarta.persistence.*;
@Entity
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "property_name")
    private String propertyName;

    @Column(name = "property_description")
    private String propertyDescription;

    @Column(name = "price")
    private Long price;

    @Column(name = "location")
    private String location;

    @Column(name = "owner_name")
    private String ownerName;  // Seller's name

    @Column(name = "owner_email")
    private String ownerEmail;  // Seller's email for contact

    @Column(name = "owner_phone")
    private String ownerPhone;  // Seller's phone number for contact

    @Column(name = "property_type")
    private String propertyType; // "SELL" or "RENT"

    @Column(name = "property_image")
    private String propertyImage; // Image URL or file path

    // Getter and Setter for propertyImage
    public String getPropertyImage() {
        return propertyImage;
    }

    public void setPropertyImage(String propertyImage) {
        this.propertyImage = propertyImage;
    }

    // Getter and Setter for propertyType
    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    // Getter and Setter for id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // Getter and Setter for propertyName
    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    // Getter and Setter for propertyDescription
    public String getPropertyDescription() {
        return propertyDescription;
    }

    public void setPropertyDescription(String propertyDescription) {
        this.propertyDescription = propertyDescription;
    }

    // Getter and Setter for price
    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    // Getter and Setter for location
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    // Getter and Setter for ownerName
    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    // Getter and Setter for ownerEmail
    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    // Getter and Setter for ownerPhone
    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }
}
