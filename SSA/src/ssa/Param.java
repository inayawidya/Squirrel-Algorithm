/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ssa;

/**
 *
 * @author User
 */
// inisialisasi parameter 
public interface Param {
    
    static final Integer N = 10;// banyak tupai = vm baris
    static final Integer D = 6; //dimensi sbg kolom
    static final Double PDP = 0.1; //probabilitas predator
    static final Double DG = 0.6; // jarak meluncur
    static final Double GC = 1.9; // konstanta meluncur
    static final Integer MAX_ITER = 3; // jumlah maks iterasi
    static final Double LAMBDA = 1.5; // nilai lambda
    static final Integer FSU = 1; // batas atas
    static final Integer FS1 = -1; // batas bawah
}
