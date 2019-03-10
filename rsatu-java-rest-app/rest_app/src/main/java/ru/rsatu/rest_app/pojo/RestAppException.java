/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.rsatu.rest_app.pojo;

/**
 *
 * @author npetrov
 */
public class RestAppException extends Exception {

    /**
     * Creates a new instance of <code>RestAppException</code> without detail
     * message.
     */
    public RestAppException() {
    }

    /**
     * Constructs an instance of <code>RestAppException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public RestAppException(String msg) {
        super(msg);
    }
}
