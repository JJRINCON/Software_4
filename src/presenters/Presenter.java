package presenters;

import views.MainFrame;

public class Presenter {

    private MainFrame mainFrame;

    public Presenter(){
        mainFrame = new MainFrame();
        mainFrame.setVisible(true);
    }
}
