package com.epam.ot.parser;

import com.epam.ot.products.Gun;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;

public class JaxbGunParser implements GunParser {

    @Override
    public Gun parseGun(InputStream input) {
        try {
            JAXBContext context = JAXBContext.newInstance(Gun.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Gun gun = (Gun) unmarshaller.unmarshal(input);
            return gun;
        } catch (JAXBException | IllegalArgumentException e) {
            throw new ParserException(e);
        }
    }
}
