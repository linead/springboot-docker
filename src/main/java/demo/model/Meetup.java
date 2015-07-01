package demo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Meetup {

    @Id
    private long id;

    private String title;

    private Date date;

    @OneToMany(mappedBy = "meetup", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private List<Member> attendees;

    public Meetup() {

    }

    public Meetup(Long id, String title) {
        setId(id);
        setTitle(title);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Member> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<Member> attendees) {
        this.attendees = attendees;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Meetup {" +
                " title='" + title + '\'' +
                ", attendees=" + getAttendees() +
                " }";
    }

    public Resource toResource() {
        return new Resource(getId(), getTitle());
    }


    public class Resource extends ResourceSupport {

        @JsonProperty("id")
        private final Long meetupId;

        private final String title;

        @JsonCreator
        public Resource(@JsonProperty("id") Long id, @JsonProperty("title") String title) {

            this.meetupId = id;
            this.title = title;
        }

        public Long getMeetupId() {
            return id;
        }

        public String getTitle() {
            return title;
        }
    }
}
