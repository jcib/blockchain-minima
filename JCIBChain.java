package jcibchain;

import java.util.ArrayList;

import com.google.gson.GsonBuilder;

public class JCIBChain {

	public static int difficulty = 2;

	public static ArrayList<Block> blockchain = new ArrayList<Block>();

	public static void main(String[] args) {

		// va añadiendo los bloques al array:
		blockchain.add(new Block("¡Primer bloque!", "0"));
		System.out.println("Minando bloque 1... ");
		blockchain.get(0).mineBlock(difficulty);

		blockchain.add(new Block("¡Segundo bloque!", blockchain.get(blockchain.size() - 1).hash));
		System.out.println("Minando bloque 2... ");
		blockchain.get(1).mineBlock(difficulty);

		blockchain.add(new Block("¡Tercer bloque!", blockchain.get(blockchain.size() - 1).hash));
		System.out.println("Minando bloque 3... ");
		blockchain.get(2).mineBlock(difficulty);

		System.out.println("\nBlockchain válida: " + isChainValid());
		
		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
		System.out.println("\nLa cadena de bloques: ");
		System.out.println(blockchainJson);
	}

	public static Boolean isChainValid() {
		Block currentBlock;
		Block previousBlock;
		String hashTarget = new String(new char[difficulty]).replace('\0', '0');

		// hace un loop para comprobar los hashes:
		for (int i = 1; i < blockchain.size(); i++) {
			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i - 1);

			if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
				System.out.println("Los hashes actuales no son los mismos.");
				return false;
			}

			if (!previousBlock.hash.equals(currentBlock.previousHash)) {
				System.out.println("Los hashes previos no son los mismos.");
				return false;
			}
			// comprueba si el hash se ha resuelto
			if (!currentBlock.hash.substring(0, difficulty).equals(hashTarget)) {
				System.out.println("El bloque no se ha minado");
				return false;
			}
		}
		return true;
	}
}