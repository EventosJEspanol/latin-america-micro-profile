package org.jespanol.conference;

import org.jespanol.conference.infra.YearAttributeConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Year;
import java.util.Objects;

@Entity
@Table(name = "Conference")
public class Conference {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    @NotBlank
    private String name;

    @NotNull
    @Convert(converter = YearAttributeConverter.class)
    @Column
    private Year year;

    @Column
    @NotNull
    private String city;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void update(Conference conference) {
        this.city = conference.city;
        this.name = conference.name;
        this.year = conference.year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Conference that = (Conference) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Conference{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", city='" + city + '\'' +
                '}';
    }

}



