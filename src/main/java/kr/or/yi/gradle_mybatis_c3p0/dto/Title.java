package kr.or.yi.gradle_mybatis_c3p0.dto;

public class Title {
	private int titleCode;
	private String titleName;

	
	public Title() {
		
	}

	public Title(int titleCode) {
		this.titleCode = titleCode;
	}

	public Title(int titleCode, String titleName) {
		this.titleCode = titleCode;
		this.titleName = titleName;
	}

	public int getTitleCode() {
		return titleCode;
	}

	public void setTitleCode(int titleCode) {
		this.titleCode = titleCode;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + titleCode;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Title other = (Title) obj;
		if (titleCode != other.titleCode)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("%s-%s", titleCode,titleName);
	}

	public Object[] toArray() {
		return new Object[]{String.format("T%03d", titleCode), titleName};
	}
}
