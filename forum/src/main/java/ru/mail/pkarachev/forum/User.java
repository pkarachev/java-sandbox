package ru.mail.pkarachev.forum;

import org.hibernate.annotations.GenericGenerator;
import ru.mail.pkarachev.forum.util.Utils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author pkarachev
 */
@Entity
@Table
public class User {
    private Long id;
    private String name;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return Utils.toJson(this);
    }
}
