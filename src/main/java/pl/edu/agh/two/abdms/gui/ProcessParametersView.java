package pl.edu.agh.two.abdms.gui;

/**
 * Created by pawko on 2014-12-03.
 */
public interface ProcessParametersView<T extends ProcessParameters> {

    public interface EventListener<T> {

        public void onConfirmedClicked(T dataPrepareParametersDto);

    }

    public void setEventListener(EventListener listener);

    public void displayData(T dto);

}
