package Entity;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author ASUS
 */
public class Passenger {
    private String pcode;
    private String name;
    private String phone;

    public Passenger() {
    }

    public Passenger(String pcode, String name, String phone) {
        this.pcode = pcode;
        this.name = name;
        this.phone = phone;
    }

    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (phone.matches("\\d+")){ //xác định sđt chỉ chứa các chữ số
            this.phone = phone;
        } else {
            throw new IllegalArgumentException("Phone is digit");
        }    
    }

    @Override
    public String toString() {
        return "Pcode: " + pcode + ", Name: " + name + ", Phone: " + phone + '.';
    }
    
    public int compareTo(Passenger other) {
        return this.pcode.compareTo(other.pcode);
    }
}
