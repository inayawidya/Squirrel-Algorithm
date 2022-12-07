package ssa;

import java.util.Random;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class Main {

    private static final Logger LOG = Logger.getLogger(Main.class.getName());

//    private Integer[][] frekuansi1;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        QuadraticAssignmentProblem qap = new QuadraticAssignmentProblem();
        Scanner input = new Scanner(System.in);
        try {
            System.out.print("Masukkan banyak fasilitas : ");
            qap.setFacility(Integer.parseInt(input.nextLine()));
//            qap.setFacility(randomInteger(3, 3));
            System.out.print("Masukkan banyak lokasi : ");
            qap.setLocation(Integer.parseInt(input.nextLine()));
//            qap.setLocation(randomInteger(3, 3));
            System.out.println(qap.toString());
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }

        Algorithm algorithm = new Algorithm();
        Integer[][] frekuansi1 = algorithm.inputData(input, qap.getFacility(), qap.getLocation());
        Integer[][] frekuansi2 = algorithm.inputData(input, qap.getFacility(), qap.getLocation());
        Integer[][] distance = algorithm.inputData(input, qap.getFacility(), qap.getLocation());

        Double[][] squirrel = algorithm.population(Param.N, qap.getFacility(), 1, 0);

        Integer[][] order = algorithm.order(Param.N, qap.getFacility(), squirrel);

        Integer[][] x = algorithm.facility(Param.N, qap.getFacility(), order);
        
        Double[] fitness = algorithm.calculationFitness(Param.N, qap.getFacility(), frekuansi1, frekuansi2, distance, x);
        
        Double[][] newSquirrel = algorithm.newLocation(Param.N, qap.getFacility(), squirrel, Param.DG, Param.GC, Param.PDP);
        
        newSquirrel = algorithm.normalTree(Param.N, qap.getFacility(), squirrel, Param.DG, Param.GC, Param.PDP);
        
        Double[] season = algorithm.calculationUpdateCons(qap.getFacility(), newSquirrel, Param.MAX_ITER);
        
        newSquirrel = algorithm.levy(Param.N, qap.getFacility(), Param.LAMBDA, newSquirrel, season, 1, 0);
        
        squirrel = algorithm.updateFS(Param.N, qap.getFacility(), newSquirrel, squirrel);
        

    }

    private static Integer randomInteger(Integer min, Integer max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

}
