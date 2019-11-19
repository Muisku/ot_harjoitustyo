/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import dao.Database;
import dao.UserDataDao;
import domain.User;
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
public class userTest {
    
    @Test
    public void Constructor() {
        User user = new User("William");
        assertEquals("William", user.getName());
    }
}
