package labs.lab6;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class Controller {
    private Model model;
    private ControllerState currentState;
    private double prevX, prevY, dX, dY;
    private IModel iModel;

    public Controller() { currentState = ready; }

    public void setModel(Model model) { this.model = model; }
    public void setIModel(IModel iModel) { this.iModel = iModel; }

    public void handlePressed(MouseEvent mouseEvent) { currentState.handlePressed(mouseEvent); }
    public void handleReleased(MouseEvent mouseEvent) { currentState.handlePressed(mouseEvent); }
    public void handleDragged(MouseEvent mouseEvent) { currentState.handleDragged(mouseEvent); }
    public void handleKeyPressed(KeyEvent keyEvent) { currentState.handleKeyPressed(keyEvent); }

    private abstract class ControllerState {
        void handlePressed(MouseEvent mouseEvent) {}
        void handleReleased(MouseEvent mouseEvent) {}
        void handleDragged(MouseEvent mouseEvent) {}
        void handleKeyPressed(KeyEvent keyEvent) {}
    }

    ControllerState ready = new ControllerState() {
        @Override
        public void handlePressed(MouseEvent mouseEvent) {
            System.out.println("click");
            prevX = mouseEvent.getX();
            prevY = mouseEvent.getY();

            double adjustedX = mouseEvent.getX() - iModel.getViewLeft();
            double adjustedY = mouseEvent.getY() - iModel.getViewTop();

            if (mouseEvent.isShiftDown()) { // should shift itself signal a state transition??
                iModel.startPath(mouseEvent.getX(), mouseEvent.getY());
                currentState = pathing;
            } else {
                if (model.contains(adjustedX, adjustedY)) {
                    iModel.select(model.whichEntity(adjustedX, adjustedY));
                    currentState = dragging;
                }
                else {
//                    model.addEntity(adjustedX, adjustedY);
//                    iModel.select(model.whichEntity(adjustedX, adjustedY));
                    currentState = creating;
                }
            }
//            currentState = ready;
        }

        public void handleKeyPressed(KeyEvent event) {
            if (event.getCode() == KeyCode.LEFT) {
                iModel.moveViewportLeft();
            }
            if (event.getCode() == KeyCode.RIGHT) {
                iModel.moveViewportRight();
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
            System.out.println("dragging - release");
            double adjustedX = mouseEvent.getX() - iModel.getViewLeft();
            double adjustedY = mouseEvent.getY() - iModel.getViewTop();
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
            System.out.println("creating - release");
            double adjustedX = mouseEvent.getX() - iModel.getViewLeft();
            double adjustedY = mouseEvent.getY() - iModel.getViewTop();

            model.addEntity(adjustedX, adjustedY);
            iModel.setSelected(model.whichEntity(adjustedX, adjustedY));
            currentState = ready;
        }
    };

    ControllerState pathing = new ControllerState() {
        @Override
        public void handleDragged(MouseEvent mouseEvent) {
            iModel.continuePath(mouseEvent.getX(), mouseEvent.getY());
        }

        @Override
        public void handleReleased(MouseEvent mouseEvent) {
            iModel.endPath();
            currentState = ready;
        }
    };
}
