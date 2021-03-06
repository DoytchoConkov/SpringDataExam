package com.example.football.models.dto.rootDto;

import com.example.football.models.dto.StatSeedDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.beans.Transient;
import java.util.List;

@XmlRootElement(name = "stats")
@XmlAccessorType(XmlAccessType.FIELD)
public class StatRootSeedDto {

    @XmlElement(name = "stat")
    private List<StatSeedDto> stats;

    public StatRootSeedDto() {
    }

    public List<StatSeedDto> getStats() {
        return stats;
    }

    public void setStats(List<StatSeedDto> stats) {
        this.stats = stats;
    }
}
