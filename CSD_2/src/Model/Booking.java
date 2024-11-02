/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author phank
 */
public class Booking {

    private String tcode;
    private String pcode;
    private Date odate;
    private Date paidDate;
    private int seat;
    private int state;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    public Booking(String tcode, String pcode, Date odate, Date paidDate, int seat, int state) {
        this.tcode = tcode;
        this.pcode = pcode;
        this.odate = odate;
        this.paidDate = paidDate;
        this.seat = seat;
        this.state = state;
    }

    public String getTcode() {
        return this.tcode;
    }

    public String getPcode() {
        return this.pcode;
    }

    public Date isPaid() {
        return this.paidDate = null;
    }

    public Date getPaidDate() {
        return this.paidDate;
    }

    public void setPaidDate(Date paidDate) {
        this.paidDate = paidDate;
    }

    public int getSeat() {
        return this.seat;
    }

    public Date getOdate() {
        return this.odate;
    }

    public void setOdate(Date odate) {
        this.odate = odate;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String paidStatus = (this.state == 0) ? "Not Paid" : "Paid on " + sdf.format(this.paidDate);
        return String.format("Booking - Train: %s | Passenger: %s | Date: %s | State: %s | Seats: %d",
                this.tcode, this.pcode, sdf.format(this.odate), paidStatus, this.seat);
    }

}
