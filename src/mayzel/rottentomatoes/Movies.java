package mayzel.rottentomatoes;

public class Movies {

	private Posters posters;
	private String title;
	private String[] genres;
	private Ratings critics_rating;

	public Movies() {

	}

	public Posters getPosters() {
		return posters;
	}

	public String getTitle() {
		return title;
	}

	public String[] getGenres() {
		return genres;
	}

	public Ratings getCritics_rating() {
		return critics_rating;
	}

}
