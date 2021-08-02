package com.example.football.models.dto;

import com.example.football.models.entity.enums.Position;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class PlayerSeedDto {
    @XmlElement(name = "first-name")
    private String firstName;
    @XmlElement(name = "last-name")
    private String lastName;
    @XmlElement(name = "email")
    private String email;
    @XmlElement(name = "birth-date")
    private String birthdate;
    @XmlElement(name = "position")
    private Position position;
    @XmlElement(name = "town")
    private TownSeedNameDto town;
    @XmlElement(name = "team")
    private TeamSeedNameDto team;
    @XmlElement(name = "stat")
    private StatSeedIdDto stat;


    @Size(min = 2)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Size(min = 2)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public TownSeedNameDto getTown() {
        return town;
    }

    public void setTown(TownSeedNameDto town) {
        this.town = town;
    }

    public TeamSeedNameDto getTeam() {
        return team;
    }

    public void setTeam(TeamSeedNameDto team) {
        this.team = team;
    }

    public StatSeedIdDto getStat() {
        return stat;
    }

    public void setStat(StatSeedIdDto stat) {
        this.stat = stat;
    }
}
