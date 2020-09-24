package com.machbees.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A UserMaster.
 */
@Entity
@Table(name = "user_master")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 40)
    @Pattern(regexp = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")
    @Column(name = "email_id", length = 40, nullable = false)
    private String emailId;

    @NotNull
    @Size(max = 40)
    @Column(name = "password", length = 40, nullable = false)
    private String password;

    @Size(max = 40)
    @Column(name = "updated_by", length = 40)
    private String updatedBy;

    @Column(name = "updated_date")
    private LocalDate updatedDate;

    @OneToMany(mappedBy = "user")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<UserLanguageDetails> userlanguagedetails = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "userMasters", allowSetters = true)
    private CategoryMetadata status;

    @ManyToOne
    @JsonIgnoreProperties(value = "userMasters", allowSetters = true)
    private ProfileMaster profile;

    @ManyToOne
    @JsonIgnoreProperties(value = "userMasters", allowSetters = true)
    private CategoryMetadata profileCategory;

    @ManyToOne
    @JsonIgnoreProperties(value = "userMasters", allowSetters = true)
    private CategoryMetadata paymentSubscription;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmailId() {
        return emailId;
    }

    public UserMaster emailId(String emailId) {
        this.emailId = emailId;
        return this;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public UserMaster password(String password) {
        this.password = password;
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public UserMaster updatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public UserMaster updatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Set<UserLanguageDetails> getUserlanguagedetails() {
        return userlanguagedetails;
    }

    public UserMaster userlanguagedetails(Set<UserLanguageDetails> userLanguageDetails) {
        this.userlanguagedetails = userLanguageDetails;
        return this;
    }

    public UserMaster addUserlanguagedetails(UserLanguageDetails userLanguageDetails) {
        this.userlanguagedetails.add(userLanguageDetails);
        userLanguageDetails.setUser(this);
        return this;
    }

    public UserMaster removeUserlanguagedetails(UserLanguageDetails userLanguageDetails) {
        this.userlanguagedetails.remove(userLanguageDetails);
        userLanguageDetails.setUser(null);
        return this;
    }

    public void setUserlanguagedetails(Set<UserLanguageDetails> userLanguageDetails) {
        this.userlanguagedetails = userLanguageDetails;
    }

    public CategoryMetadata getStatus() {
        return status;
    }

    public UserMaster status(CategoryMetadata categoryMetadata) {
        this.status = categoryMetadata;
        return this;
    }

    public void setStatus(CategoryMetadata categoryMetadata) {
        this.status = categoryMetadata;
    }

    public ProfileMaster getProfile() {
        return profile;
    }

    public UserMaster profile(ProfileMaster profileMaster) {
        this.profile = profileMaster;
        return this;
    }

    public void setProfile(ProfileMaster profileMaster) {
        this.profile = profileMaster;
    }

    public CategoryMetadata getProfileCategory() {
        return profileCategory;
    }

    public UserMaster profileCategory(CategoryMetadata categoryMetadata) {
        this.profileCategory = categoryMetadata;
        return this;
    }

    public void setProfileCategory(CategoryMetadata categoryMetadata) {
        this.profileCategory = categoryMetadata;
    }

    public CategoryMetadata getPaymentSubscription() {
        return paymentSubscription;
    }

    public UserMaster paymentSubscription(CategoryMetadata categoryMetadata) {
        this.paymentSubscription = categoryMetadata;
        return this;
    }

    public void setPaymentSubscription(CategoryMetadata categoryMetadata) {
        this.paymentSubscription = categoryMetadata;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserMaster)) {
            return false;
        }
        return id != null && id.equals(((UserMaster) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserMaster{" +
            "id=" + getId() +
            ", emailId='" + getEmailId() + "'" +
            ", password='" + getPassword() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            "}";
    }
}
