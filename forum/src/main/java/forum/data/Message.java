package forum.data;

import org.hibernate.annotations.GenericGenerator;
import forum.util.Utils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@Table
public class Message {
    private Long id;
    private String text;
    private Date timestampValue;
    private boolean modified;
    private User author;

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
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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

    @Column
    public boolean isModified() {
        return modified;
    }

    // TODO implement at db level
    public void setModified(boolean modified) {
        this.modified = modified;
    }

    @OneToOne
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return Utils.toJson(this);
    }
}
