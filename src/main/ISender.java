/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author Alessandro
 */
public interface ISender {
    public void sendStopSignal();
    
    public void sendMessage(String message);
}