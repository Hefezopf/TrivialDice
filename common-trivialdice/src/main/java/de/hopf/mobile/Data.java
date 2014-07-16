package de.hopf.mobile;

public interface Data
{
	Integer getNumber();
	void setNumber(Integer i);
	Boolean hasInterrupted();
	void setInterrupted(Boolean b);
	Boolean hasRolled();
	void setRolled(Boolean b);
	
	int getCounter();
}
