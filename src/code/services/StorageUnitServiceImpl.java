package code.services;

import code.model.dao.StorageUnitDAO;
import code.model.dao.StorageUnitDAOImpl;
import code.model.pojo.StorageUnit;
import org.apache.log4j.Logger;

import java.util.HashSet;

/**
 * Created by admin on 22.04.2017.
 */
public class StorageUnitServiceImpl implements StorageUnitService {

    private static StorageUnitDAO storageUnitDAO = new StorageUnitDAOImpl();

    @Override
    public HashSet<StorageUnit> getAllStorageUnits(){
        HashSet<StorageUnit> storageUnits = storageUnitDAO.getAllStorageUnits();
        return storageUnits;
    }

    @Override
    public StorageUnit getStorageUnitByISN(String isn){
        StorageUnit storageUnit = storageUnitDAO.getStorageUnitByISN(isn);
        return storageUnit;
    }

    @Override
    public void delStorageUnitByISN(String isn){
        storageUnitDAO.delStorageUnitByISN(isn);
    }
}
