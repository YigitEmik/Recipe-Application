package ca.georgebrown.recipeapplication.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Table(name = "recipes")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Lob
    private String imageUrl;

    private String name;

    private int prepTime;

    private int cookTime;

    @Lob
    private String ingredients;

    @Lob
    private String method;

    public List<User> getFavoriteUsers() {
        return favoriteUsers;
    }

    public void setFavoriteUsers(List<User> favoriteUsers) {
        this.favoriteUsers = favoriteUsers;
    }

    @CreatedDate
    private Date creationDate;


    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name = "user_fav_recipes", joinColumns = @JoinColumn(name = "recipe_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> favoriteUsers = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(int prepTime) {
        this.prepTime = prepTime;
    }

    public int getCookTime() {
        return cookTime;
    }

    public void setCookTime(int cookTime) {
        this.cookTime = cookTime;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", imageUrl='" + imageUrl + '\'' +
                ", name='" + name + '\'' +
                ", prepTime=" + prepTime +
                ", cookTime=" + cookTime +
                ", ingredients='" + ingredients + '\'' +
                ", method='" + method + '\'' +
                '}';
    }
}
