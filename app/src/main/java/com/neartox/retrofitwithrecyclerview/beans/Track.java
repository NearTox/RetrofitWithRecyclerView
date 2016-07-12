package com.neartox.retrofitwithrecyclerview.beans;

public class Track {
    public String ID;
    public String Name;
    public String AutorID;
    public String AutorName;

    public Track(String ID, String name, String autorID, String autorName) {
        this.ID = ID;
        Name = name;
        AutorID = autorID;
        AutorName = autorName;
    }

    public String getName() {

        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getAutorName() {
        return AutorName;
    }

    public void setAutorName(String autorName) {
        AutorName = autorName;
    }

    public String getAutorID() {
        return AutorID;
    }

    public void setAutorID(String autorID) {
        AutorID = autorID;
    }

    @Override
    public String toString() {
        return this.Name;
    }
}
