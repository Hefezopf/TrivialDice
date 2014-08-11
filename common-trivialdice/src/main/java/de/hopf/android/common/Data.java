package de.hopf.android.common;

public interface Data
{
    Integer getNumber();
    void setNumber(Integer i1);
    Integer getNumber2();
    void setNumber2(Integer i2);
    Integer getNumber3();
    void setNumber3(Integer i3);
    Integer getNumber4();
    void setNumber4(Integer i4);
	Boolean hasInterrupted();
	void setInterrupted(Boolean b);
	Boolean hasRolled();
	void setRolled(Boolean b);
	
	int getCounter();
}
