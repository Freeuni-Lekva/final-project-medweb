package freeuni.edu.ge.DAO.Interfaces;

import freeuni.edu.ge.Models.Request;

import java.sql.SQLException;
import java.util.Iterator;

public interface AdministratorCommands {
    Iterator<Request> getIterator() throws SQLException;
    void requestAnswer(boolean answer, String ID) throws SQLException;
}
