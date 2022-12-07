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

    private Integer facility;
    private Integer location;

    public Integer getFacility() {
        return facility;
    }

    public void setFacility(Integer facility) {
        this.facility = facility;
    }

    public Integer getLocation() {
        return location;
    }

    public void setLocation(Integer location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "QuadraticAssignmentProblem{" + "facility=" + facility + ", location=" + location + '}';
    }
}
