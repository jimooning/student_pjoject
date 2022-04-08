package studentProgram;

import java.io.Serializable;
import java.util.Objects;

public class StudentModel implements Comparable<Object>, Serializable{
	//�����
	public static final int SUBJECT_COUNT=3;
	//�������
	private String name;
	private String id;
	private String hp;
	private int kor;
	private int math;
	private int eng;
	private int total;
	private double avr;
	private String grade;
	//������(����Ʈ������,�Ű�����������)
	public StudentModel(String name,  String id, String hp, int kor, int math, int eng, int total, Double avr, String grade) {
		super();
		this.name = name;
		this.id = id;
		this.hp = hp;
		this.kor = kor;
		this.math = math;
		this.eng = eng;
		this.total = total;
		this.avr = avr;
		this.grade = grade;
	}
	//�հ��� �Լ�
	public void totalSum(){
		this.total=this.kor+this.math+this.eng;
	}
	//��հ�� �Լ�
	public void totalAvr(){
		this.avr=this.total/SUBJECT_COUNT;
	}
	//��ް�� �Լ�
	public void calGrade() {
		if(this.avr>=90.0) {
			this.grade="A";
		}else if(this.avr>=80.0) {
			this.grade="B";
		}else if(this.avr>=70.0) {
			this.grade="C";
		}else if(this.avr>=60.0) {
			this.grade="D";
		}else {
			this.grade="F";
		}
	}
	//�������̵� : hashcode(hp), equals(hp), compareTo(avr), toString
	@Override
	public int hashCode() {
		return Objects.hash(hp);
	}
	@Override
	public boolean equals(Object obj) {
		boolean flag=false;
		if(obj instanceof StudentModel) {
			StudentModel sd=(StudentModel)obj;
			flag=this.hp.equals(sd.hp);
		}
		return flag;
	}
	@Override
	public int compareTo(Object o) {
		if(!(o instanceof StudentModel)) {
			throw new IllegalArgumentException("�񱳰� �ȵǴ� ��ü�� ���¾��");
		}
		StudentModel studentData=(StudentModel)o;
		if(this.avr<studentData.avr) {
			return -1;
		}else if(this.avr>studentData.avr) {
			return 1;
		}else {
			return 0;
		}		
	}
	@Override
	public String toString() {
		return  name + "\t" +  id +  "\t" + hp
				+  "\t" + kor +  "\t" + math +  "\t" + eng +  "\t" + total +  "\t" + String.format("%6.2f", avr) +  "\t"
				+ grade ;
	}
	//getter,setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHp() {
		return hp;
	}
	public void setHp(String hp) {
		this.hp = hp;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getKor() {
		return kor;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}
	public int getMath() {
		return math;
	}
	public void setMath(int math) {
		this.math = math;
	}
	public int getEng() {
		return eng;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public double getAvr() {
		return avr;
	}
	public void setAvr(double avr) {
		this.avr = avr;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public static int getSubjectCount() {
		return SUBJECT_COUNT;
	}
	
	

	
	
	
	
}
