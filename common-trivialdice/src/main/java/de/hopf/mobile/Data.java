package de.hopf.mobile;

public interface Data
{
    Integer getNumber();
    void setNumber(Integer i1);
    Integer getNumber2();
    void setNumber2(Integer i2);
    Integer getNumber3();
    void setNumber3(Integer i3);
	Boolean hasInterrupted();
	void setInterrupted(Boolean b);
	Boolean hasRolled();
	void setRolled(Boolean b);
	
	int getCounter();
}
