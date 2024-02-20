package onetomany.Phones;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import onetomany.Users.User;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Name cannot be blank")
    @Length(max = 100)
    private String name;

    @NotBlank(message = "Manufacturer cannot be blank")
    @Length(max = 100)
    private String manufacturer;

    @NotNull(message = "Camera quality cannot be null")
    @Min(value = 0, message = "Camera quality must be positive")
    private double cameraQuality;

    @NotNull(message = "Battery capacity cannot be null")
    @Min(value = 0, message = "Battery capacity must be positive")
    private double battery;

    @NotNull(message = "Price cannot be null")
    @Min(value = 0, message = "Price must be a positive number")
    private int price;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    // Custom constructor without id field
    public Phone(String name, String manufacturer, double cameraQuality, double battery, int price) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.cameraQuality = cameraQuality;
        this.battery = battery;
        this.price = price;
    }
}
