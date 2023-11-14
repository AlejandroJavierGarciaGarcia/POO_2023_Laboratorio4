package view.ProfileView.IProfileView;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

public interface IUserView {

    public void View() throws IOException, ParserConfigurationException;
    public void ModoPrestamo() throws IOException, ParserConfigurationException;
    public void ModoSeleccion() throws IOException, ParserConfigurationException;

}