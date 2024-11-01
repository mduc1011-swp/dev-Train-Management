/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

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

    public Booking(String tcode, String pcode, int seat) {
        this.tcode = tcode;
        this.pcode = pcode;
        this.seat = seat;
        this.odate = new Date();
        this.paidDate = null;
    }

    public String getTcode() {
        return this.tcode;
    }

    public String getPcode() {
        return this.pcode;
    }

    // Check whether paidDate has been paid or not (if paidDate is different from null, it means paid)
    public boolean isPaid() {
        return this.paidDate != null;
    }

    public Date getPaidDate() {
        return this.paidDate;
    }

    // Setter for settlement date (set settlement date to current date)
    // Lối ngày trả bị lấy giá trị ngày mới khi load lại
    public void setPaidDate() {
        this.paidDate = new Date();
    }

    public int getSeat() {
        return this.seat;
    }

    public Date getOdate() {
        return this.odate;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String paidStatus = (this.paidDate == null) ? "Not Paid" : "Paid on " + sdf.format(this.paidDate);
        return String.format("Booking - Train: %s | Passenger: %s | Date: %s | Paid: %s | Seats: %d",
                this.tcode, this.pcode, sdf.format(this.odate), paidStatus, this.seat);
    }
}
