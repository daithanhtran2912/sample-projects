/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thanh.utils;

import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;


/**
 *
 * @author T.Z.B
 */
public class XmlValidatorHandler implements ValidationEventHandler{

    @Override
    public boolean handleEvent(ValidationEvent event) {
        return true;
    }
    
}
