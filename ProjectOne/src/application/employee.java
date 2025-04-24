package application;

public class employee {
//EmployeeID, Name, Age, Department, Date of Joining, and Gender.
	private String id ;
	private String name ;
	private int age;
	private String Dep ;
	private String date;
	private String gender ;

	public employee (String id,String name,int age,String dep,String date,String gender) {
this.id = id ; 
this.name = name ; 
this.age = age ; 
this.Dep = dep ; 
this.date = date ; 
this.gender = gender ; 		
	}

	public employee () {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getDep() {
		return Dep;
	}

	public void setDep(String dep) {
		Dep = dep;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return " id= "  + id + " name= " + name + " age= " + age + " Dep= " + Dep + " date= " + date
				+ " gender= " + gender +"\n";
	}
}
