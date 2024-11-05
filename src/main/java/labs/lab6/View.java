package labs.lab6;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class View extends StackPane implements Subscriber {
    double width, height;
    GraphicsContext gc;
    Model model;
    IModel iModel;


    public View() {
        width = 500;
        height = 500;
        Canvas myCanvas = new Canvas(width, height);
        gc = myCanvas.getGraphicsContext2D();
        this.getChildren().add(myCanvas);
        Platform.runLater(this::requestFocus);
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void setIModel(IModel iModel) {
        this.iModel = iModel;
    }

    public void setupEvents(Controller controller) {
        setOnMousePressed(controller::handlePressed);
        setOnMouseReleased(controller::handleReleased);
        setOnMouseDragged(controller::handleDragged);
        setOnKeyPressed(controller::handleKeyPressed);
    }

    public void draw() {
        gc.clearRect(0, 0, width, height);
        gc.save();
        gc.translate(iModel.getViewLeft(), iModel.getViewTop());
        //typically this is something that owuld be stored in the interaction model
        // lazily hardcoded the world size in here (1000x1000)
        for (int i = 0; i < 11; i++) {
            gc.strokeLine(0, i*100, 1000, i*100);
            gc.strokeLine(i*100, 0, i*100, 1000);
        }

        model.getEntities().forEach(entity -> {
            gc.setFill(iModel.selected == entity ? Color.HOTPINK : Color.INDIGO);
            gc.fillOval(entity.getX()-entity.getR(),
                    entity.getY()-entity.getR(),
                    entity.getR()*2,
                    entity.getR()*2);
        });

        gc.restore();
    }

    public void modelChanged() {
        draw();
    }
}