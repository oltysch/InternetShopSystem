package com.epam.ot.dao;

import com.epam.ot.products.Gun;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcGunDao implements GunDao {
    //TODO split this into gun types
    public static final String FIND_ALL = "SELECT ID, GUNS.TYPE, MODEL, PRICE, ORIGIN, DESCRIPTION, CALIBER, MAGAZINE_CAPACITY, FIRE_RATE, FIRING_RANGE, EFFECTIVE_FIRING_RANGE FROM GUNS, GUNS_TTC WHERE GUNS_TTC.GUN_ID=GUNS.ID";
    public static final String FIND_BY_TYPE = "SELECT ID, GUNS.TYPE, MODEL, PRICE, ORIGIN, DESCRIPTION, CALIBER, MAGAZINE_CAPACITY, FIRE_RATE, FIRING_RANGE, EFFECTIVE_FIRING_RANGE FROM GUNS, GUNS_TTC WHERE GUNS.TYPE=? AND GUNS_TTC.GUN_ID=GUNS.ID";
    public static final String FIND_BY_MODEL = "SELECT ID, GUNS.TYPE, MODEL, PRICE, ORIGIN, DESCRIPTION, CALIBER, MAGAZINE_CAPACITY, FIRE_RATE, FIRING_RANGE, EFFECTIVE_FIRING_RANGE FROM GUNS, GUNS_TTC WHERE GUNS.MODEL=? AND GUNS_TTC.GUN_ID=GUNS.ID";
    public static final String FIND_BY_PRICE_RANGE = "SELECT ID, GUNS.TYPE, MODEL, PRICE, ORIGIN, DESCRIPTION, CALIBER, MAGAZINE_CAPACITY, FIRE_RATE, FIRING_RANGE, EFFECTIVE_FIRING_RANGE FROM GUNS, GUNS_TTC WHERE GUNS.PRICE>=? AND GUNS.PRICE<=? AND GUNS_TTC.GUN_ID=GUNS.ID";
    public static final String FIND_BY_ORIGIN = "SELECT ID, GUNS.TYPE, MODEL, PRICE, ORIGIN, DESCRIPTION, CALIBER, MAGAZINE_CAPACITY, FIRE_RATE, FIRING_RANGE, EFFECTIVE_FIRING_RANGE FROM GUNS, GUNS_TTC WHERE GUNS.ORIGIN=? AND GUNS_TTC.GUN_ID=GUNS.ID";
    public static final String FIND_BY_CALIBER = "SELECT ID, GUNS.TYPE, MODEL, PRICE, ORIGIN, DESCRIPTION, CALIBER, MAGAZINE_CAPACITY, FIRE_RATE, FIRING_RANGE, EFFECTIVE_FIRING_RANGE FROM GUNS, GUNS_TTC WHERE GUNS_TTC.CALIBER=? AND GUNS_TTC.GUN_ID=GUNS.ID";
    public static final String FIND_BY_FIRING_RANGE = "SELECT ID, GUNS.TYPE, MODEL, PRICE, ORIGIN, DESCRIPTION, CALIBER, MAGAZINE_CAPACITY, FIRE_RATE, FIRING_RANGE, EFFECTIVE_FIRING_RANGE FROM GUNS, GUNS_TTC WHERE GUNS_TTC.FIRING_RANGE>=? AND GUNS_TTC.FIRING_RANGE<=? AND GUNS_TTC.GUN_ID=GUNS.ID";
    public static final String FIND_BY_EFFECTIVE_FIRING_RANGE = "SELECT ID, GUNS.TYPE, MODEL, PRICE, ORIGIN, DESCRIPTION, CALIBER, MAGAZINE_CAPACITY, FIRE_RATE, FIRING_RANGE, EFFECTIVE_FIRING_RANGE FROM GUNS, GUNS_TTC WHERE GUNS_TTC.EFFECTIVE_FIRING_RANGE>=? AND GUNS_TTC.EFFECTIVE_FIRING_RANGE<=? AND GUNS_TTC.GUN_ID=GUNS.ID";
    public static final String FIND_BY_MAGAZINE_CAPACITY = "SELECT ID, GUNS.TYPE, MODEL, PRICE, ORIGIN, DESCRIPTION, CALIBER, MAGAZINE_CAPACITY, FIRE_RATE, FIRING_RANGE, EFFECTIVE_FIRING_RANGE FROM GUNS, GUNS_TTC WHERE GUNS_TTC.MAGAZINE_CAPACITY>=? AND GUNS_TTC.MAGAZINE_CAPACITY<=? AND GUNS_TTC.GUN_ID=GUNS.ID";
    public static final String FIND_BY_FIRE_RATE = "SELECT ID, GUNS.TYPE, MODEL, PRICE, ORIGIN, DESCRIPTION, CALIBER, MAGAZINE_CAPACITY, FIRE_RATE, FIRING_RANGE, EFFECTIVE_FIRING_RANGE FROM GUNS, GUNS_TTC WHERE GUNS_TTC.FIRE_RATE>=? AND GUNS_TTC.FIRE_RATE<=? AND GUNS_TTC.GUN_ID=GUNS.ID";
    public static final String FIND_BY_ID = "SELECT * FROM GUNS, GUNS_TTC WHERE id = ? AND GUN_ID=ID";
    public static final String INSERT_INTO_GUNS = "INSERT INTO GUNS VALUES (?, ?, ?, ?, ?, ?);INSERT INTO GUNS_TTC VALUES (?, ?, ?, ?, ?, ?)";
    public static final String REMOVE_GUN_BY_ID = "DELETE FROM GUNS_TTC WHERE GUN_ID=?; DELETE FROM GUNS WHERE ID=?";
    //    public static final String UPDATE_GUN = "UPDATE GUNS SET VALUES (DEFAULT, ?, ?, ?, ?, ?) WHERE ID = ?;UPDATE GUNS_TTC SET VALUES (DEFAULT, ?, ?, ?, ?, ?) WHERE GUN_ID = ?";
    public static final String UPDATE_GUN = "UPDATE GUNS SET TYPE=?, MODEL=?, PRICE=?, ORIGIN=?, DESCRIPTION=? WHERE ID = ?;UPDATE GUNS_TTC SET FIRING_RANGE=?, EFFECTIVE_FIRING_RANGE=?, MAGAZINE_CAPACITY=?, CALIBER=?, FIRE_RATE=?) WHERE GUN_ID = ?";
    private final Connection connection;

    //TODO remake JdbcGunDao
    public JdbcGunDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Gun findById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean found = resultSet.next();
            if (found) {
                Gun gun = new Gun(resultSet.getString(2), resultSet.getString(3), resultSet.getDouble(4), resultSet.getString(5), resultSet.getString(11), resultSet.getInt(10), resultSet.getInt(12), resultSet.getInt(8), resultSet.getInt(9));
                gun.setId(resultSet.getInt(1));
                gun.setDescription(resultSet.getString(6));
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
                Gun gun = new Gun(resultSet.getString(2), resultSet.getString(3), resultSet.getDouble(4), resultSet.getString(5), resultSet.getString(7), resultSet.getInt(8), resultSet.getInt(9), resultSet.getInt(10), resultSet.getInt(11));
                gun.setId(resultSet.getInt(1));
                gun.setDescription(resultSet.getString(6));
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
                Gun gun = new Gun(resultSet.getString(2), resultSet.getString(3), resultSet.getDouble(4), resultSet.getString(5), resultSet.getString(7), resultSet.getInt(8), resultSet.getInt(9), resultSet.getInt(10), resultSet.getInt(11));
                gun.setId(resultSet.getInt(1));
                gun.setDescription(resultSet.getString(6));
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
                Gun gun = new Gun(resultSet.getString(2), resultSet.getString(3), resultSet.getDouble(4), resultSet.getString(5), resultSet.getString(7), resultSet.getInt(8), resultSet.getInt(9), resultSet.getInt(10), resultSet.getInt(11));
                gun.setId(resultSet.getInt(1));
                gun.setDescription(resultSet.getString(6));
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
                Gun gun = new Gun(resultSet.getString(2), resultSet.getString(3), resultSet.getDouble(4), resultSet.getString(5), resultSet.getString(7), resultSet.getInt(8), resultSet.getInt(9), resultSet.getInt(10), resultSet.getInt(11));
                gun.setId(resultSet.getInt(1));
                gun.setDescription(resultSet.getString(6));
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
                Gun gun = new Gun(resultSet.getString(2), resultSet.getString(3), resultSet.getDouble(4), resultSet.getString(5), resultSet.getString(7), resultSet.getInt(8), resultSet.getInt(9), resultSet.getInt(10), resultSet.getInt(11));
                gun.setId(resultSet.getInt(1));
                gun.setDescription(resultSet.getString(6));
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
                Gun gun = new Gun(resultSet.getString(2), resultSet.getString(3), resultSet.getDouble(4), resultSet.getString(5), resultSet.getString(7), resultSet.getInt(8), resultSet.getInt(9), resultSet.getInt(10), resultSet.getInt(11));
                gun.setId(resultSet.getInt(1));
                gun.setDescription(resultSet.getString(6));
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
                Gun gun = new Gun(resultSet.getString(2), resultSet.getString(3), resultSet.getDouble(4), resultSet.getString(5), resultSet.getString(7), resultSet.getInt(8), resultSet.getInt(9), resultSet.getInt(10), resultSet.getInt(11));
                gun.setId(resultSet.getInt(1));
                gun.setDescription(resultSet.getString(6));
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
                Gun gun = new Gun(resultSet.getString(2), resultSet.getString(3), resultSet.getDouble(4), resultSet.getString(5), resultSet.getString(7), resultSet.getInt(8), resultSet.getInt(9), resultSet.getInt(10), resultSet.getInt(11));
                gun.setId(resultSet.getInt(1));
                gun.setDescription(resultSet.getString(6));
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
                Gun gun = new Gun(resultSet.getString(2), resultSet.getString(3), resultSet.getDouble(4), resultSet.getString(5), resultSet.getString(7), resultSet.getInt(8), resultSet.getInt(9), resultSet.getInt(10), resultSet.getInt(11));
                gun.setId(resultSet.getInt(1));
                gun.setDescription(resultSet.getString(6));
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
                Gun gun = new Gun(resultSet.getString(2), resultSet.getString(3), resultSet.getDouble(4), resultSet.getString(5), resultSet.getString(7), resultSet.getInt(8), resultSet.getInt(9), resultSet.getInt(10), resultSet.getInt(11));
                gun.setId(resultSet.getInt(1));
                gun.setDescription(resultSet.getString(6));
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
            preparedStatement.setInt(6, gun.getId());
            preparedStatement.setInt(7, gun.getTtc().getFiringRange());
            preparedStatement.setInt(8, gun.getTtc().getEffectiveFiringRange());
            preparedStatement.setInt(9, gun.getTtc().getMagazineCapacity());
            preparedStatement.setString(10, gun.getTtc().getCaliber());
            preparedStatement.setInt(11, gun.getTtc().getFireRate());
            preparedStatement.setInt(12, gun.getId());
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
            preparedStatement.setInt(1, gun.getId());
            preparedStatement.setString(2, gun.getType());
            preparedStatement.setString(3, gun.getModel());
            preparedStatement.setDouble(4, gun.getPrice());
            preparedStatement.setString(5, gun.getOrigin());
            preparedStatement.setString(6, gun.getDescription());
//            TODO insert TTCS
//            preparedStatement.setString(6, gun.getId());
            preparedStatement.setString(7, gun.getOrigin());
            preparedStatement.setString(8, gun.getOrigin());
            preparedStatement.setString(9, gun.getOrigin());
            preparedStatement.setString(10, gun.getOrigin());
            preparedStatement.setString(11, gun.getOrigin());
//            TODO make description
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
    public boolean removeById(int id) {
        boolean res = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_GUN_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, id);
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
