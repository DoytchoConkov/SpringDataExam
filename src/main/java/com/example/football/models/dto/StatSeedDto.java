package com.example.football.models.dto;

import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
public class StatSeedDto {
    @XmlElement(name = "shooting")
    private BigDecimal shooting;
    @XmlElement(name = "passing")
    private BigDecimal passing;
    @XmlElement(name = "endurance")
    private BigDecimal endurance;

    public StatSeedDto() {
    }

    @Min(0)
    public BigDecimal getShooting() {
        return shooting;
    }

    public void setShooting(BigDecimal shooting) {
        this.shooting = shooting;
    }

    @Min(0)
    public BigDecimal getPassing() {
        return passing;
    }

    public void setPassing(BigDecimal passing) {
        this.passing = passing;
    }

    @Min(0)
    public BigDecimal getEndurance() {
        return endurance;
    }

    public void setEndurance(BigDecimal endurance) {
        this.endurance = endurance;
    }
}
