package queueImplementation;


public class Songs implements Comparable<Songs>{
	static int count = 0;
  int id;  
	String name;
	
	
	public Songs(String name) {
		this.id = ++count;
		this.name = name;
	}
	
	
	public Songs(int id,String name) {
		this.id = id;
		this.name = name;
		count++;
	}


	public static int getCount() {
		return count;
	}


	public static void setCount(int count) {
		Songs.count = count;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int compareTo(Songs o) {
		return Integer.compare(o.id, this.id);
	}
	
}
