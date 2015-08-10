package com.epam.ot.dao;

import com.epam.ot.products.Gun;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JdbcGunDao implements GunDao {
    //TODO split this into gun types
    public static final String FIND_ALL = "SELECT ID, GUNS.UUID, GUNS.TYPE, MODEL, PRICE, ORIGIN, DESCRIPTION, CALIBER, MAGAZINE_CAPACITY, FIRE_RATE, FIRING_RANGE, EFFECTIVE_FIRING_RANGE FROM GUNS, GUNS_TTC WHERE GUNS_TTC.GUN_UUID=GUNS.UUID";
    public static final String FIND_BY_TYPE = "SELECT ID, GUNS.UUID, GUNS.TYPE, MODEL, PRICE, ORIGIN, DESCRIPTION, CALIBER, MAGAZINE_CAPACITY, FIRE_RATE, FIRING_RANGE, EFFECTIVE_FIRING_RANGE FROM GUNS, GUNS_TTC WHERE GUNS.TYPE=? AND GUNS_TTC.GUN_UUID=GUNS.UUID";
    public static final String FIND_BY_MODEL = "SELECT ID, GUNS.UUID, GUNS.TYPE, MODEL, PRICE, ORIGIN, DESCRIPTION, CALIBER, MAGAZINE_CAPACITY, FIRE_RATE, FIRING_RANGE, EFFECTIVE_FIRING_RANGE FROM GUNS, GUNS_TTC WHERE GUNS.MODEL=? AND GUNS_TTC.GUN_UUID=GUNS.UUID";
    public static final String FIND_BY_PRICE_RANGE = "SELECT ID, GUNS.UUID, GUNS.TYPE, MODEL, PRICE, ORIGIN, DESCRIPTION, CALIBER, MAGAZINE_CAPACITY, FIRE_RATE, FIRING_RANGE, EFFECTIVE_FIRING_RANGE FROM GUNS, GUNS_TTC WHERE GUNS.PRICE>=? AND GUNS.PRICE<=? AND GUNS_TTC.GUN_UUID=GUNS.UUID";
    public static final String FIND_BY_ORIGIN = "SELECT ID, GUNS.UUID, GUNS.TYPE, MODEL, PRICE, ORIGIN, DESCRIPTION, CALIBER, MAGAZINE_CAPACITY, FIRE_RATE, FIRING_RANGE, EFFECTIVE_FIRING_RANGE FROM GUNS, GUNS_TTC WHERE GUNS.ORIGIN=? AND GUNS_TTC.GUN_UUID=GUNS.UUID";
    public static final String FIND_BY_CALIBER = "SELECT ID, GUNS.UUID, GUNS.TYPE, MODEL, PRICE, ORIGIN, DESCRIPTION, CALIBER, MAGAZINE_CAPACITY, FIRE_RATE, FIRING_RANGE, EFFECTIVE_FIRING_RANGE FROM GUNS, GUNS_TTC WHERE GUNS_TTC.CALIBER=? AND GUNS_TTC.GUN_UUID=GUNS.UUID";
    public static final String FIND_BY_FIRING_RANGE = "SELECT ID, GUNS.UUID, GUNS.TYPE, MODEL, PRICE, ORIGIN, DESCRIPTION, CALIBER, MAGAZINE_CAPACITY, FIRE_RATE, FIRING_RANGE, EFFECTIVE_FIRING_RANGE FROM GUNS, GUNS_TTC WHERE GUNS_TTC.FIRING_RANGE>=? AND GUNS_TTC.FIRING_RANGE<=? AND GUNS_TTC.GUN_UUID=GUNS.UUID";
    public static final String FIND_BY_EFFECTIVE_FIRING_RANGE = "SELECT ID, GUNS.UUID, GUNS.TYPE, MODEL, PRICE, ORIGIN, DESCRIPTION, CALIBER, MAGAZINE_CAPACITY, FIRE_RATE, FIRING_RANGE, EFFECTIVE_FIRING_RANGE FROM GUNS, GUNS_TTC WHERE GUNS_TTC.EFFECTIVE_FIRING_RANGE>=? AND GUNS_TTC.EFFECTIVE_FIRING_RANGE<=? AND GUNS_TTC.GUN_UUID=GUNS.UUID";
    public static final String FIND_BY_MAGAZINE_CAPACITY = "SELECT ID, GUNS.UUID, GUNS.TYPE, MODEL, PRICE, ORIGIN, DESCRIPTION, CALIBER, MAGAZINE_CAPACITY, FIRE_RATE, FIRING_RANGE, EFFECTIVE_FIRING_RANGE FROM GUNS, GUNS_TTC WHERE GUNS_TTC.MAGAZINE_CAPACITY>=? AND GUNS_TTC.MAGAZINE_CAPACITY<=? AND GUNS_TTC.GUN_UUID=GUNS.UUID";
    public static final String FIND_BY_FIRE_RATE = "SELECT ID, GUNS.UUID, GUNS.TYPE, MODEL, PRICE, ORIGIN, DESCRIPTION, CALIBER, MAGAZINE_CAPACITY, FIRE_RATE, FIRING_RANGE, EFFECTIVE_FIRING_RANGE FROM GUNS, GUNS_TTC WHERE GUNS_TTC.FIRE_RATE>=? AND GUNS_TTC.FIRE_RATE<=? AND GUNS_TTC.GUN_UUID=GUNS.UUID";
    public static final String FIND_BY_ID = "SELECT * FROM GUNS, GUNS_TTC WHERE id = ? AND GUN_UUID=GUNS.UUID";
    public static final String FIND_BY_UUID = "SELECT * FROM GUNS, GUNS_TTC WHERE GUNS.UUID = ? AND GUN_UUID=GUNS.UUID";

    public static final String INSERT_INTO_GUNS = "INSERT INTO GUNS VALUES (DEFAULT, ?, ?, ?, ?, ?, ?);INSERT INTO GUNS_TTC VALUES (?, ?, ?, ?, ?, ?)";

    //    public static final String REMOVE_GUN_BY_ID = "DELETE FROM GUNS_TTC WHERE GUN_UUID=?; DELETE FROM GUNS WHERE GUNS.UUID=?";
    public static final String REMOVE_GUN_BY_UUID = "DELETE FROM GUNS_TTC WHERE GUN_UUID=?; DELETE FROM GUNS WHERE GUNS.UUID=?";

    public static final String UPDATE_GUN = "UPDATE GUNS SET TYPE=?, MODEL=?, PRICE=?, ORIGIN=?, DESCRIPTION=? WHERE GUNS.UUID = ?;UPDATE GUNS_TTC SET FIRING_RANGE=?, EFFECTIVE_FIRING_RANGE=?, MAGAZINE_CAPACITY=?, CALIBER=?, FIRE_RATE=? WHERE GUN_UUID = ?";

    private final Connection connection;

    public JdbcGunDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Gun findById(long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean found = resultSet.next();
            if (found) {
                Gun gun = new Gun(resultSet.getString(3), resultSet.getString(4), resultSet.getDouble(5), resultSet.getString(6), resultSet.getString(12), resultSet.getInt(11), resultSet.getInt(13), resultSet.getInt(9), resultSet.getInt(10));
                gun.setId(resultSet.getInt(1));
                gun.setUuid((UUID) resultSet.getObject(2));
                gun.setDescription(resultSet.getString(7));
                return gun;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }
    }

    @Override
    public Gun findByUuid(UUID uuid) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_UUID);
            preparedStatement.setObject(1, uuid);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean found = resultSet.next();
            if (found) {
                Gun gun = new Gun(resultSet.getString(3), resultSet.getString(4), resultSet.getDouble(5), resultSet.getString(6), resultSet.getString(12), resultSet.getInt(11), resultSet.getInt(13), resultSet.getInt(9), resultSet.getInt(10));
                gun.setId(resultSet.getInt(1));
                gun.setUuid((UUID) resultSet.getObject(2));
                gun.setDescription(resultSet.getString(7));
                return gun;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }
    }

    @Override
    public List<Gun> findAll() {
        List<Gun> guns = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Gun gun = new Gun(resultSet.getString(3), resultSet.getString(4), resultSet.getDouble(5), resultSet.getString(6), resultSet.getString(8), resultSet.getInt(9), resultSet.getInt(10), resultSet.getInt(11), resultSet.getInt(12));
                gun.setId(resultSet.getInt(1));
                gun.setUuid((UUID) resultSet.getObject(2));
                gun.setDescription(resultSet.getString(7));
                guns.add(gun);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }
        return guns;
    }

    @Override
    public List<Gun> findByType(String type) {
        List<Gun> guns = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_TYPE);
            preparedStatement.setString(1, type);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Gun gun = new Gun(resultSet.getString(3), resultSet.getString(4), resultSet.getDouble(5), resultSet.getString(6), resultSet.getString(8), resultSet.getInt(9), resultSet.getInt(10), resultSet.getInt(11), resultSet.getInt(12));
                gun.setId(resultSet.getInt(1));
                gun.setUuid((UUID) resultSet.getObject(2));
                gun.setDescription(resultSet.getString(7));
                guns.add(gun);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }
        return guns;
    }

    @Override
    public List<Gun> findByModel(String model) {
        List<Gun> guns = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_MODEL);
            preparedStatement.setString(1, model);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Gun gun = new Gun(resultSet.getString(3), resultSet.getString(4), resultSet.getDouble(5), resultSet.getString(6), resultSet.getString(8), resultSet.getInt(9), resultSet.getInt(10), resultSet.getInt(11), resultSet.getInt(12));
                gun.setId(resultSet.getInt(1));
                gun.setUuid((UUID) resultSet.getObject(2));
                gun.setDescription(resultSet.getString(7));
                guns.add(gun);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }
        return guns;
    }

    @Override
    public List<Gun> findByPriceRange(int min, int max) {
        List<Gun> guns = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_PRICE_RANGE);
            preparedStatement.setInt(1, min);
            preparedStatement.setInt(2, max);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Gun gun = new Gun(resultSet.getString(3), resultSet.getString(4), resultSet.getDouble(5), resultSet.getString(6), resultSet.getString(8), resultSet.getInt(9), resultSet.getInt(10), resultSet.getInt(11), resultSet.getInt(12));
                gun.setId(resultSet.getInt(1));
                gun.setUuid((UUID) resultSet.getObject(2));
                gun.setDescription(resultSet.getString(7));
                guns.add(gun);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }
        return guns;
    }

    @Override
    public List<Gun> findByOrigin(String origin) {
        List<Gun> guns = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ORIGIN);
            preparedStatement.setString(1, origin);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Gun gun = new Gun(resultSet.getString(3), resultSet.getString(4), resultSet.getDouble(5), resultSet.getString(6), resultSet.getString(8), resultSet.getInt(9), resultSet.getInt(10), resultSet.getInt(11), resultSet.getInt(12));
                gun.setId(resultSet.getInt(1));
                gun.setUuid((UUID) resultSet.getObject(2));
                gun.setDescription(resultSet.getString(7));
                guns.add(gun);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }
        return guns;
    }

    @Override
    public List<Gun> findByFiringRange(int min, int max) {
        List<Gun> guns = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_FIRING_RANGE);
            preparedStatement.setInt(1, min);
            preparedStatement.setInt(2, max);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Gun gun = new Gun(resultSet.getString(3), resultSet.getString(4), resultSet.getDouble(5), resultSet.getString(6), resultSet.getString(8), resultSet.getInt(9), resultSet.getInt(10), resultSet.getInt(11), resultSet.getInt(12));
                gun.setId(resultSet.getInt(1));
                gun.setUuid((UUID) resultSet.getObject(2));
                gun.setDescription(resultSet.getString(7));
                guns.add(gun);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }
        return guns;
    }

    @Override
    public List<Gun> findByEffectiveFiringRange(int min, int max) {
        List<Gun> guns = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_EFFECTIVE_FIRING_RANGE);
            preparedStatement.setInt(1, min);
            preparedStatement.setInt(2, max);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Gun gun = new Gun(resultSet.getString(3), resultSet.getString(4), resultSet.getDouble(5), resultSet.getString(6), resultSet.getString(8), resultSet.getInt(9), resultSet.getInt(10), resultSet.getInt(11), resultSet.getInt(12));
                gun.setId(resultSet.getInt(1));
                gun.setUuid((UUID) resultSet.getObject(2));
                gun.setDescription(resultSet.getString(7));
                guns.add(gun);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }
        return guns;
    }

    @Override
    public List<Gun> findByMagazineCapacity(int min, int max) {
        List<Gun> guns = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_MAGAZINE_CAPACITY);
            preparedStatement.setInt(1, min);
            preparedStatement.setInt(2, max);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Gun gun = new Gun(resultSet.getString(3), resultSet.getString(4), resultSet.getDouble(5), resultSet.getString(6), resultSet.getString(8), resultSet.getInt(9), resultSet.getInt(10), resultSet.getInt(11), resultSet.getInt(12));
                gun.setId(resultSet.getInt(1));
                gun.setUuid((UUID) resultSet.getObject(2));
                gun.setDescription(resultSet.getString(7));
                guns.add(gun);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }
        return guns;
    }

    @Override
    public List<Gun> findByCaliber(String caliber) {
        List<Gun> guns = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_CALIBER);
            preparedStatement.setString(1, caliber);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Gun gun = new Gun(resultSet.getString(3), resultSet.getString(4), resultSet.getDouble(5), resultSet.getString(6), resultSet.getString(8), resultSet.getInt(9), resultSet.getInt(10), resultSet.getInt(11), resultSet.getInt(12));
                gun.setId(resultSet.getInt(1));
                gun.setUuid((UUID) resultSet.getObject(2));
                gun.setDescription(resultSet.getString(7));
                guns.add(gun);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }
        return guns;
    }

    @Override
    public List<Gun> findByFireRate(int min, int max) {
        List<Gun> guns = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_FIRE_RATE);
            preparedStatement.setInt(1, min);
            preparedStatement.setInt(2, max);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Gun gun = new Gun(resultSet.getString(3), resultSet.getString(4), resultSet.getDouble(5), resultSet.getString(6), resultSet.getString(8), resultSet.getInt(9), resultSet.getInt(10), resultSet.getInt(11), resultSet.getInt(12));
                gun.setId(resultSet.getInt(1));
                gun.setUuid((UUID) resultSet.getObject(2));
                gun.setDescription(resultSet.getString(7));
                guns.add(gun);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }
        return guns;
    }

    @Override
    public void update(Gun gun) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_GUN);
            preparedStatement.setString(1, gun.getType());
            preparedStatement.setString(2, gun.getModel());
            preparedStatement.setDouble(3, gun.getPrice());
            preparedStatement.setString(4, gun.getOrigin());
            preparedStatement.setString(5, gun.getDescription());
            preparedStatement.setObject(6, gun.getUuid());
            preparedStatement.setInt(7, gun.getTtc().getFiringRange());
            preparedStatement.setInt(8, gun.getTtc().getEffectiveFiringRange());
            preparedStatement.setInt(9, gun.getTtc().getMagazineCapacity());
            preparedStatement.setString(10, gun.getTtc().getCaliber());
            preparedStatement.setInt(11, gun.getTtc().getFireRate());
            preparedStatement.setObject(12, gun.getUuid());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }
    }

    @Override
    public void insert(Gun gun) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_GUNS);
            preparedStatement.setObject(1, gun.getUuid());
            preparedStatement.setString(2, gun.getType());
            preparedStatement.setString(3, gun.getModel());
            preparedStatement.setDouble(4, gun.getPrice());
            preparedStatement.setString(5, gun.getOrigin());
            preparedStatement.setString(6, gun.getDescription());
            preparedStatement.setObject(7, gun.getUuid());
            preparedStatement.setInt(8, gun.getTtc().getFiringRange());
            preparedStatement.setInt(9, gun.getTtc().getEffectiveFiringRange());
            preparedStatement.setInt(10, gun.getTtc().getMagazineCapacity());
            preparedStatement.setString(11, gun.getTtc().getCaliber());
            preparedStatement.setInt(12, gun.getTtc().getFireRate());
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }
    }

//    @Override
//    public boolean removeById(long id) {
//        boolean res = false;
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_GUN_BY_ID);
//            preparedStatement.setLong(1, id);
//            preparedStatement.setLong(2, id);
//            preparedStatement.executeUpdate();
//            res = true;
//        } catch (SQLException e) {
//            throw new DaoException(e);
//        } finally {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                throw new DaoException(e);
//            }
//        }
//        return res;
//    }

    @Override
    public boolean removeByUuid(UUID uuid) {
        boolean res = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_GUN_BY_UUID);
            preparedStatement.setObject(1, uuid);
            preparedStatement.setObject(2, uuid);
            preparedStatement.executeUpdate();
            res = true;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }
        return res;
    }
}
