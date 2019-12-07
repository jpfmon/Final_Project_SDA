package com.influencer.management.app.model.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "influencer")
@Data
public class Influencer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 2, message = "Name must be at least 2 characters long")
    private String profileName;

    private String notes;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;


    /**
     * Here is added the field for the Personal Details Object of the Influencer
     * @return
     */
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "personal_details_id", nullable = true)
    private PersonalDetails personalDetails;

    /**
     * Here is added the field for the Instagram Profile Object of the Influencer
     * @return
     */
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "instagram_profile_id", nullable = true)
    private InstagramProfile instagramProfile;

    /**
     * Here is added the List of the days when Influencer has been contacted
     * @return
     */
    @OneToMany(mappedBy = "influencer")
    private List<DayContacted> daysContacted;

/**
 * Here is added the List of the reviews the Influencer has done
 *
 */
        @OneToMany(mappedBy = "influencer")
        private List<Review> reviews;

    /**
     * Here is added the List of the products sent to the Influencer
     *
     */
    @ManyToMany
    @JoinTable(name = "products_influencer",
    joinColumns = @JoinColumn(name = "influencer_id"),
    inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> productsSent;

    public String lastDayContacted(){
        System.out.println("size of daysContacted: " + daysContacted.size());
        return daysContacted.get(daysContacted.size()-1).getDate();
    }

    /**
     *
     * HERE are 3 "convenience" methods, to be able to add dayContacted, productSent, and Review  to the three lists
     */


 public void addDate(DayContacted dayContacted){
        if(daysContacted==null){
            daysContacted = new ArrayList<>();
        }

        daysContacted.add(dayContacted);
 }

    public void addProduct(Product productSent){
        if(productsSent==null){
            productsSent = new ArrayList<>();
        }
        productsSent.add(productSent);
    }

    public void addReview(Review review){
        if(reviews==null){
            reviews = new ArrayList<>();
        }
        reviews.add(review);
    }

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "personal_details_id", nullable = false)
    private PersonalDetails personalDetails;


}
