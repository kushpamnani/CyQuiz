package Entities_Controllers.Users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import Entities_Controllers.Student_Classroom_JoinTable.Classroom_registrations;

import java.util.Set;

import static jakarta.persistence.CascadeType.*;

/**
 * 
 * @author Dalton Clark
 * 
 */
public interface User {

    public String getName();

    public void setName(String name);

    public String getPassword();

    public void setPassword(String password);
    public boolean getIsActive();

    public void setIfActive(boolean ifActive);
}
