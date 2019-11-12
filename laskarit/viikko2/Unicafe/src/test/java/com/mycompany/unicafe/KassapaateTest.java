/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author muisku
 */
public class KassapaateTest {
    
   Kassapaate kassa;
   Maksukortti kortti2;
   
   @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti2 = new Maksukortti(240);
    }

   
    @Test
    public void KortillaOnRahaaEdulliseen() {
        
        int s = kortti2.saldo();
       
        if (s == 240 || s > 240) {
            kortti2.otaRahaa(240);
    }

  
}
   @Test
    public void rahaKasvaa() {
        kassa.syoEdullisesti(240);
        assertEquals(100240, kassa.kassassaRahaa());
      
    }
    @Test
    public void rahaMaara() {
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
   @Test
    public void myydyt() {
        kassa.syoEdullisesti(240);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
      
    }
   
    
    @Test
    public void MaksuEiRiittava() {
        kassa.syoEdullisesti(230);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
        assertEquals(100000, kassa.kassassaRahaa());
      
    }
   

  
}
