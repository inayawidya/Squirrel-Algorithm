package ssa;

import java.util.Random;
import java.util.Scanner;
import java.util.logging.Logger;

public class Algorithm {

    private static final Logger LOG = Logger.getLogger(Algorithm.class.getName());

    public Integer[][] inputData(Scanner input, Integer facility, Integer location) {
        System.out.println("\n===================INPUT DATA===================\n");
        Integer[][] inputData = new Integer[facility][location];
        for (Integer i = 0; i < facility; i++) {
            for (Integer j = 0; j < location; j++) {
                System.out.print("Input Data : ");
                inputData[i][j] = Integer.parseInt(input.nextLine()); //masukkan data inputan
//                inputData[i][j] = randomInteger(1, 9); //masukkan data inputan
            }
        }

        //bentuk matriksnya
        for (Integer i = 0; i < facility; i++) {
            for (Integer j = 0; j < location; j++) {
                System.out.print(inputData[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("\n===================INPUT DATA===================\n");
        return inputData;
    }

    private Integer randomInteger(Integer min, Integer max) {
        return new Random().nextInt(max - min + 1) + min;
    }

    //membangkitkan populasi squirrel terbang awal
    public Double[][] population(Integer n, Integer facility, Integer fsu, Integer fsl) {
        System.out.println("\n===================PUPULATION===================\n");
        Double[][] squirrel = new Double[n][facility];
        for (Integer i = 0; i < n; i++) {
            for (Integer j = 0; j < facility; j++) {
                squirrel[i][j] = (fsl + (Math.random() * ((fsu - fsl))));
            }
        }

        for (Integer i = 0; i < n; i++) {
            for (Integer j = 0; j < facility; j++) {
                System.out.print(squirrel[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("\n===================PUPULATION===================\n");
        return squirrel;
    }

    //pengurutan squirrel
    public Integer[][] order(Integer n, Integer facility, Double[][] squirrel) {
        System.out.println("\n===================ORDER===================\n");
        Integer[][] order = new Integer[n][facility];
        for (Integer i = 0; i < n; i++) {
            for (Integer j = 0; j < facility; j++) {
                Integer k = 1;
                for (Integer m = 0; m < facility; m++) {
                    if (squirrel[i][j] > squirrel[i][m]) {
                        k += 1;
                    }
                }
                order[i][j] = k;
            }
        }

        for (Integer i = 0; i < n; i++) {
            for (Integer j = 0; j < facility; j++) {
                System.out.print(order[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("\n===================ORDER===================\n");
        return order;
    }

    //menentukan matriks penempatan
    public Integer[][] facility(Integer n, Integer facility, Integer[][] order) {
        System.out.println("\n===================FACILITY===================\n");
        Integer[][] x = new Integer[n][facility];
        for (Integer i = 0; i < n; i++) {
            for (Integer j = 0; j < facility; j++) {
                for (Integer k = 0; k < facility; k++) {
                    if (order[i][k].equals(j)) {
                        x[j][k] = 1;
                    } else {
                        x[j][k] = 0;
                    }
                }
            }
        }

        for (Integer i = 0; i < n; i++) {
            for (Integer j = 0; j < facility; j++) {
                System.out.print(x[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("\n===================FACILITY===================\n");
        return x;
    }

    //menghitung nilai fungsi tujuan(nilai fitness)
    public Double[] calculationFitness(Integer n, Integer facility, Integer frekuansi1[][], Integer frekuansi2[][], Integer distance[][], Integer x[][]) {
        System.out.println("\n===================CALCULATION FITNESS===================\n");
        Double[] fitness = new Double[n];
        for (Integer i = 0; i < n; i++) {
            fitness[i] = 0.0; //nilaifitness
            for (Integer j = 0; j < facility; j++) {
                for (Integer k = 0; k < facility; k++) {
                    for (Integer l = 0; l < facility; l++) {
                        for (Integer m = 0; m < facility; m++) {
                            fitness[i] = (fitness[i]
                                    + (0.5 * frekuansi1[j][l] * distance[k][m] * x[j][k] * x[l][m])
                                    + 0.5
                                    * (frekuansi2[j][l] * distance[k][m] * x[j][k] * x[l][m]));
                        }
                    }
                }
            }
        }
        for (Integer i = 0; i < n; i++) {
            for (Integer j = 0; j < facility; j++) {
                System.out.print(x[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("\n===================CALCULATION FITNESS===================\n");
        return fitness;
    }

    //menentukan facility.getLocation() masing-masing squirrel terbang
    public void location(Integer n, Integer fitness) {
        System.out.println("\n===================LOCATION===================\n");
        Integer[] accommodate2 = null;
        for (Integer i = 0; i < n; i++) {
            accommodate2[i] = i;
        }
        Integer[] accommodate = null;
        for (Integer i = 0; i < n; i++) {
            for (Integer j = 0; j < n - 1; j++) {
                if (accommodate[j] > accommodate[j + 1]) {
                    Integer temp = accommodate[j];
                    accommodate[j] = accommodate[j + 1];
                    accommodate[j + 1] = temp;

                    Integer temp2 = accommodate2[j];
                    accommodate2[j] = accommodate2[j + 1];
                    accommodate2[j + 1] = temp2;
                }
            }
        }
        System.out.println("\n===================LOCATION===================\n");
    }

    //modifikasi facility.getLocation() squirrel terbang berada pada pohon acorn
    public Double[][] newLocation(Integer n, Integer facility, Double squirrel[][], Double dg, Double gc, Double pdp) {
        System.out.println("\n===================NEW LOCATION===================\n");
        Double[][] newSquirrel = new Double[n][facility];
        for (Integer i = 0; i < n; i++) {
            for (Integer j = 0; j < facility; j++) {
                if (i >= 2 && i <= 4) {
                    Double r1 = Math.random();
                    if (r1 >= pdp) {
//                        newSquirrel[i][j] = dg * gc * (squirrel[1][j] - squirrel[i][j]); // rumus asli tapi error
                        newSquirrel[i][j] = dg * gc * (squirrel[1][j] - squirrel[i][j]);
                    } else {
                        newSquirrel[i][j] = Math.random();
                    }
                }
            }
        }

        for (Integer i = 0; i < n; i++) {
            for (Integer j = 0; j < facility; j++) {
                System.out.print(newSquirrel[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("\n===================NEW LOCATION===================\n");
        return newSquirrel;
    }

    //modifikasi facility.getLocation() squirrel terbang berada pada pohon normal
    public Double[][] normalTree(Integer n, Integer facility, Double squirrel[][], Double dg, Double gc, Double pdp) {
        System.out.println("\n===================NORMAL TREE===================\n");
        Double[][] newSquirrel = new Double[n][facility];
        for (Integer i = 0; i < n; i++) {
            for (Integer j = 0; j < facility; j++) {
                Double k = Math.random();
                if (k <= 0.5) {
                    Double R2 = Math.random();
                    if (R2 >= pdp) {
//                        newSquirrel[i][j] += dg * gc * (squirrel[2][j] - squirrel[i][j]);
                        newSquirrel[i][j] = dg * gc * (squirrel[2][j] - squirrel[i][j]);
                    } else {
                        newSquirrel[i][j] = Math.random();
                    }
                }
                if (k > 0.5) {
                    Double R3 = Math.random();
                    if (R3 >= pdp) {
//                        newSquirrel[i][j] += dg * gc * (squirrel[1][j] - squirrel[i][j]);
                        newSquirrel[i][j] = dg * gc * (squirrel[1][j] - squirrel[i][j]);
                    } else {
                        newSquirrel[i][j] = Math.random();
                    }
                }
            }
        }

        for (Integer i = 0; i < n; i++) {
            for (Integer j = 0; j < facility; j++) {
                System.out.print(newSquirrel[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("\n===================NORMAL TREE===================\n");
        return newSquirrel;
    }

    //menghitung dan update nilai konstanta musim dan konstanta musim minimum
    public Double[] calculationUpdateCons(Integer facility, Double newSquirrel[][], Integer maxIter) {
        System.out.println("\n===================CALCULATION UPDATE CONSTANTA===================\n");
        Double[] season = new Double[2];
        Integer iterasi = 0;
        Integer a = -6;
        Double b = maxIter / 2.5;
        if (iterasi <= 3) {
            Double sc1 = 0.0;
            for (Integer i = 0; i < facility; i++) {
                for (Integer j = 0; j < i; j++) {
                    sc1 += (Math.pow(newSquirrel[i][j] - newSquirrel[1][j], 2));
                }
            }
            season[0] = Math.sqrt(sc1);
            //10e^-6/365^iterasi/max_iter/2.5
            season[1] = (Math.pow(Math.exp(10), a)) / (Math.pow(365, b));
        }
        
        for (Double s : season) {
            System.out.println(s + " ");
        }
        System.out.println("\n===================CALCULATION UPDATE CONSTANTA===================\n");
        return season;
    }

    //memperbarui facility.getLocation() squirrel terbang menggunakan levy flight
    public Double[][] levy(Integer n, Integer facility, Double lb, Double[][] newSquirrel, Double[] season, Integer fsu, Integer fsl) {
        System.out.println("\n===================LEVY===================\n");
        Double[] u = new Double[facility];
        Double[] v = new Double[facility];
        Double[] s = new Double[facility];
        Double[] l = new Double[facility];
//        Double r = null;
        for (Integer i = 0; i < n; i++) {
            for (Integer j = 0; j < facility; j++) {

                u[j] = Math.random();
                v[j] = Math.random();

                Double num = gamma(1.0 + lb) * Math.sin(Math.PI * lb / 2); //pembilang
                Double den = gamma((1.0 + lb) / 2.0) * lb * Math.pow(2.0, ((lb - 1.0) / 2.0)); //penyebut
                Double r = num / den;

                s[j] = u[j] / (Math.pow(Math.abs(v[j]), lb)); //U/|V|^1/2
                l[j] = (lb * gamma(lb) * Math.sin(Math.PI * lb / 2) / Math.PI) * 1 / (Math.pow(s[j], 1 + lb)); //nilai levy
                if (season[0] < season[1]) {
                    newSquirrel[i][j] = fsl + l[j] * (fsu - fsl);
                } else {
                    newSquirrel[i][j] = newSquirrel[i][j];
                }
            }
        }

        for (Integer i = 0; i < n; i++) {
            for (Integer j = 0; j < facility; j++) {
                System.out.print(newSquirrel[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("\n===================LEVY===================\n");
        return newSquirrel;
    }

    //update squirrel terbang
    public Double[][] updateFS(Integer n, Integer facility, Double newSquirrel[][], Double squirrel[][]) {
        System.out.println("\n===================UPDATE FS===================\n");
        for (Integer i = 0; i < n; i++) {
            for (Integer j = 0; j < facility; j++) {
                squirrel[i][j] = newSquirrel[i][j];
            }
        }

        for (Integer i = 0; i < n; i++) {
            for (Integer j = 0; j < facility; j++) {
                System.out.print(squirrel[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("\n===================UPDATE FS===================\n");
        return squirrel;
    }

    private Double logGamma(Double x) {
        Double tmp = (x - 0.5) * Math.log(x + 4.5) - (x + 4.5);
        Double ser = 1.0 + 76.18009173 / (x + 0) - 86.50532033 / (x + 1)
                + 24.01409822 / (x + 2) - 1.231739516 / (x + 3)
                + 0.00120858003 / (x + 4) - 0.00000536382 / (x + 5);
        return tmp + Math.log(ser * Math.sqrt(2 * Math.PI));
    }

    private Double gamma(Double x) {
        return Math.exp(logGamma(x));
    }

    private Double[][] randi(Integer iMax, Integer n) {
        Double[][] randi = new Double[n][n];
        for (Integer i = 0; i < n; i++) {
            for (Integer j = 0; j < n; j++) {
                randi[i][j] = (double) randomInteger(1, iMax);
                System.out.print(randi[i][j] + ", ");
            }
            System.out.println();
        }
        return randi;
    }

    private Double[][] randn(Integer n, Integer m) {
        Double[][] numArray = new Double[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                numArray[i][j] = Math.random();
                System.out.print(numArray[i][j] + ", ");
            }
            System.out.println();
        }
        return numArray;
    }

//    public static void main(String[] args) {
//        Algorithm a = new Algorithm();
//        a.randn(3, 3);
//        a.randi(3, 3);
//    }

}
