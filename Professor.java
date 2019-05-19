public class Professor {
	private long id;
	private String nome;
	private int matricula;
	private String area;
	public Professor(long id, String n, int m, String a) {
		this.id = id;
		this.nome = n;
		this.matricula = m;
		this.area = a;
	}
	public Professor() {
		this(-1, "", 0, "");
	}
	public long getId() { return id; }
	public void setId(long id) { this.id = id; }
	public String getNome() { return nome; }
	public void setNome(String n) { this.nome = n; }
	public int getMatricula() { return matricula; }
	public void setMatricula(int m) { this.matricula = m; }
	public String getArea() { return area; }
	public void setArea(String a) { this.area = a; }
}