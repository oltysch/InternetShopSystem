package com.epam.ot.entity;

import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class BulletTest {
    Bullet bullet;
    ProductBlock block;

    @Test
    public void testToBlockWithNormalBullet() throws Exception {
        bullet = new Bullet("7.62x64", "BEAR BROWN", "Incendiary", 120.0);
        int descriptionLength = 150;
        bullet.setDescription(TestUtils.generateString(descriptionLength));
        UUID uuid = UUID.randomUUID();
        bullet.setUuid(uuid);
        bullet.setQty(300);

        block = bullet.toBlock();
        TestUtils.showBlock(block);

        assertNotNull(block.getName());
        assertNotNull(block.getPrice());
        assertEquals(bullet.getPrice(), block.getPrice());
        assertEquals("bullet", block.getProductType());
        assertEquals(uuid, block.getUuid());
        assertEquals(3, block.getCharacteristics().size());
        assertNotNull(block.getShortDescription());
        assertNotNull(block.getFullDescription());
        assertEquals(bullet.getDescription(), block.getFullDescription());
        assertTrue(block.getShortDescription().length() > 90);
        assertTrue(block.getShortDescription().length() < 110);
    }

    @Test
    public void testToBlockWithShortDescription() throws Exception {
        bullet = new Bullet();
        int descriptionLength = 30;
        bullet.setDescription(TestUtils.generateString(descriptionLength));

        block = bullet.toBlock();
        TestUtils.showBlock(block);

        assertNotNull(block.getShortDescription());
        assertNotNull(block.getFullDescription());
        assertEquals(descriptionLength, block.getFullDescription().length());
        assertEquals(descriptionLength, block.getShortDescription().length());
    }

    @Test
    public void testToBlockWithoutDescription() throws Exception {
        bullet = new Bullet();
        bullet.setDescription("");

        block = bullet.toBlock();
        TestUtils.showBlock(block);

        assertNotNull(block.getShortDescription());
        assertNotNull(block.getFullDescription());
        assertEquals("", block.getShortDescription());
        assertEquals("", block.getFullDescription());
    }

    @Test
    public void testToBlockWithNullDescription() throws Exception {
        bullet = new Bullet();

        block = bullet.toBlock();
        TestUtils.showBlock(block);

        assertNotNull(block.getShortDescription());
        assertNotNull(block.getFullDescription());
        assertEquals("", block.getShortDescription());
        assertEquals("", block.getFullDescription());
    }

    @Test
    public void testToBlockWithZeroQty() throws Exception {
        bullet = new Bullet();
        bullet.setQty(0);

        block = bullet.toBlock();
        TestUtils.showBlock(block);

        assertEquals(0, block.getCharacteristics().size());
    }

    @Test
    public void testToBlockWithNullQty() throws Exception {
        bullet = new Bullet();
        bullet.setQty(null);

        block = bullet.toBlock();
        TestUtils.showBlock(block);

        assertEquals(0, block.getCharacteristics().size());
    }
}