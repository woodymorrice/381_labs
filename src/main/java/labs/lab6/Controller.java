package labs.lab6;

import javafx.scene.input.MouseEvent;

public class Controller {
    private Model model;
    private ControllerState currentState;
    private double prevX, prevY, dX, dY;
    private IModel iModel;


    public Controller() {
        currentState = ready;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void setIModel(IModel iModel) {
        this.iModel = iModel;
    }

    public void handlePressed(MouseEvent mouseEvent) { currentState.handlePressed(mouseEvent); }
    public void handleReleased(MouseEvent mouseEvent) { currentState.handlePressed(mouseEvent); }
    public void handleDragged(MouseEvent mouseEvent) { currentState.handleDragged(mouseEvent); }


    private abstract class ControllerState {
        void handlePressed(MouseEvent mouseEvent) {

        }

        void handleReleased(MouseEvent mouseEvent) {

        }

        void handleDragged(MouseEvent mouseEvent) {

        }
    }

    ControllerState ready = new ControllerState() {
        @Override
        public void handlePressed(MouseEvent mouseEvent) {
            prevX = mouseEvent.getX();
            prevY = mouseEvent.getY();

            if (model.contains(mouseEvent.getX(), mouseEvent.getY())) {
                iModel.setSelected(model.whichEntity(mouseEvent.getX(), mouseEvent.getY()));
                currentState = dragging;
            }
            else {
                currentState = creating;
            }
        }
    };

    ControllerState dragging = new ControllerState() {
        @Override
        public void handleDragged(MouseEvent mouseEvent) {
            dX = mouseEvent.getX() - prevX;
            dY = mouseEvent.getY() - prevY;

            prevX = mouseEvent.getX();
            prevY = mouseEvent.getY();

            model.moveEntity(iModel.getSelected(), dX, dY);
        }

        @Override
        public void handleReleased(MouseEvent mouseEvent) {
            iModel.unselect();
            currentState = ready;
        }
    };

    ControllerState creating = new ControllerState() {
        @Override
        public void handleDragged(MouseEvent mouseEvent) {
            // cancel create
            currentState = ready;
        }

        @Override
        public void handleReleased(MouseEvent mouseEvent) {
            model.addEntity(mouseEvent.getX(), mouseEvent.getY());
            currentState = ready;
        }
    };
}
