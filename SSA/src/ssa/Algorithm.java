package ssa;

public class Algorithm {

    //membangkitkan populasi squirrel terbang awal
    public void population(Integer N, Integer D, Integer fsu, Integer fsl) {
        Double iterasi[] = new Double[Param.MAX_ITER];
        for (int iter = 0; iter < Param.MAX_ITER; iter++) {
            System.out.println("\n===================INISIALISASI POPULASI===================\n");
            Double[][] squirrel = new Double[N][D];
            Double[][] squirrel1 = new Double[N][D];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < D; j++) {
                    squirrel[i][j] = (fsl + (Math.random() * ((fsu - fsl))));
                }
            }

            for (Integer i = 0; i < N; i++) {
                for (Integer j = 0; j < D; j++) {
                    System.out.print(squirrel[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("\n===================INISIALISASI POPULASI===================\n");

            System.out.println("\n===================PANGKAT===================\n");

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < D; j++) {
                    squirrel1[i][j] = Math.pow(squirrel[i][j], 2);
                }
            }

            for (Integer i = 0; i < N; i++) {
                for (Integer j = 0; j < D; j++) {
                    System.out.print(squirrel1[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("\n===================PANGKAT===================\n");

            System.out.println("\n===================CALCULATE FITNESS===================\n");
            Double sum[][] = new Double[2][N];
            for (int i = 0; i < N; i++) {
                sum[0][i] = Double.valueOf(i + 1);
                sum[1][i] = 0.0;
                for (int j = 0; j < D; j++) {
                    sum[1][i] += squirrel1[i][j];
                }
                System.out.println("fitness FS " + sum[0][i].intValue() + " adalah: " + sum[1][i]);
            }
            System.out.println("\n===================CALCULATE FITNESS===================\n");

            System.out.println("\n===================ORDER===================\n");

            sort(sum, N);
            String tree;
            for (int i = 0; i < N; i++) {
                if (i == 0) {
                    tree = "Hickory Nut Tree";
                } else if (i >= 1 && i <= 3) {
                    tree = "Acorn  Nut Tree";
                } else {
                    tree = "Normal Tree";
                }
                System.out.println("fitness FS " + sum[0][i].intValue() + " adalah: " + sum[1][i] + " " + tree);
            }
            System.out.println("\n===================ORDER===================\n");

            System.out.println("\n===================MODIFIKASI TUPAI TERBANG===================\n");

            Double newsquirrel[][] = new Double[N][D];

            for (Integer i = 0; i < N; i++) {
                for (Integer j = 0; j < D; j++) {
                    newsquirrel[i][j] = squirrel[sum[0][i].intValue() - 1][j];
                }
            }

//        for (Integer i = 0; i < 1; i++) {
//            for (Integer j = 0; j < D; j++) {
//                System.out.print(squirrel[sum[0][i].intValue() - 1][j] + " ");
//            }
//            System.out.println();
//        }
            for (Integer i = 0; i < 1; i++) {
                for (Integer j = 0; j < D; j++) {
                    System.out.print(newsquirrel[i][j] + " ");
                }
                System.out.println();
            }

//        for (Integer i = 1; i < 4; i++) {
//            for (Integer j = 0; j < D; j++) {
//                System.out.print(squirrel[sum[0][0].intValue() - 1][j] + Param.DG * Param.GC * (squirrel[sum[0][i].intValue() - 1][j] - squirrel[sum[0][0].intValue() - 1][j]) + " ");
//            }
//            System.out.println();
//        }
            for (Integer i = 1; i < 4; i++) {
                for (Integer j = 0; j < D; j++) {
                    System.out.print(newsquirrel[0][j] + Param.DG * Param.GC * (newsquirrel[i][j] - newsquirrel[0][j]) + " ");
                }
                System.out.println();
            }

//        for (Integer i = 3; i < N; i++) {
//            for (Integer j = 0; j < D; j++) {
//                System.out.print(squirrel[sum[0][0].intValue() - 1][j] + Param.DG * Param.GC * (squirrel[sum[0][i].intValue() - 1][j] - squirrel[sum[0][0].intValue() - 1][j]) + " ");
//            }
//            System.out.println();
//        }
            for (Integer i = 3; i < N; i++) {
                for (Integer j = 0; j < D; j++) {
                    Double k = Math.random();
                    if (k <= 0.5) {
                        System.out.print(newsquirrel[0][j] + Param.DG * Param.GC * (squirrel[2][j] - squirrel[i][j]) + " ");
                    } else {
                        System.out.print(newsquirrel[0][j] + Param.DG * Param.GC * (squirrel[1][j] - squirrel[j][j]) + " ");
                    }
                }
                System.out.println();
            }

            System.out.println("\n===================MODIFIKASI TUPAI TERBANG===================\n");

            System.out.println("\n===================CALCULATION UPDATE CONSTANTA===================\n");
            Double[] season = new Double[2];
//            Integer iterasi = 0;
            Integer a = -6;
            Double b = Param.MAX_ITER / 2.5;
//            if (iterasi <= 3) {
            Double sc1 = 0.0;
            for (Integer i = 0; i < D; i++) {
                for (Integer j = 0; j < i; j++) {
                    sc1 += (Math.pow(newsquirrel[i][j] - newsquirrel[1][j], 2));
                }
            }
            season[0] = Math.sqrt(sc1);
            //10e^-6/365^iterasi/max_iter/2.5
            season[1] = (Math.pow(Math.exp(10), a)) / (Math.pow(365, b));
//            }

            for (Double s : season) {
                System.out.println(s + " ");
            }
            System.out.println("\n===================CALCULATION UPDATE CONSTANTA===================\n");

            System.out.println("\n===================LEVY===================\n");
            Double[] u = new Double[D];
            Double[] v = new Double[D];
            Double[] s = new Double[D];
            Double[] l = new Double[D];
//        Double r = null;
            for (Integer i = 0; i < N; i++) {
                for (Integer j = 0; j < D; j++) {

                    u[j] = Math.random();
                    v[j] = Math.random();

                    Double num = gamma(1.0 + Param.LAMBDA) * Math.sin(Math.PI * Param.LAMBDA / 2); //pembilang
                    Double den = gamma((1.0 + Param.LAMBDA) / 2.0) * Param.LAMBDA * Math.pow(2.0, ((Param.LAMBDA - 1.0) / 2.0)); //penyebut
                    Double r = num / den;

                    s[j] = u[j] / (Math.pow(Math.abs(v[j]), Param.LAMBDA)); //U/|V|^1/2
                    l[j] = (Param.LAMBDA * gamma(Param.LAMBDA) * Math.sin(Math.PI * Param.LAMBDA / 2) / Math.PI) * 1 / (Math.pow(s[j], 1 + Param.LAMBDA)); //nilai levy
                    if (season[0] < season[1]) {
                        newsquirrel[i][j] = fsl + l[j] * (fsu - fsl);
                    } else {
                        newsquirrel[i][j] = newsquirrel[i][j];
                    }
                }
            }

            for (Integer i = 0; i < N; i++) {
                for (Integer j = 0; j < D; j++) {
                    System.out.print(newsquirrel[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("\n===================LEVY===================\n");

            System.out.println("\n===================UPDATE FS===================\n");
            for (Integer i = 0; i < N; i++) {
                for (Integer j = 0; j < D; j++) {
                    squirrel[i][j] = newsquirrel[i][j];
                }
            }

            for (Integer i = 0; i < N; i++) {
                for (Integer j = 0; j < D; j++) {
                    System.out.print(squirrel[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("\n===================UPDATE FS===================\n");

            System.out.println("\n===================NILAI FITNESS===================\n");

            
            for (int i = 0; i <= iter; i++) {
                iterasi[iter] = sum[1][i];
            }
            for (int i = 0; i <= iter; i++) {
                System.out.println(iterasi[i] + " ");
            }

            System.out.println("\n===================NILAI FITNESS===================\n");
        }

    }

    private void sort(Double[][] sum, Integer N) {
        int n = N;
        for (int i = 0; i < n - 1; i++) {
            int min_idx = i;
            for (int j = i + 1; j < n; j++) {
                if (sum[1][j] < sum[1][min_idx]) {
                    min_idx = j;
                }
            }

            Double temp0 = sum[0][min_idx];
            sum[0][min_idx] = sum[0][i];
            sum[0][i] = temp0;
            Double temp1 = sum[1][min_idx];
            sum[1][min_idx] = sum[1][i];
            sum[1][i] = temp1;
        }
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

}
