package demo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;

@Entity
public class Member {

    @Id
    private long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Meetup meetup;

    public Member() {}

    public Member(Meetup m, long id, String name) {
        this.meetup = m;
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Meetup getMeetup() {
        return meetup;
    }

    @Override
    public String toString() {
        return name;
    }

    public Resource toResource() {
        return new Resource(this.getId(), this.getName());
    }

    public class Resource extends ResourceSupport {

        @JsonProperty("id")
        private final Long memberId;

        private final String name;

        @JsonCreator
        public Resource(@JsonProperty("id") Long id, @JsonProperty("name") String name) {

            this.memberId = id;
            this.name = name;
        }

        public Long getMemberId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
}
