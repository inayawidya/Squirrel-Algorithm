/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ssa;

import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class QuadraticAssignmentProblem {

    private static final Logger LOG = Logger.getLogger(QuadraticAssignmentProblem.class.getName());

    private Integer D;
    private Integer location;

    public Integer getDim() {
        return D;
    }

    public void setDim(Integer D) {
        this.D = D;
    }

    public Integer getLocation() {
        return location;
    }

    public void setLocation(Integer location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "QuadraticAssignmentProblem{" + "facility=" + D + ", location=" + location + '}';
    }
}