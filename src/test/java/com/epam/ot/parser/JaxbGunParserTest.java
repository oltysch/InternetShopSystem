package com.epam.ot.parser;

import com.epam.ot.products.Gun;
import com.epam.ot.writer.GunWriter;
import com.epam.ot.writer.JaxbGunWriter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import static org.junit.Assert.*;

public class JaxbGunParserTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testParseGun() throws Exception {
        //TODO - split the tests and move implements upper
        InputStream input = getClass().getClassLoader().getResourceAsStream("gun.xml");
        GunParser parser = new JaxbGunParser();
        Gun gun = parser.parseGun(input);
        System.out.println(gun);
        File file = new File("123.xml");
        GunWriter writer = new JaxbGunWriter();
        Gun gun2 = new Gun("Assault Rifle", "AK-47", 20000.0, "USSR", "7.62x39mm", 30, 600, 800, 1500);
        writer.writeGun(file, gun2);
        Gun gun3 = parser.parseGun(new FileInputStream(file));
        System.out.println("\n" + gun3);
    }
}