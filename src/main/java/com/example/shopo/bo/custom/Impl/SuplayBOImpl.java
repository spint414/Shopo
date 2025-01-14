package com.example.shopo.bo.custom.Impl;

import com.example.shopo.bo.custom.SuplayBO;
import com.example.shopo.dao.DAOFactory;
import com.example.shopo.dao.custom.SuplayDAO;
import com.example.shopo.dto.SuplayDTO;
import com.example.shopo.entity.Suplay;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class SuplayBOImpl implements SuplayBO {
    SuplayDAO suplayDAO= (SuplayDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.SUPLAY);

    @Override
    public boolean addSuplay(SuplayDTO suplay) throws ClassNotFoundException, SQLException {
        return suplayDAO.add(new Suplay(suplay.getSuplayerID(),suplay.getSuplayerName(),suplay.getSuplayerAddress(),suplay.getSuplayerPhone(),suplay.getSuplayerEmail()));

    }

    @Override
    public boolean deleteSuplay(String id) throws ClassNotFoundException, SQLException {
        return suplayDAO.delete(id);
    }

    @Override
    public boolean updateSuplay(SuplayDTO suplay) throws ClassNotFoundException, SQLException {
        return suplayDAO.update(new Suplay(suplay.getSuplayerID(),suplay.getSuplayerName(),suplay.getSuplayerAddress(),suplay.getSuplayerPhone(),suplay.getSuplayerEmail()));
    }

    @Override
    public SuplayDTO searchSuplay(String id) throws ClassNotFoundException, SQLException {
        Suplay search = suplayDAO.search(id);
        return new SuplayDTO(search.getSuplayerID(),search.getSuplayerName(),search.getSuplayerAddress(),search.getSuplayerPhone(),search.getSuplayerEmail());
    }

    @Override
    public ObservableList<SuplayDTO> getAllSuplay() throws ClassNotFoundException, SQLException {
        ObservableList<Suplay> all =suplayDAO.getAll();
        ObservableList<SuplayDTO> allSuplay = FXCollections.observableArrayList();
        for (Suplay ID : all) {
            SuplayDTO dto = new SuplayDTO(ID.getSuplayerID(),ID.getSuplayerName(),ID.getSuplayerAddress(),ID.getSuplayerPhone(),ID.getSuplayerEmail());
            allSuplay.add(dto);
        }
        return allSuplay;
    }

    @Override
    public int getRowCount() throws ClassNotFoundException, SQLException {
        return suplayDAO.getRowCount();
    }
}
