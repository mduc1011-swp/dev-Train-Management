/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Model;

/**
 *
 * @author phank
 */
public class Train extends Model {

    private String tcode;
    private String name;
    private String dstation;
    private String astation;
    private double dtime;
    private int seat;
    private int booked;
    private double atime;

    public Train(String tcode, String name, String dstation, String astation, double dtime, double atime, int seat, int booked) {
        this.tcode = tcode;
        this.name = name;
        this.dstation = dstation;
        this.astation = astation;
        this.dtime = dtime;
        this.seat = seat;
        this.booked = booked;
        this.atime = atime;
    }

    public Train() {
    }

    public String getTcode() {
        return tcode;
    }

    public void setTcode(String tcode) {
        this.tcode = tcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDstation() {
        return dstation;
    }

    public void setDstation(String dstation) {
        this.dstation = dstation;
    }

    public String getAstation() {
        return astation;
    }

    public void setAstation(String astation) {
        this.astation = astation;
    }

    public double getDtime() {
        return dtime;
    }

    public void setDtime(double dtime) {
        this.dtime = dtime;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public int getBooked() {
        return booked;
    }

    public void setBooked(int booked) {
        this.booked = booked;
    }

    public int getRemainSeat() {
        return getSeat() - getBooked();
    }

    public double getAtime() {
        return atime;
    }

    public void setAtime(double atime) {
        this.atime = atime;
    }

    @Override
    public String toString() {
        return "Train{" + "tcode=" + tcode + ", name=" + name + ", dstation=" + dstation + ", astation=" + astation + ", dtime=" + dtime + ", seat=" + seat + ", booked=" + booked + ", atime=" + atime + '}';
    }

    /**
     *
     * @return
     */
    @Override

public String getKey() {
    return this.tcode; // giả sử `tcode` là trường mã của `Train`
}
}
