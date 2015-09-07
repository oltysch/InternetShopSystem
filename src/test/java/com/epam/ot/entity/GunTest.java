package com.epam.ot.entity;

import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class GunTest {
    Gun gun;
    ProductBlock block;

    @Test
    public void testToBlockWithNormalBullet() throws Exception {
        gun = new Gun("pistol", "Five Seven", 120.0);
        int descriptionLength = 150;
        gun.setDescription(TestUtils.generateString(descriptionLength));
        UUID uuid = UUID.randomUUID();
        gun.setUuid(uuid);

        block = gun.toBlock();
        TestUtils.showBlock(block);

        assertNotNull(block.getName());
        assertNotNull(block.getPrice());
        assertEquals(gun.getPrice(), block.getPrice());
        assertEquals("gun", block.getProductType());
        assertEquals(uuid, block.getUuid());
        assertEquals(1, block.getCharacteristics().size());
        assertNotNull(block.getShortDescription());
        assertNotNull(block.getFullDescription());
        assertEquals(gun.getDescription(), block.getFullDescription());
        assertEquals(gun.getDescription(), block.getFullDescription());
        assertTrue(block.getShortDescription().length() > 90);
        assertTrue(block.getShortDescription().length() < 110);
    }

    @Test
    public void testToBlockWithShortDescription() throws Exception {
        gun = new Gun();
        int descriptionLength = 30;
        gun.setDescription(TestUtils.generateString(descriptionLength));

        block = gun.toBlock();
        TestUtils.showBlock(block);

        assertNotNull(block.getShortDescription());
        assertNotNull(block.getFullDescription());
        assertTrue(block.getFullDescription().length() == descriptionLength);
        assertTrue(block.getShortDescription().length() == descriptionLength);
    }

    @Test
    public void testToBlockWithoutDescription() throws Exception {
        gun = new Gun();
        gun.setDescription("");

        block = gun.toBlock();
        TestUtils.showBlock(block);

        assertNotNull(block.getShortDescription());
        assertNotNull(block.getFullDescription());
        assertEquals("", block.getShortDescription());
        assertEquals("", block.getFullDescription());
    }

    @Test
    public void testToBlockWithNullDescription() throws Exception {
        gun = new Gun();

        block = gun.toBlock();
        TestUtils.showBlock(block);

        assertNotNull(block.getShortDescription());
        assertNotNull(block.getFullDescription());
        assertEquals("", block.getShortDescription());
        assertEquals("", block.getFullDescription());
    }

    @Test
    public void testToBlockWithZeroCharacteristics() throws Exception {
        gun = new Gun();
        gun.setOrigin("");
        gun.setType("");
        gun.setFiringRange(0);
        gun.setEffectiveFiringRange(0);
        gun.setMagazineCapacity(0);
        gun.setCaliber("");
        gun.setFireRate(0);

        block = gun.toBlock();
        TestUtils.showBlock(block);

        assertEquals(0, block.getCharacteristics().size());
    }

    @Test
    public void testToBlockWithNullCharacteristics() throws Exception {
        gun = new Gun();

        block = gun.toBlock();
        TestUtils.showBlock(block);

        assertEquals(0, block.getCharacteristics().size());
    }
}