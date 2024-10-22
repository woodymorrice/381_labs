package labs.lab6;

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
    }

    public void draw() {
        gc.clearRect(0, 0, width, height);
        model.getEntities().forEach(entity -> {
            gc.setFill(iModel.selected == entity ? Color.HOTPINK : Color.INDIGO);
            gc.fillOval(entity.getX()-entity.getR(),
                    entity.getY()-entity.getR(),
                    entity.getR()*2,
                    entity.getR()*2);
        });
    }

    public void modelChanged() {
        draw();
    }
}