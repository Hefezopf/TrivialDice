package de.hopf.android.trivialdice.color.lite;

public interface Data
{
	Integer getNumber();
	void setNumber(Integer i);
	Boolean hasInterrupted();
	void setInterrupted(Boolean b);
	Boolean hasRolled();
	void setRolled(Boolean b);
}
