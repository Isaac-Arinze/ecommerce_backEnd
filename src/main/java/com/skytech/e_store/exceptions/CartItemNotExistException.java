package com.skytech.e_store.exceptions;

public class CartItemNotExistException extends Exception{
    public CartItemNotExistException (String msg){
        super(msg);
    }
}
