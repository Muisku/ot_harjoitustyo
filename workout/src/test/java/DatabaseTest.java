/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import dao.Database;
import java.io.IOException;
import java.sql.SQLException;
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
public class DatabaseTest {
    Database test;
   @Before
    public void setUp() throws IOException, ClassNotFoundException, Exception {  
        test = new Database("jdbc:sqlite:test.db");   
        test.init();   
    }
    
    @Test
    public void initReturnsTrueIfExecuted() throws SQLException {

        assertEquals(true, test.init());
    }
}
