package freeuni.edu.ge;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InMemoryAdmnDao implements AdministratorDao{
    private List<Request> list;
    private final String ID = "6000111223344";
    private final String Password = "chemikai";

    public InMemoryAdmnDao(){
        list = new ArrayList<>();
    }

    @Override
    public void addNewDoctorRegistrationRequest(String name, String surname, String ID) {
        list.add(new Request(name,surname,ID));
    }

    @Override
    public Iterator<Request> getIterator() {
        return list.listIterator();
    }

    @Override
    public boolean checkIfItIsAdministrator(String ID, String password) {
        return ID.equals(this.ID)&&password.equals(this.Password);
    }
}
