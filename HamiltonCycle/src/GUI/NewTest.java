package GUI;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import exceptions.GeneticAlgorithmExceptions;
import geneticAlgorithm.GeneticAlgorithm;
import geneticAlgorithm.Population;
import graph.Node;
import graph.NodeManager;
import hamiltonAlgorithm.HamiltonAlgorithm;
import hamiltonAlgorithm.MatrixPermission;

public class NewTest {
	public static void main(String[] args) {
		/*
		 * Create objects of nodes
		 */
		Node node0 = new Node();
		NodeManager.addNode(node0);
		Node node1 = new Node();
		NodeManager.addNode(node1);
		Node node2 = new Node();
		NodeManager.addNode(node2);
		Node node3 = new Node();
		NodeManager.addNode(node3);
		Node node4 = new Node();
		NodeManager.addNode(node4);
		Node node5 = new Node();
		NodeManager.addNode(node5);
		Node node6 = new Node();
		NodeManager.addNode(node6);
		Node node7 = new Node();
		NodeManager.addNode(node7);

		/*
		 * Add some Permission to above nodes.
		 */

		MatrixPermission.addPermission(0, 1);
		MatrixPermission.addPermission(0, 4);
		MatrixPermission.addPermission(1, 2);
		MatrixPermission.addPermission(2, 4);
		MatrixPermission.addPermission(2, 3);
		MatrixPermission.addPermission(4, 3);
		MatrixPermission.addPermission(4, 6);
		MatrixPermission.addPermission(3, 6);
		MatrixPermission.addPermission(3, 5);
		MatrixPermission.addPermission(5, 7);
		MatrixPermission.addPermission(7, 4);
		MatrixPermission.addPermission(5, 2);
		MatrixPermission.addPermission(6, 7);
		MatrixPermission.addPermission(4, 3);

		System.out.println("Permission matrix: ");
		System.out.println(MatrixPermission.toPrint()); // print of matrix
														// permission

		MatrixPermission.setReady(); // setReady to true. We set that program is
										// ready to start.

		Population testPopulacji = new Population(100, true);
		GeneticAlgorithm genetic = new GeneticAlgorithm(100,0.1);
		/******************* Test population *************************/
		System.out.println();
		for (int i = 0; i < 100000; i++)
			if (!genetic.isFull()) {
				try {
					testPopulacji = genetic.solvePopulation(testPopulacji);
				} catch (GeneticAlgorithmExceptions e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		//
		
		Population hamiltonPaths = genetic.getHamiltonPaths(); //population of correct nonduplicated pathes. 
		System.out.println("Correct paths: " + genetic.getAmountGoodPath());
		for (int i = 0; i < genetic.getAmountGoodPath(); i++) {
			System.out.println(HamiltonAlgorithm.checkHamilton(hamiltonPaths
					.getTour(i)) + " : " + hamiltonPaths.getTour(i));
		}
	
		PrintWriter zapis = null;
		 try {
			 zapis =  new PrintWriter("hamilton.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 zapis.println(MatrixPermission.toPrint());
		 zapis.println("Correct paths: " + genetic.getAmountGoodPath());
		for (int i = 0; i < genetic.getAmountGoodPath(); i++) {
			zapis.println(HamiltonAlgorithm.checkHamilton(hamiltonPaths
						.getTour(i)) + " : " + hamiltonPaths.getTour(i));
			}

		zapis.close();
	}

}

