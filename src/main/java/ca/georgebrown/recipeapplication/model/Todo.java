package ca.georgebrown.recipeapplication.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Todo {

    @Id
    @Column
    @GeneratedValue
    private Integer id;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date date;

    @ManyToOne(cascade = CascadeType.ALL)
    private Recipe recipe;

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
