package com.example.entities.TaskEntities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NamedEntityGraph(name = "userEntity-graph", attributeNodes = @NamedAttributeNode("phones"))
public class UserEntity {


    @Id
    @GeneratedValue
    private int id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<AddressEntity> addresses;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<PhoneEntity> phones;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<AddressEntity> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<AddressEntity> addresses) {
        this.addresses = addresses;
    }


    public Set<PhoneEntity> getPhones() {
        return phones;
    }

    public void setPhones(Set<PhoneEntity> phones) {
        this.phones = phones;
    }

    public void addPhone(String number, String type) {
        if (phones == null) {
            phones = new HashSet<>();
        }
        PhoneEntity p = new PhoneEntity();
        p.setNumber(number);
        p.setType(type);
        phones.add(p);
    }

    public void addAddressEntity(String street, String city, String country) {
        if (addresses == null) {
            addresses = new HashSet<>();
        }
        AddressEntity a = new AddressEntity();
        a.setStreet(street);
        a.setCity(city);
        a.setCountry(country);
        addresses.add(a);
    }
}
