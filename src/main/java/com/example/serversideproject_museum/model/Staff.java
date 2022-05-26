package com.example.serversideproject_museum.model;

import com.example.serversideproject_museum.repository.ExhibitRepository;
import com.example.serversideproject_museum.service.ExhibitService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/*


 UML Diagram: Staff 
 +------------------------------------------+
 |        Staff                             |
 +------------------------------------------+
 |   id                  :  Long            |
 |   firstName           :  String          |
 |   lastName            :  String          |
 |   dob                 :  LocalDate       |
 |   address             : String           |
 |   salary              : Integer          |
 |   exhibits            : Set<Exhibit>     |
 +------------------------------------------+
 |   Constructors                           |
 |   Getters & Setters                      |
 +------------------------------------------+
 |   addExhibit(Exhibit) :  void            |
 |   toString()          :  String          |
 +------------------------------------------+

 */


@Entity
@Table(name = "staff")
public class Staff {


    //   Staff properties

    //Primary key - Long - id - Generated for us by Spring, initial value of 51 as we created 50 Staff objects in data.sql
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Staff_generator")
    @SequenceGenerator(name = "Staff_generator", allocationSize = 1, initialValue = 51) //Increment by 1, start at 51
 
    @Column
    private Long id;

    //String - firstName - First name of the staff member
    @Column
    private String firstName;

    //String - lastName - Last name of the staff member
    @Column
    private String lastName;

    //LocalDate - dob - Date of birth of the staff member
    @Column
    private LocalDate dob;

    //String - address - Current address of the staff member
    @Column
    private String address;

    //Integer - salary - Salary of the given staff member
    @Column
    private Integer salary;

    /* Set<Exhibit> - exhibits - Set of exhibits that the staff member works in
    *                          - Ignored by JSON to avoid Infinite Recursion error
    *                          - Initialised as an empty HashSet
    *                          - Many-To-Many relationship with Exhibit, mapped by staff property of Exhibit
    *                          - Join table defined as:
    *                           - ASSIGNMENTS with two columns
    *                           - STAFF_ID refers to the id of a staff object
    *                           - EXHIBIT_ID refers to the id of an exhibit object
    *
    * Relationship - Each member of Staff can work in many exhibits
    *              - Each Exhibit can have many staff members working in it
    */
 
    @ManyToMany(mappedBy = "staff", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Exhibit> exhibits = new HashSet<>();


    //   Constructors

    // No arg constructor
    public Staff() {
    }

    // Standard constructor
    public Staff(String firsName, String lastName, LocalDate dob, String address, Integer salary) {
        this.firstName = firsName;
        this.lastName = lastName;
        this.dob = dob;
        this.address = address;
        this.salary = salary;

    }


    // Getters & Setters

    //For firstName property:
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    //For lastName property:
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    //For dob property:

    public LocalDate getDob() {
        return dob;
    }
    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    //For address property:

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    //For salary property:

    public Integer getSalary() {
        return salary;
    }
    public void setSalary(Integer salary) {
        this.salary = salary;

    }

    //For exhibits property:

    public Set<Exhibit> getExhibits() {
        return exhibits;
    }
    public void setExhibits(Set<Exhibit> exhibits) {
        this.exhibits = exhibits;
    }

    // getter for id
    public Long getId() {
        return id;
    }


    //   Other methods

    /* addExhibit(Exhibit exhibit)
    *       Adds an exhibit to the set of exhibits the staff member
    *       currently works in
    *
    * @Param Exhibit - Exhibit to be added to current set of exhibits
    *
    */
    public void addExhibit(Exhibit exhibit) {
        this.exhibits.add(exhibit);
    }

    /* toString()
    *       Simple override of toString method, autogenerated
    */
    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", firsName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                ", address='" + address + '\'' +
                ", salary=" + salary +
                ", exhibits=" + exhibits +
                '}';
    }
}
