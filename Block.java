package jcibchain;
import java.util.Date;

public class Block {
	
	public String hash;
	public String previousHash;
	private String data; // mensaje
	private long timeStamp; //en milisegundos
	
	//constructor
	public Block(String data, String previousHash) {
		
		this.data = data;
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();
		this.hash = calculateHash();
	
	}
	
	//método para calcular el hash
	public String calculateHash() {
		String calculatedhash = StringUtil.applySha256( 
				previousHash +
				Long.toString(timeStamp) +
				data 
				);
		return calculatedhash;
	}
}