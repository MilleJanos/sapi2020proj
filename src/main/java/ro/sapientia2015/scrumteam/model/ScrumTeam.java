package ro.sapientia2015.scrumteam.model;

import java.util.List;

import javax.persistence.*;


import ro.sapientia2015.story.model.Story;


@Entity
@Table(name="scrumteam")
public class ScrumTeam {

	public static final int MAX_LENGTH_NAME = 100;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@Column(name = "name", nullable = false, length = MAX_LENGTH_NAME)
    private String name;
	
	@Column(name = "members")
    private String members;
	
	
	//@JoinTable(name="scrumteam_and_stories", 
    //	joinColumns={@JoinColumn(name="id1")},
    //	inverseJoinColumns=@JoinColumn(name="id2"))
	@Column(name = "stories")
	@OneToMany(mappedBy = "scrumTeam", fetch = FetchType.LAZY)
    private List<Story> stories;
	
	private String storiesCSV;
	
	@Version
    private long version;
	
	
	public ScrumTeam() {
		
	}
	
	public static Builder getBuilder(String title) {
        return new Builder(title);
    }
	
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public String getMembers() {
		return members;
	}

	public List<Story> getStories() {
		return stories;
	}
	
	public String getStoriesCSV() {
		return storiesCSV;
	}


	private void updateStoriesCSV() {
		String titles = "";
		int i = 0;
		for(Story s : stories) {
			titles += s.getTitle();
			i++;
			if (i != stories.size()) {
				titles += ", ";
			}
		}
		storiesCSV = titles;
	}
	
	public static class Builder {

        private ScrumTeam built;

        public Builder(String name) {
            built = new ScrumTeam();
            built.name = name;
        }

        public ScrumTeam build() {
            return built;
        }

        public Builder members(String members) {
            built.members = members;
            return this;
        }
        
        public Builder stories(List<Story> stories) {
            built.stories = stories;
            built.updateStoriesCSV();
            return this;
        }
        
        // To add creation date use @PrePersist annotation and:
        // creationDate = DateTime.now()
        
        // To add modification date use @PreUpdate annotation and:
        // lastModifiedDate = DateTime.now()
    }
		
}
