package com.epam.ot.writer;

import com.epam.ot.entity.Gun;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class JaxbGunWriter implements GunWriter {
    @Override
    public void writeGun(File output, Gun gun) throws WriterException {
        try {
            JAXBContext context = JAXBContext.newInstance(Gun.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(gun, output);
        } catch (JAXBException e) {
            throw new WriterException(e);
        }
    }
}
