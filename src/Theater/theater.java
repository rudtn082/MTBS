package Theater;

public class theater {
	private String tTheaterID; //상영관 아이디
	private String tCinemaName; //영화상연관 이름
	private String tSeatNum;	//좌석 수
	private String tMovieID;	//영화아이디
	private String tStartTime; //상영시간
	public String gettTheaterID() {
		return tTheaterID;
	}
	public void settTheaterID(String tTheaterID) {
		this.tTheaterID = tTheaterID;
	}
	public String gettCinemaName() {
		return tCinemaName;
	}
	public void settCinemaName(String tCinemaName) {
		this.tCinemaName = tCinemaName;
	}
	public String gettSeatNum() {
		return tSeatNum;
	}
	public void settSeatNum(String tSeatNum) {
		this.tSeatNum = tSeatNum;
	}
	public String gettMovieID() {
		return tMovieID;
	}
	public void settMovieID(String tMovieID) {
		this.tMovieID = tMovieID;
	}
	public String gettStartTime() {
		return tStartTime;
	}
	public void settStartTime(String tStartTime) {
		this.tStartTime = tStartTime;
	}
	
}
