package code.model.dao;

import code.model.pojo.StorageUnit;

import java.util.HashSet;

/**
 * Created by admin on 22.04.2017.
 */
public interface StorageUnitDAO {
    HashSet<StorageUnit> getAllStorageUnits();
    StorageUnit getStorageUnitByISN(String isn);
    void delStorageUnitByISN(String isn);
}
