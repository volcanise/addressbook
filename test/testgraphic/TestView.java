/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgraphic;

import view.AddressBookFrame;

/**
 *
 * @author shahin.behrooz@gmail.com
 */
public class TestView {
    public static void main(String[] args) {
        AddressBookFrame frame = new AddressBookFrame();
        frame.setTitle("Listes des contacts");
        frame.setSize(790,320);
        frame.setVisible(true);
    }
}
