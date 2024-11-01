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
    private int paidDate;
    private int seat;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    public Booking(String tcode, String pcode, int seat) {
        this.tcode = tcode;
        this.pcode = pcode;
        this.seat = seat;
        this.odate = new Date();
        this.paidDate = paidDate;
    }

    public String getTcode() {
        return this.tcode;
    }

    public String getPcode() {
        return this.pcode;
    }

    // Check whether paidDate has been paid or not (if paidDate is different from null, it means paid)
    public boolean isPaid() {
        return this.paidDate != 0;
    }

    public int getPaidDate() {
        return this.paidDate;
    }

    public void setPaidDate(int paidDate) {
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

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String paidStatus = (this.paidDate == 0) ? "Not Paid" : "Paid on " + sdf.format(this.paidDate);
        return String.format("Booking - Train: %s | Passenger: %s | Date: %s | Paid: %s | Seats: %d",
                this.tcode, this.pcode, sdf.format(this.odate), paidStatus, this.seat);
    }

}
