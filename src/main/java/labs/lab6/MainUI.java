package labs.lab6;

import javafx.scene.layout.StackPane;

public class MainUI extends StackPane {
    public MainUI() {
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller();
        IModel iModel = new IModel();

        controller.setModel(model);
        controller.setIModel(iModel);
        view.setModel(model);
        view.setIModel(iModel);
        view.setupEvents(controller);
        model.addSubscriber(view);
        iModel.addSubscriber(view);

        this.getChildren().add(view);
    }
}