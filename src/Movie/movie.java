package Movie;

public class movie {
	private String mvID;  //���̵�
	private String mvTheaterID; //�󿵰� ���̵�	
	private String mvMovieTitle; //��ȭ����
	private String mvDirector; //��ȭ ����
	private String mvGrade; //���
	private String mvActor;	//��ȭ����
	private String mvInfo;//�ֿ�����
	private String mvAccumulateNum;//�������ż�
	
	
	public String getMvAccumulateNum() {
		return mvAccumulateNum;
	}
	public String getMvTheaterID() {
		return mvTheaterID;
	}
	public void setMvTheaterID(String mvTheaterID) {
		this.mvTheaterID = mvTheaterID;
	}
	public void setMvAccumulateNum(String mvAccumulateNum) {
		this.mvAccumulateNum = mvAccumulateNum;
	}
	public String getMvID() {
		return mvID;
	}
	public void setMvID(String mvID) {
		this.mvID = mvID;
	}
	
	public String getMvMovieTitle() {
		return mvMovieTitle;
	}
	public void setMvMovieTitle(String mvMovieTitle) {
		this.mvMovieTitle = mvMovieTitle;
	}
	public String getMvDirector() {
		return mvDirector;
	}
	public void setMvDirector(String mvDirector) {
		this.mvDirector = mvDirector;
	}
	public String getMvActor() {
		return mvActor;
	}
	public void setMvActor(String mvActor) {
		this.mvActor = mvActor;
	}
	public String getMvGrade() {
		return mvGrade;
	}
	public void setMvGrade(String mvGrade) {
		this.mvGrade = mvGrade;
	}
	public String getMvInfo() {
		return mvInfo;
	}
	public void setMvInfo(String mvInfo) {
		this.mvInfo = mvInfo;
	}
}
