package ru.mail.pkarachev.forum;

import org.hibernate.annotations.GenericGenerator;
import ru.mail.pkarachev.forum.util.Utils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

/**
 * @author pkarachev
 */
@Entity
@Table
public class Topic {
    private Long id;
    private String name;
    private Date timestampValue;
    private User author;
    private List<Message> messages;

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

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    public Date getTimestampValue() {
        return timestampValue;
    }

    // TODO implement at db level
    public void setTimestampValue(Date timestampValue) {
        this.timestampValue = timestampValue;
    }

    @OneToOne
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @OneToMany
    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return Utils.toJson(this);
    }
}
