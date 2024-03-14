package com.onboarding.actors.actorsws.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "actors")
public class ActorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "actor_id")
    private Integer actorId;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "gender")
    private String gender;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "place_of_birth")
    private String placeOfBirth;

    @Column(name = "children")
    private String children;

    @Column(name = "spouse")
    private String spouse;

    @Column(name = "parents")
    private String parents;

    @Column(name = "biography")
    private String biography;

    @Column(name = "image_name")
    private String imageName;

    @Transient
    private Integer age;

    public ActorEntity() {

    }

    public ActorEntity(String fullName, String gender, String jobTitle, Date dateOfBirth,
                       String placeOfBirth, String children, String spouse, String parents,
                       String biography, String imageName, Integer age) {
        this.fullName = fullName;
        this.gender = gender;
        this.jobTitle = jobTitle;
        this.dateOfBirth = dateOfBirth;
        this.placeOfBirth = placeOfBirth;
        this.children = children;
        this.spouse = spouse;
        this.parents = parents;
        this.biography = biography;
        this.imageName = imageName;
        this.age = age;
    }

    public Integer getActorId() {
        return actorId;
    }

    public void setActorId(Integer actorId) {
        this.actorId = actorId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getChildren() {
        return children;
    }

    public void setChildren(String children) {
        this.children = children;
    }

    public String getSpouse() {
        return spouse;
    }

    public void setSpouse(String spouse) {
        this.spouse = spouse;
    }

    public String getParents() {
        return parents;
    }

    public void setParents(String parents) {
        this.parents = parents;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "ActorEntity{" +
                "actorId=" + actorId +
                ", fullName='" + fullName + '\'' +
                ", gender='" + gender + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", placeOfBirth='" + placeOfBirth + '\'' +
                ", children='" + children + '\'' +
                ", spouse='" + spouse + '\'' +
                ", parents='" + parents + '\'' +
                ", biography='" + biography + '\'' +
                ", imageName='" + imageName + '\'' +
                ", age=" + age +
                '}';
    }
}
